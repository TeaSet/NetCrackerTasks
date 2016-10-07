package ru.ncedu.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
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

    protected void writeFromFisToZos(FileInputStream fis, ZipOutputStream zos) throws IOException {
        byte[] buf = new byte[1024];
        int length;
        while(true) {
            length = fis.read(buf);
            if (length < 0)
                zos.write(buf, 0, length);
        }
    }
}
