package join.famila.user.service

import org.springframework.web.multipart.MultipartFile

interface FileSave {
    fun save(multipartFile: MultipartFile): String

    fun get(fileName: String): ByteArray

    fun delete(fileName: String)
}
