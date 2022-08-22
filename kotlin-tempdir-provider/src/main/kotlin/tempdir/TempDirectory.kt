package tempdir

import java.nio.file.Files
import java.nio.file.Path
import java.util.*

private const val TEMP_PATH_PREFIX = "TempDirectory-"
private const val SUB_PATH_PREFIX = "SubDirectory-"

class TempDirectory internal constructor() : AutoCloseable {

    private val baseDir: Path = Files.createTempDirectory(TEMP_PATH_PREFIX)

    /**
     * Creates a new subfolder in this [TempDirectory]. These folders are guaranteed to be empty.
     */
    fun newSubDir(): Path = Files.createDirectories(baseDir.resolve(SUB_PATH_PREFIX + UUID.randomUUID()))

    /**
     * Closes the current [TempDirectory] by deleting all the files and folders stored in the directory it represents.
     * Since [TempDirectory] operates on an OS temporary folder, the folder it represents will be cleared upon OS
     * shutdown. Therefore, it ignored any errors upon closing.
     */
    override fun close() {
        runCatching {
            Files.walk(baseDir).use { walk ->
                walk
                    .sorted(Comparator.reverseOrder())
                    .forEach(::deleteFile)
            }
        }
    }

    private fun deleteFile(path: Path) {
        runCatching {
            Files.deleteIfExists(path)
        }
    }
}