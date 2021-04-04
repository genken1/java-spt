package ru.mirea.practice12;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHelper {
    public String readFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        br.close();
        return sb.toString();
    }

    public void writeToFile(String fileName, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(text);
        writer.close();
    }

    /**
     * Функция шифрующая текстЮ по алгоритму MD5
     *
     * @param text Текст для шифрования
     * @return Зашифрованный текст
     */
    public String getMessageDigest(final String text) throws UnsupportedEncodingException {
        if ((text == null))
            return null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (md != null) {
            StringBuilder sb = new StringBuilder();
            for (byte b : md.digest()) {
                sb.append(b);
            }
            // sb.append(new String(md.digest(), StandardCharsets.UTF_8));
            return sb.toString();
        }
        return null;
    }
}
