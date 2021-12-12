package com.example.bitanalize;

import com.example.bitanalize.backend.readFile.ReadFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BitanalizeApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BitanalizeApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext("com.exemple.bitanalize");

        List<Long> numsFromConsole = new ArrayList<>();

//        FrontendConsole frontendConsole = new FrontendConsole();
//        long num1 = frontendConsole.stepOne();// запрашиваем инфо от пользователя, выбор файла
//        numsFromConsole.add(num1);//добавим ответ пользователя в список
//        long num2 = frontendConsole.stepTwo();// запрашиваем инфо от пользователя, начальный байт файла считывания
//        numsFromConsole.add(num2);//добавим ответ пользователя в список
//        long num3 = frontendConsole.stepThree();// запрашиваем инфо от пользователя, конечный байт файла считывания
//        numsFromConsole.add(num3);//добавим ответ пользователя в список
//        long num4 = frontendConsole.stepFour();// запрашиваем инфо от пользователя, сохранять / не сохранять в таблицу
//        numsFromConsole.add(num4);//добавим ответ пользователя в список


        numsFromConsole.add((long) 2);// это для теста
        numsFromConsole.add((long) 0);// это для теста
        numsFromConsole.add((long) 1457664);// это для теста
        numsFromConsole.add((long) 1);// это для теста

        long startTime = System.currentTimeMillis();

        ReadFile readFile = new ReadFile(numsFromConsole);
        double[][] arrayCountingBytes = readFile.readFileToArray();



        long finishTime = System.currentTimeMillis();
        System.out.println("\nвремя работы=" + (finishTime-startTime) + "ms.");
    }
}
