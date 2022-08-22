package tempdir

class TempDirectoryProvider {

    /**
     * Returns a new [TempDirectory]. This object implements [AutoCloseable] and holds the path to an existing temporary
     * directory, which will be removed upon closing.
     */
    fun provide() = TempDirectory()
}