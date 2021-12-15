package com.example.bitanalize.frontend;

import com.example.bitanalize.backend.readFile.ScanDirectory;
import dnl.utils.text.table.TextTable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;

public class CSVWriter {
    private final int tableWinner;
    private final List<Long> numsFromConsole;
    private final double[][] arrayCountingPiecesOfBytes;
    private final double[][] weightArrayCountingPiecesOfBytes;
    private final LinkedHashMap[] mapArrayCountingBytes;


    public CSVWriter(int tableWinner, List<Long> numsFromConsole, double[][] arrayCountingPiecesOfBytes,  double[][] weightArrayCountingPiecesOfBytes, LinkedHashMap[] mapArrayCountingBytes) {
        this.tableWinner = tableWinner;
        this.numsFromConsole = numsFromConsole;
        this.arrayCountingPiecesOfBytes = arrayCountingPiecesOfBytes;
        this.weightArrayCountingPiecesOfBytes = weightArrayCountingPiecesOfBytes;
        this.mapArrayCountingBytes = mapArrayCountingBytes;
    }

    public void CreateCSV(Object[][][] writePreparationArray) throws IOException {

        ScanDirectory scanDirectory = new ScanDirectory();
        List<String> filesList = scanDirectory.scanDir();
        long index = numsFromConsole.get(0);
        String filename = filesList.get((int) index - 1);



        if(numsFromConsole.get(3)==1) {//если пользователь выбрал сохранение в файлы
            CreateAFolder createAFolder = new CreateAFolder();
            createAFolder.setDirName("out");
            createAFolder.createDir();
            createAFolder.setDirName("out/" + filename);
            createAFolder.createDir();

            for (int i = 1; i < 16; i++) {
                String CSV_FILE = "";
                if (i == tableWinner) {
                    CSV_FILE = "./out/" + filename + "/Table_X" + (i + 1) + "_Winner.csv";
                } else {
                    CSV_FILE = "./out/" + filename + "/Table_X" + (i + 1) + ".csv";
                }

                try (
                        BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE));

                        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withDelimiter(';'));
                ) {
                    csvPrinter.printRecord("Indx", "Num", "Count", "Ic");
                    for (int j = 0; j < writePreparationArray[i].length; j++) {
                        csvPrinter.printRecord(
                                writePreparationArray[i][j][0],
                                writePreparationArray[i][j][1],
                                writePreparationArray[i][j][2],
                                writePreparationArray[i][j][3]
                        );
                    }
                    csvPrinter.flush();
                }
            }
            System.out.println("\nФайлы сохранены в папке \"out\", в одной директории вместе с исполняемой программой.");
        }
        else if(numsFromConsole.get(3)==0) {//если пользователь не выбрал сохранение в файлы, вывод в консоль
            Object [][][] arrayForconsoleTable = new Object[16][][];

            arrayForconsoleTable[0] = new Object[2][4];// для 1 битных значений 2^1=2
            arrayForconsoleTable[1] = new Object[4][4];// для 2 битных значений 2^2=4
            arrayForconsoleTable[2] = new Object[8][4];// для 3 битных значений 2^3=8
            arrayForconsoleTable[3] = new Object[16][4];// для 4 битных значений 2^4=16
            arrayForconsoleTable[4] = new Object[32][4];// для 5 битных значений 2^5=32
            arrayForconsoleTable[5] = new Object[64][4];// для 6 битных значений 2^6=64
            arrayForconsoleTable[6] = new Object[128][4];// для 7 битных значений 2^7=128
            arrayForconsoleTable[7] = new Object[256][4];// для 8 битных значений 2^8=256
            arrayForconsoleTable[8] = new Object[512][4];// для 9 битных значений 2^9=512
            arrayForconsoleTable[9] = new Object[1024][4];// для 10 битных значений 2^10=1024
            arrayForconsoleTable[10] = new Object[2048][4];// для 11 битных значений 2^11=2048
            arrayForconsoleTable[11] = new Object[4096][4];// для 12 битных значений 2^12=4096
            arrayForconsoleTable[12] = new Object[8192][4];// для 13 битных значений 2^13=8192
            arrayForconsoleTable[13] = new Object[16384][4];// для 14 битных значений 2^14=16384
            arrayForconsoleTable[14] = new Object[32768][4];// для 15 битных значений 2^15=32768
            arrayForconsoleTable[15] = new Object[65536][4];// для 16 битных значений 2^16=65536

            String[] columnNames = {"Indx", "Num", "Count", "Ic"};
            for (int i = 1; i < 16; i++) {
                for (int j = 0; j < writePreparationArray[i].length; j++) {
                    for (int k = 0; k < 4; k++) {
                        arrayForconsoleTable[i][j][k] = writePreparationArray[i][j][k];
                    }
                }
            }
            System.out.println("\nТаблица победитель: Х"+tableWinner);
            System.out.println("Файл: "+filename);
            TextTable tt = new TextTable(columnNames, arrayForconsoleTable[tableWinner]);

            tt.printTable();

            System.out.println("\nТаблица победитель: Х"+tableWinner);
            System.out.println("Файл: "+filename);
        }
    }
    public Object [][][] csvPreparation(){
        Object [][][] writePreparationArray = new Object[16][][];

        writePreparationArray[0] = new Object[2][4];// для 1 битных значений 2^1=2
        writePreparationArray[1] = new Object[4][4];// для 2 битных значений 2^2=4
        writePreparationArray[2] = new Object[8][4];// для 3 битных значений 2^3=8
        writePreparationArray[3] = new Object[16][4];// для 4 битных значений 2^4=16
        writePreparationArray[4] = new Object[32][4];// для 5 битных значений 2^5=32
        writePreparationArray[5] = new Object[64][4];// для 6 битных значений 2^6=64
        writePreparationArray[6] = new Object[128][4];// для 7 битных значений 2^7=128
        writePreparationArray[7] = new Object[256][4];// для 8 битных значений 2^8=256
        writePreparationArray[8] = new Object[512][4];// для 9 битных значений 2^9=512
        writePreparationArray[9] = new Object[1024][4];// для 10 битных значений 2^10=1024
        writePreparationArray[10] = new Object[2048][4];// для 11 битных значений 2^11=2048
        writePreparationArray[11] = new Object[4096][4];// для 12 битных значений 2^12=4096
        writePreparationArray[12] = new Object[8192][4];// для 13 битных значений 2^13=8192
        writePreparationArray[13] = new Object[16384][4];// для 14 битных значений 2^14=16384
        writePreparationArray[14] = new Object[32768][4];// для 15 битных значений 2^15=32768
        writePreparationArray[15] = new Object[65536][4];// для 16 битных значений 2^16=65536

        for (int i = 0; i < 16 ; i++) {
            int j=0;
            for (Object key : mapArrayCountingBytes[i].keySet()) {//"Indx", "Num", "Count", "Ic"
                writePreparationArray[i][j][0] = String.valueOf(j+1);//"Indx"
                writePreparationArray[i][j][1] = key;//"Num"
                writePreparationArray[i][j][2] = (int)arrayCountingPiecesOfBytes[i][(int)key];// "Count"
                writePreparationArray[i][j][3] = mapArrayCountingBytes[i].get(key);//"Ic"
                j++;
            }
        }
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < writePreparationArray[i].length; j++) {
//                System.out.println(Arrays.toString( writePreparationArray[i][j]));
//            }
//        }
        return writePreparationArray;
    }

}
