package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    public static File zipDirectory(String sourceDirPath, String zipFilePath) {
        try {
            Path zipPath = Paths.get(zipFilePath);
            Path sourceDir = Paths.get(sourceDirPath);
            if (!Files.exists(sourceDir)) {
                throw new IOException("Source directory does not exist: " + sourceDirPath);
            }

            ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipPath));
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    zipOutputStream.putNextEntry(new ZipEntry(sourceDir.relativize(file).toString()));
                    Files.copy(file, zipOutputStream);
                    zipOutputStream.closeEntry();
                    return FileVisitResult.CONTINUE;
                }
            });
            zipOutputStream.close();
            return zipPath.toFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}