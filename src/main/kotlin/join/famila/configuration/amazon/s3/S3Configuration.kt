package join.famila.configuration.amazon.s3

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Configuration(
    @Value("\${spring.cloud.aws.accessKey}")
    private val accessKey: String,

    @Value("\${spring.cloud.aws.secretKey}")
    private val secretKey: String,

    @Value("\${spring.cloud.aws.region}")
    private val region: String,
) {
    @Bean
    fun amazonS3Client(): AmazonS3Client {
        return AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)).let {
            AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(it)
                .build() as AmazonS3Client
        }
    }
}
