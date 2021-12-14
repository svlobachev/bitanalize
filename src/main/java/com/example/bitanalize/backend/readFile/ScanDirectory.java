package com.example.bitanalize.backend.readFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScanDirectory {

    public List<String> scanDir() {
        List<String> filesList = new ArrayList<>();
        String path = "./files";
        File  dir = new File(path);// определяем объект для каталога
        // если объект представляет каталог
        if (dir.isDirectory()) {
            // получаем все вложенные объекты в каталоге
            for (File item : Objects.requireNonNull(dir.listFiles())) {
                if (!item.isDirectory()) {
                    filesList.add(item.getName());
//                    System.out.println(item.getName() + "\t file");
                }
            }
        }
        else{
            System.out.println("Не найден каталог «"+path+"» c файлами для анализа.\n " +
                    "Каталог должен располагаться в одной папке вместе с исполняемой программой.");
            System.exit(0);
        }
        if(0 == filesList.size()){
            System.out.println("В каталоге «"+path+"» нет файлов для анализа.");
            System.exit(0);
        }
        return filesList;
    }
}



