package ru.ncedu.java.utils;

import java.io.IOException;

/**
 * Created by Dmitriy on 07.10.2016.
 */
public interface Archiver {
    public void createZipArchiveWithFiles(String zipArchiveName, String filename) throws IOException;
}
