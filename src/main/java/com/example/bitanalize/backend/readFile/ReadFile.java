package com.example.bitanalize.backend.readFile;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

public class ReadFile {
    private final List<Long> numsFromConsole;
    static long pointOfstart;

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

        double[][] arrayCountingBytes = new double[16][];// объявим

        arrayCountingBytes[0] = new double[2];// для 1 битных значений 2^1=2
        arrayCountingBytes[1] = new double[4];// для 2 битных значений 2^2=4
        arrayCountingBytes[2] = new double[8];// для 3 битных значений 2^3=8
        arrayCountingBytes[3] = new double[16];// для 4 битных значений 2^4=16
        arrayCountingBytes[4] = new double[32];// для 5 битных значений 2^5=32
        arrayCountingBytes[5] = new double[64];// для 6 битных значений 2^6=64
        arrayCountingBytes[6] = new double[128];// для 7 битных значений 2^7=128
        arrayCountingBytes[7] = new double[256];// для 8 битных значений 2^8=256
        arrayCountingBytes[8] = new double[512];// для 9 битных значений 2^9=512
        arrayCountingBytes[9] = new double[1024];// для 10 битных значений 2^10=1024
        arrayCountingBytes[10] = new double[2048];// для 11 битных значений 2^11=2048
        arrayCountingBytes[11] = new double[4096];// для 12 битных значений 2^12=4096
        arrayCountingBytes[12] = new double[8192];// для 13 битных значений 2^13=8192
        arrayCountingBytes[13] = new double[16384];// для 14 битных значений 2^14=16384
        arrayCountingBytes[14] = new double[32768];// для 15 битных значений 2^15=32768
        arrayCountingBytes[15] = new double[65536];// для 16 битных значений 2^16=65536


        for (long i=1; i<=16; i++) {
            for (long j=startByte; j< 1; j+=i) {
                try{
                    int k=-1;
                    ArrayList<String> plist = new ArrayList<String>();
                    for(int n=0; n<i; n++){
                        //берем байт со смещением
                        long myBites = (long) (data[(int) (j + (++k))] & 0xff);
                        // форматируем и складываем в список
                        plist.add(String.format("%8s", Long.toBinaryString(myBites)).replace(' ', '0'));
                    }
                    String joinedString = String.join("", plist);
//                    System.out.println(joinedString);
                    System.out.println(Arrays.toString(
                            joinedString.split("(?<=\\G.{"+i+"})")// нарезаем строку на нужные отрезки и складываем в массив
                    ));
                } catch (Exception e) {
//                            e.printStackTrace();
                }
            }
        }
        return  arrayCountingBytes;
    }
}





//            System.out.println(String.format("%8s", Integer.toBinaryString(data[i]  & 0xff)).replace(' ', '0'));

//        for (long i=startByte; i< finishByte; i++) {
//            int ii = (data[(int) (i+1)] & 0xff);// преобразуем биты в число
//            String bits8String = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
//            System.out.println(bits8String);
//        }



//        for (long i=startByte; i< finishByte; i++) {//заполняем массив 8 битовыми значениями 2^8=256
//            int ii = (int) data[(int) i] & 0xff;// преобразуем биты в число
//            arrayCountingBytes[0][0][ii]++;// считаем сколько раз встречается
//        }

//        for (long i=startByte; i< finishByte; i=i+2) {//заполняем массив 16 битовыми значениями 2^16=65536
//            int ii = ((data[(int) i] & 0xff) << 8) | (data[(int) (i+1)] & 0xff);// преобразуем биты в число
//            arrayCountingBytes[2][0][ii]++;// считаем сколько раз встречается
//        }
//