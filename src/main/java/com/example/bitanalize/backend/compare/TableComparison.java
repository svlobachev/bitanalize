package com.example.bitanalize.backend.compare;

import java.util.LinkedHashMap;

public class TableComparison {
    private  LinkedHashMap[] mapArrayCountingBytes;

    public TableComparison(LinkedHashMap[] mapArrayCountingBytes) {
        this.mapArrayCountingBytes = mapArrayCountingBytes;
    }
    public void calculatingTheWinnerTable(){
        int[] winner = new int[16];
        for (int i = 15; i < mapArrayCountingBytes.length; i--) {
            int j =0;
            if(i > 0) {
                for (Object key : mapArrayCountingBytes[i - 1].keySet()) {
                    int jj = 0;
                    for (Object underKey : mapArrayCountingBytes[i].keySet()) {
                        if (j == jj) {// одинаковые по счету строки
                            if ((double) mapArrayCountingBytes[i - 1].get(key) > (double) mapArrayCountingBytes[i].get(underKey)) {
                                winner[i - 1] = 1;
                                winner[i] = 0;
                            } else if ((double) mapArrayCountingBytes[i - 1].get(key) < (double) mapArrayCountingBytes[i].get(underKey)) {
                                winner[i - 1] = 0;
                                winner[i] = 1;
                            }
//                        System.out.println("Tabl# " + (i-1) +" Str# : "+ j + " Val: " + mapArrayCountingBytes[i-1].get(key));
//                        System.out.println("Tabl# " + (i) +" Str# : "+ jj + " Val: " + mapArrayCountingBytes[i].get(underKey));
                            break;
                        }
                        jj++;
                    }
                    j++;
                }
            }
        }
    }
}
