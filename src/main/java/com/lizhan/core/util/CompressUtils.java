package com.lizhan.core.util;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.io.*;

public class CompressUtils {

    public static void Compress7z(File inFile, File outFile) throws IOException {
        if (!inFile.exists()) {
            return;
        }
        SevenZOutputFile sevenZOutputFile = new SevenZOutputFile(outFile);
        Compress7z("", inFile, sevenZOutputFile);
        sevenZOutputFile.finish();
    }

    private static void Compress7z(String basePath, File inFile, SevenZOutputFile sevenZOutputFile) throws IOException {
        SevenZArchiveEntry entry = sevenZOutputFile.createArchiveEntry(inFile, basePath + inFile.getName());
        sevenZOutputFile.putArchiveEntry(entry);
        if (inFile.isFile()) {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inFile));
            int read = 0;
            while ((read = bis.read()) != -1) {
                sevenZOutputFile.write(read);
            }
            bis.close();
        }
        sevenZOutputFile.closeArchiveEntry();
        if (inFile.isDirectory()) {
            for (File file : inFile.listFiles()) {
                Compress7z(basePath + inFile.getName() + File.separator, file, sevenZOutputFile);
            }
        }
    }

    public static void DeCompress7z(File inFile, File outFile) throws IOException {
        if (!inFile.exists()) {
            return;
        }
        SevenZFile sevenZFile = new SevenZFile(inFile);
        if (!outFile.exists()) {
            outFile.mkdirs();
        }
        SevenZArchiveEntry entry;
        while ((entry = sevenZFile.getNextEntry()) != null) {
            if (entry.isDirectory()) {
                File file = new File(outFile.getAbsolutePath() + File.separator + entry.getName());
                file.mkdirs();
            } else {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile.getAbsolutePath() + File.separator + entry.getName()));
                byte[] byteBuffer = new byte[8194];
                long needRead = entry.getSize();
                while (needRead > 0) {
                    int thisRead = sevenZFile.read(byteBuffer, 0, (int) (needRead < 8194 ? needRead : 8194));
                    needRead -= thisRead;
                    bos.write(byteBuffer, 0, thisRead);
                }
                bos.flush();
                bos.close();
            }
        }
        sevenZFile.close();
    }

    public static void main(String[] args) throws IOException {
        File srcFile = new File("src");
        File sevenFile = new File("src.7z");
        File deFile = new File("dir");
        Compress7z(srcFile, sevenFile);
        DeCompress7z(sevenFile, deFile);
    }

}
