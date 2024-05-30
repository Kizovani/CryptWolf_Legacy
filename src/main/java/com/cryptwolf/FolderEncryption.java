package com.cryptwolf;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class FolderEncryption {
    
    //TODO: PLEASE NEVER USE THIS KEY THIS IS JUST FOR TESTING (16 bit)
    private static final String KEY = "abcdefghijklmnop";

    public static void encryptFolder(String folderPath, String destPath, String key) throws Exception {
        // Create cipher for AES encryption
        byte[] keyBytes = key.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Create ZipOutputStream for writing zip file
        try (ZipOutputStream zipOut = new ZipOutputStream(new CipherOutputStream(new FileOutputStream(destPath), cipher))) {
            // Walk through all files in the folder
            Files.walk(Paths.get(folderPath))
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        // For each file, create a new ZipEntry and write file data
                        try {
                            zipOut.putNextEntry(new ZipEntry(path.toString()));
                            Files.copy(path, zipOut);
                            zipOut.closeEntry();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        }
    }

    public static void decryptFolder(String srcPath, String destFolder, String key) throws Exception {
        // Create cipher for AES decryption
        byte[] keyBytes = key.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Create ZipInputStream for reading zip file
        try (ZipInputStream zipIn = new ZipInputStream(new CipherInputStream(new FileInputStream(srcPath), cipher))) {
            ZipEntry entry;
            // Read each entry from the zip file
            while ((entry = zipIn.getNextEntry()) != null) {
                // For each entry, create a new file and write the decrypted data
                File file = new File(destFolder, entry.getName());
                Files.copy(zipIn, file.toPath());
                zipIn.closeEntry();
            }
        }
    }
}