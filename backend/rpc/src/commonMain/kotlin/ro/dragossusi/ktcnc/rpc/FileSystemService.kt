package ro.dragossusi.ktcnc.rpc

import kotlinx.rpc.annotations.Rpc

@Rpc
interface FileSystemService {
    suspend fun getRoot(): FileResponse

    suspend fun getFile(path: String): FileResponse

    suspend fun getFilesInPath(path: String): List<FileResponse>
}
