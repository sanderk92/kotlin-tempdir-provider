# Temporary Directory Provider

A simple Autocloseable Temporary Directory Provider that can be used for temporary storage. All files are cleaned up 
when the Autocloseable is closed.

```kotlin

tempDirectoryProvider.provide().use { tempDir ->
    
    val firstTemporarySubDir = tempDir.newSubDir()
    val firstTemporaryFile = firstTemporarySubDir.resolve("temporary-file.txt")
    Files.createFile(firstTemporaryFile)

    val secondTemporarySubDir = tempDir.newSubDir()
    val secondTemporaryFile = secondTemporarySubDir.resolve("temporary-file.txt")
    Files.createFile(secondTemporaryFile)
}
```

<sub>* All subdirectories created are guaranteed to be empty.<sub>
