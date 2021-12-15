package com.example.bitanalize.frontend;


import java.io.*;


public class CreateAFolder {
    private String dirName;

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public void createDir() {
        File theDir = new File(dirName);

    // if the directory does not exist, create it
        if (!theDir.exists()) {
//            System.out.println("Создание директории: " + theDir.getName());
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("Директория создана. " + theDir.getName());
            }
        }
    }
}
