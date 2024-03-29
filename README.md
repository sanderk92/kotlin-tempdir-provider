# Kotlin Temporary Directory Provider

A simple Autocloseable Temporary Directory Provider that can be used for temporary storage. All files are removed 
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
