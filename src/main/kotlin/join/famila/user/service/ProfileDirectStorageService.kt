package join.famila.user.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Service
class ProfileDirectStorageService(
    @Value("\${storage.profile.directory}")
    val filePath: String,
) : FileSave {
    override fun save(multipartFile: MultipartFile): String {
        val originalFileName = multipartFile.originalFilename

        require(originalFileName != null)
        createDirectory()

        return createUUidFileName(originalFileName).apply {
            multipartFile.transferTo(Paths.get(filePath, this))
        }
    }

    override fun get(fileName: String): ByteArray {
        createDirectory()
        val fileInputStream = File("${filePath}${fileName}")

        return Files.readAllBytes(fileInputStream.toPath())
    }

    override fun delete(fileName: String) {
        createDirectory()
        val deleteFile = File("${filePath}${fileName}")

        if (deleteFile.exists()) {
            Files.delete(deleteFile.toPath())
        }
    }

    private fun createUUidFileName(fileName: String): String {
        val index = fileName.lastIndexOf(".")
        val ext = fileName.substring(index)

        return "${UUID.randomUUID()}$ext"
    }

    private fun createDirectory() {
        val path = Paths.get(filePath)

        if (Files.notExists(path)) {
            Files.createDirectories(path)
        }
    }
}
