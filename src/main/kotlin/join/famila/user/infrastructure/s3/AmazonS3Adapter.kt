package join.famila.user.infrastructure.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import java.util.UUID
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.multipart.MultipartFile

@Repository
class AmazonS3Adapter(
    private val amazonS3Client: AmazonS3Client,

    @Value("\${spring.cloud.aws.s3.bucket}")
    private val bucketName: String,
) {
    fun upload(file: MultipartFile): String {
        val fileName = UUID.randomUUID().toString()

        val metadata = ObjectMetadata().apply {
            contentLength = file.size
            contentType = file.contentType
        }

        PutObjectRequest(bucketName, fileName, file.inputStream, metadata)
            .apply { amazonS3Client.putObject(this) }

        return fileName
    }

    fun delete(fileName: String) {
        DeleteObjectRequest(bucketName, fileName).apply { amazonS3Client.deleteObject(this) }
    }
}
