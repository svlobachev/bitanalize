package com.example.bitanalize.backend.compare;

import java.util.LinkedHashMap;
import java.util.Map;

public class SortingInMap {
   private final double[][] weightArrayCountingPiecesOfBytes;

    public SortingInMap(double[][] arrayCountingPiecesOfBytes) {
        this.weightArrayCountingPiecesOfBytes = arrayCountingPiecesOfBytes;
    }
    public LinkedHashMap[] sortingArrayCountingPiecesOfBytesInMap(){

        Map<Integer, Double> tempHashMap = new LinkedHashMap<>();
        LinkedHashMap[] mapArrayCountingBytes;// создаем  массив

        mapArrayCountingBytes = new LinkedHashMap[16];// объявим размер уровня

        for (int i = 0; i < weightArrayCountingPiecesOfBytes.length; i++) {
            tempHashMap = new LinkedHashMap<>();
            for (int ii = 0; ii < weightArrayCountingPiecesOfBytes[i].length; ii++) {
                tempHashMap.put(ii, weightArrayCountingPiecesOfBytes[i][ii]);
            }

            final LinkedHashMap<Integer, Double> newMap = new LinkedHashMap<>();
            tempHashMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())// сортируем
                    .forEachOrdered(entry -> newMap.put(entry.getKey(), entry.getValue()));//смладываем в Мар

            mapArrayCountingBytes[i] = newMap;
        }
//        for (int i = 0; i < mapArrayCountingBytes.length; i++) {
//            for (Object key : mapArrayCountingBytes[i].keySet()) {
//                System.out.println("   " + key + "     " + mapArrayCountingBytes[i].get(key));
//            }
//        }
        return mapArrayCountingBytes;
    }
}
