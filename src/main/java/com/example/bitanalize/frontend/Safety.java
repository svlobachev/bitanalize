package com.example.bitanalize.frontend;

import jdk.swing.interop.SwingInterOpUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Safety {

    public void SafetyRun() throws IOException {

        long currentTime = System.currentTimeMillis();

//        File createFile = new File("key.txt");
//        createFile.createNewFile();
        boolean key = false;
        try {
            String[] lines = StringUtils.split(FileUtils.readFileToString(new File("key.txt")), '\n');
            try{
                if(lines[0].equals("80904hhj834u8h830092392jk34820923jld90")) key = true;
            } catch (Exception e) {
//                e.printStackTrace();
            }

        } catch (IOException e) {
//            e.printStackTrace();
        }
        if(currentTime > 1639792341711l && key == false){
            File createFile = new File("key.txt");
            createFile.createNewFile();
            System.out.println("\n\nПрограмме требуется ключ. " +
                    "Пожалуйста, обратитесь к разработчику. " +
                    "\nт. +79525687080 " +
                    "Skype: sv_lobachev");

            System.out.println(currentTime);
            System.out.println((currentTime) + (60*60*24*7)*1000);

            System.exit(0);
        }
    }

}
