package com.example.bitanalize.frontend;

import com.example.bitanalize.backend.readFile.ScanDirectory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FrontendConsole {
    long num1;
    long num2;
    long num3;
    long num4;
    List<String> filesList = new ArrayList<>();
    String path = "./files";
    int ii;
    String stepTwoFile;
    int result;

    public long stepOne() {
        Scanner in = new Scanner(System.in);
        ScanDirectory scanDirectory = new ScanDirectory();
        filesList = scanDirectory.scanDir();

        System.out.println("\n");
        for (int i = 0; i < filesList.size(); i++) {
            File file = new File(path+"/"+filesList.get(i));
            int result = (int)file.length();
            ii = i + 1;
            System.out.println(ii + ". " + filesList.get(i) +  " (" + result + " байт)");
        }

        System.out.println("\nВыберите файл с 1 до " + ii + ".");
        num1 = 0;
        try {
            num1 = in.nextInt();
        } catch (Exception e) {
            System.out.println("Осуществлен не верный ввод, ожидалось число.\n");
            stepOne();
        }
        if (num1 > ii || num1 <= 0){
            System.out.println("Осуществлен не верный ввод, ожидалось число от 1 до " + ii + "\n");
            stepOne();
        }
//        in.close();
        return num1;
    }
    public long stepTwo() {
        Scanner in = new Scanner(System.in);

        File file = new File(path + "/" + filesList.get((int) (num1-1)));
        result = (int) file.length();
        stepTwoFile = filesList.get((int) (num1-1)) + " (" + result + " байт)";


        System.out.println("\nВыберите начальный байт для анализа файла "+ stepTwoFile);
        try {
            num2 = in.nextInt();
        } catch (Exception e) {
            System.out.println("Осуществлен не верный ввод, ожидалось число.");
            stepTwo();
        }
        if (num2 > result || num2  < 0){
            System.out.println("Осуществлен не верный ввод, ожидалось число от 0 до "+ result +" байт.");
            stepTwo();
        }
        return num2 ;
    }
    public long stepThree() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nВыберите конечный байт, число от "+ num2 + " до "+ result +" байт.");
        try {
            num3 = in.nextInt();
        } catch (Exception e) {
            System.out.println("Осуществлен не верный ввод, ожидалось число.");
            stepThree();
        }
        if (num3 > result || num3 <= 0 || num3 <= num2){
            System.out.println("Осуществлен не верный ввод, ожидалось число от "+ num2 + " до "+ result +" байт.");
            stepThree();
        }
        return num3;
    }

    public long stepFour() {
        Scanner in = new Scanner(System.in);
        System.out.println("\nВыберите ключ сохранения/не сохранения результатов в файлы. 1 - да или 0 - нет.");
        try {
            num4 = in.nextInt();
        } catch (Exception e) {
            System.out.println("Осуществлен не верный ввод, ожидалось число.");
            stepFour();
        }
        if (num4 != 0 && num4 != 1 ){
            System.out.println("Осуществлен не верный ввод, ожидалось число  1 - да или 0 - нет.");
            stepFour();
        }
        System.out.println(" Процесс подсчета запущен, ожидайте результата...");
        in.close();
        return num4;
    }
}
