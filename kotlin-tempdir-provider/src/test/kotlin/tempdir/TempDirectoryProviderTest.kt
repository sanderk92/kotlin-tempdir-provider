package tempdir

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

class TempDirectoryProviderTest {

    @Test
    fun `Temporary directory is removed when close is called`() {

        var firstTempDirectoryPath: Path
        var secondTempDirectoryPath: Path

        TempDirectoryProvider().provide().use { tempDirectory ->

            firstTempDirectoryPath = tempDirectory.newSubDir()
            secondTempDirectoryPath = tempDirectory.newSubDir()
            assertThat(firstTempDirectoryPath).isNotEqualTo(secondTempDirectoryPath)

            val firstTempFilePath = Files.createFile(firstTempDirectoryPath.resolve("file.txt"))
            val secondTempFilePath = Files.createFile(secondTempDirectoryPath.resolve("file.txt"))
            assertThat(firstTempFilePath).exists()
            assertThat(secondTempFilePath).exists()

        }
        assertThat(firstTempDirectoryPath).doesNotExist()
        assertThat(secondTempDirectoryPath).doesNotExist()
    }
}