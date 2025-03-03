package utils;

import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class ZipUtils {

    public static String zipResultsFolder(String sourceDir, String zipFilePath) {
        File sourceFolder = new File(sourceDir);
        if (!sourceFolder.exists()) {
            System.err.println("⚠️ Source folder not found: " + sourceDir);
            return null;
        }

        try {
            File zipFile = new File(zipFilePath);
            if (zipFile.exists()) {
                zipFile.delete();
            }

            try (FileOutputStream fos = new FileOutputStream(zipFile);
                 ZipOutputStream zipOut = new ZipOutputStream(fos)) {

                zipFile(sourceFolder, sourceFolder.getName(), zipOut);
            }

            System.out.println("✅ Zipped folder: " + zipFilePath);
            return zipFilePath;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Failed to zip the folder.");
            return null;
        }
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }

        if (fileToZip.isDirectory()) {
            File[] children = fileToZip.listFiles();
            if (children != null) {
                for (File childFile : children) {
                    zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
                }
            }
            return;
        }

        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }
}
