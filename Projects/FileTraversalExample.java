package Projects;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileTraversalExample {

    public static void main(String[] args) throws IOException {
        Path startPath = Paths.get("C:\\Users\\mehme\\Desktop\\Repositories");

        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                System.out.println("File: " + file.toString());
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
