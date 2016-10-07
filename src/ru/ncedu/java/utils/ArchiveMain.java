package ru.ncedu.java.utils;

import java.io.IOException;

/**
 * Created by Dmitriy on 07.10.2016.
 */
public class ArchiveMain {
    public static void main(String[] args) {
        Archiver archiver = new ZipArchive();
        try {
            archiver.createZipArchiveWithFiles(args[0], args[1]);
            archiver.recoverZipArchiveWithFiles(args[0], args[2]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finish successfully!");
    }
}
