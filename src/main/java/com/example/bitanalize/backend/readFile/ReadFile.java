package com.example.bitanalize.backend.readFile;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

public class ReadFile {
    private final List<Long> numsFromConsole;

    public ReadFile(List<Long> numsFromConsole) {
        this.numsFromConsole = numsFromConsole;
    }

    public double[][]  readFileToArray() throws Exception {
        ScanDirectory scanDirectory = new ScanDirectory();
        List<String> filesList = scanDirectory.scanDir();
        long index = numsFromConsole.get(0);
        String filename = filesList.get((int) index - 1);
        long startByte = numsFromConsole.get(1);
        long finishByte = numsFromConsole.get(2);

        File file = new File("./files/"+ filename);
        byte[] data  = readFileToByteArray(file);

        double[][] arrayCountingPiecesOfBytes = new double[16][];// объявим

        arrayCountingPiecesOfBytes[0] = new double[2];// для 1 битных значений 2^1=2
        arrayCountingPiecesOfBytes[1] = new double[4];// для 2 битных значений 2^2=4
        arrayCountingPiecesOfBytes[2] = new double[8];// для 3 битных значений 2^3=8
        arrayCountingPiecesOfBytes[3] = new double[16];// для 4 битных значений 2^4=16
        arrayCountingPiecesOfBytes[4] = new double[32];// для 5 битных значений 2^5=32
        arrayCountingPiecesOfBytes[5] = new double[64];// для 6 битных значений 2^6=64
        arrayCountingPiecesOfBytes[6] = new double[128];// для 7 битных значений 2^7=128
        arrayCountingPiecesOfBytes[7] = new double[256];// для 8 битных значений 2^8=256
        arrayCountingPiecesOfBytes[8] = new double[512];// для 9 битных значений 2^9=512
        arrayCountingPiecesOfBytes[9] = new double[1024];// для 10 битных значений 2^10=1024
        arrayCountingPiecesOfBytes[10] = new double[2048];// для 11 битных значений 2^11=2048
        arrayCountingPiecesOfBytes[11] = new double[4096];// для 12 битных значений 2^12=4096
        arrayCountingPiecesOfBytes[12] = new double[8192];// для 13 битных значений 2^13=8192
        arrayCountingPiecesOfBytes[13] = new double[16384];// для 14 битных значений 2^14=16384
        arrayCountingPiecesOfBytes[14] = new double[32768];// для 15 битных значений 2^15=32768
        arrayCountingPiecesOfBytes[15] = new double[65536];// для 16 битных значений 2^16=65536

        // этот блок формирует строку в виде - битовое представление как строка
        for (int i=1; i<=16; i++) {// это разрядность возвращаемой строки
//            for (long j=startByte; j< finishByte; j+=(i+1)) {//это рабочий полный ваиант обхода
            for (long j=startByte; j<=10000; j+=(i+1)) {// это тестовый
                long[] longPiecesOfString = new long[8];
                String[] arrPiecesOfString = new String[8];
                try {
                    int k = -1;
                    ArrayList<String> plist = new ArrayList<>();
                    for (int n = 0; n < i; n++) {
                        //берем байт со смещением
                        long myBites = (data[(int) (j + (++k))] & 0xff);
                        // форматируем и складываем в список
                        String strMyBites = String.format("%8s", Long.toBinaryString(myBites)).replace(' ', '0');
                        plist.add(strMyBites);
                    }
                    String joinedString = String.join("", plist);
                    arrPiecesOfString = joinedString.split("(?<=\\G.{" + i + "})");// нарезаем строку и складируем в массив
                    for (int u=0; u<arrPiecesOfString.length; u++){
                            longPiecesOfString[u] = parseLong(arrPiecesOfString[u]);// переводим двоичную строку в число long
                    }
                } catch (Exception e) {
//                            e.printStackTrace();
                }

                for (int g=0; g < arrPiecesOfString.length; g++) {
                    if (arrPiecesOfString[g] != null) {
                        for (int r = 0; r < arrayCountingPiecesOfBytes[i - 1].length; r++) {
                            if (longPiecesOfString[g] == r) arrayCountingPiecesOfBytes[i-1][r]++;// заполняем массив подсчетом
                        }
                    }
                }
            }
        }
        return arrayCountingPiecesOfBytes;
    }

    public double[][] calculatingThePercentageOfOccurrences(double[][] arrayCountingPiecesOfBytes){
        //считаем долю присутствия по формуле от заказчика TAB.ic [i] = int(TAB.count [i]/(Sum(TAB.count [i]))
        double sumCountbitArray;
        for (int i=0; i < 16;  i++) {
            sumCountbitArray = 0;
            for (int ii = 0; ii < arrayCountingPiecesOfBytes[i].length; ii++) {// считаем общую сумму для каждой таблицы учитывая её размерность
                sumCountbitArray += arrayCountingPiecesOfBytes[i][ii];
            }
            for (int ii = 0; ii < arrayCountingPiecesOfBytes[i].length; ii++) {
                //считаем долю присутствия по формуле от заказчика TAB.ic [i] = int(TAB.count [i]/(Sum(TAB.count [i]))
                arrayCountingPiecesOfBytes[i][ii] = arrayCountingPiecesOfBytes[i][ii] / sumCountbitArray;
            }
        }

        return arrayCountingPiecesOfBytes;
    }

    private static long parseLong(String s) {
        return new BigInteger(s, 2).longValue();
    }
}