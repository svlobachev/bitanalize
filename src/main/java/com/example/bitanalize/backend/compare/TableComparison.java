package com.example.bitanalize.backend.compare;

import java.util.LinkedHashMap;

public class TableComparison<winner> {
    private final LinkedHashMap[] mapArrayCountingBytes;

    public TableComparison(LinkedHashMap[] mapArrayCountingBytes) {
        this.mapArrayCountingBytes = mapArrayCountingBytes;
    }
    public int calculatingTheWinnerTable(){
        int tableWinner =15;
        for (int i = 15; i > 0; i--) {
           if(tableWinner != i ) tableWinner = compareTwoTables(i, tableWinner);
        }
        return tableWinner;
    }
    private int compareTwoTables(int table1, int table2){
        int tableNumLess;
        int tableNumMore;
        if(table1 > table2){//
            tableNumLess = table2;
            tableNumMore = table1;
        }
        else if(table1 < table2){
            tableNumLess = table1;
            tableNumMore = table2;
        }
        else return -1;
        int j =0;
        for (Object keyTableNumLess : mapArrayCountingBytes[tableNumLess].keySet()) {
            int jj = 0;
            for (Object keyTableNumMore : mapArrayCountingBytes[tableNumMore].keySet()) {
                if (j == jj) {// одинаковые по счету строки
                    if ((double) mapArrayCountingBytes[tableNumLess].get(keyTableNumLess) > (double) mapArrayCountingBytes[tableNumMore].get(keyTableNumMore)) {
//                        System.out.println("Tab1: "+tableNumLess+" Tab2: "+ tableNumMore +" WinnerTab: "+tableNumLess+" str: "+j);
                        return tableNumLess;
                    } else if ((double) mapArrayCountingBytes[tableNumLess].get(keyTableNumLess) < (double) mapArrayCountingBytes[tableNumMore].get(keyTableNumMore)) {
//                        System.out.println("Tab1: "+tableNumLess+" Tab2: "+ tableNumMore +" WinnerTab: "+tableNumLess+" str: "+j);
                        return tableNumMore;
                    }
                }
                jj++;
            }
            j++;
        }
        return tableNumLess;
    }
}
