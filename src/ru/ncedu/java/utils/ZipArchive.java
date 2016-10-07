package ru.ncedu.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Dmitriy on 07.10.2016.
 */
public class ZipArchive implements Archiver {
    @Override
    public void createZipArchiveWithFiles(String zipArchiveName, String filename) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipArchiveName));
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        ZipEntry ze = new ZipEntry(file.getName());
        zos.putNextEntry(ze);

        writeFromFisToZos(fis, zos);

        fis.close();
        zos.closeEntry();
        zos.close();
    }

    @Override
    public void recoverZipArchiveWithFiles(String zipArchiveName, String path) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipArchiveName));
        FileOutputStream fos = null;
        ZipEntry ze = null;

        if (!Files.exists(Paths.get(path)))
            new File(path).mkdirs();

        while((ze = zis.getNextEntry()) != null) {
            String outFileName = ze.getName();
            if (ze.isDirectory())
                new File(path, outFileName).mkdirs();
            else
                fos = new FileOutputStream(new File(path, outFileName));

            writeFromZisToFos(zis, fos);
            zis.close();
        }
    }

    protected void writeFromFisToZos(FileInputStream fis, ZipOutputStream zos) throws IOException {
        byte[] buf = new byte[1024];
        int length;
        while(true) {
            length = fis.read(buf);
            if (length < 0) break;
            zos.write(buf, 0, length);
        }
    }

    protected void writeFromZisToFos(ZipInputStream zis, FileOutputStream fos) throws IOException {
        byte[] buf = new byte[1024];
        int length;
        while (true) {
            length = zis.read(buf);
            if (length < 0) break;
            fos.write(buf, 0, length);
        }
    }
}
