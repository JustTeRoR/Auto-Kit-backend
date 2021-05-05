package com.justterror.auto_kit.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ResponsesFactory {
    public static String extendResponsePartBySerial(List<Object[]> objList) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String globalResponseForList = "[\n ";
        String singlePartTemplate = "{ \n" +
                "   \"id\": \"%d\",\n" +
                "   \"quantity\": \"%d\",\n" +
                "   \"measureName\": \"%s\",\n" +
                "   \"makeName\": \"%s\",\n" +
                "   \"partTypeName\": \"%s\",\n" +
                "   \"isOEM\": \"%b\",\n" +
                "   \"lastPurchasePrice\": \"%f\",\n" +
                "   \"serialNumber\": \"%s\",\n" +
                "   \"lastDeliveryTime\": \"%s\" \n }";

        for (int i = 0; i < objList.size(); i++) {
            String formattedDate = objList.get(i)[8].toString();
            String wrapObject = String.format(singlePartTemplate, objList.get(i)[0],objList.get(i)[1], objList.get(i)[2], objList.get(i)[3],
                    objList.get(i)[4], objList.get(i)[5], objList.get(i)[6],objList.get(i)[7], formattedDate);
            globalResponseForList = globalResponseForList.concat(wrapObject);
            if (i != objList.size() -1) {
                globalResponseForList = globalResponseForList.concat(",");
            }
        }
        globalResponseForList = globalResponseForList.concat("]");
        return globalResponseForList;
    }
}
