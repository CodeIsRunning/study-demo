package com.study.demo.studylittlepsvm.files;

import sun.nio.cs.ext.ExtendedCharsets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: study-demo
 * @description:
 * @author: lxf
 * @create: 2019-08-29 13:06
 **/
public class Combine {

    private  static File fileTwo = new File("C:\\Users\\Administrator\\Desktop\\20190829s11.csv");

    private  static   File fileOne = new File("C:\\Users\\Administrator\\Desktop\\20190829s(2).csv");

    private  static File newOutput = new File("C:\\Users\\Administrator\\Desktop\\new.csv");

    public static void main(String[] args) throws Exception {
        combine();
        //test();
    }


    public static void combine() throws Exception {


        List<String> listOne = Files.readAllLines(fileOne.toPath());
        List<String> listTwo = Files.readAllLines(fileTwo.toPath());

        List<String> output = new ArrayList<>();
        if (fileOne.length() > fileTwo.length()) {
            for (String strOne : listOne) {
                String[] strOneArgs = strOne.split(",");
                String cardNo = strOneArgs[0];
                String posNo = strOneArgs[13];
                String dept = strOneArgs[8];
                /*if (!dept.equals(02)|| !dept.equals(2)) {
                    continue;
                }*/

                boolean isOk = true;

                for (String strTwo : listTwo) {
                    String[] strTwoArgs = strTwo.split(",");
                    String cardNoTwo = strTwoArgs[0];
                    String posNoTwo = strTwoArgs[13];
                    String deptTwo = strTwoArgs[8];
                    if (cardNo.equals(cardNoTwo) && posNo.equals(posNoTwo)) {
                        strOneArgs[10] = String.valueOf(Integer.valueOf(strOneArgs[10]) + Integer.valueOf(strTwoArgs[10]));
                        DecimalFormat df = new DecimalFormat("0.00");
                        BigDecimal totalMoney = new BigDecimal(strOneArgs[9]);
                        totalMoney = totalMoney.add(new BigDecimal(strTwoArgs[9]));
                        strOneArgs[9] = String.valueOf(df.format(totalMoney));
                        StringBuffer stringBuffer = new StringBuffer();
                        for (String data : strOneArgs
                                ) {
                            stringBuffer.append(data).append(",");
                        }
                        output.add(stringBuffer.substring(0, stringBuffer.length() - 1) + System.lineSeparator());
                        isOk=false;
                        break;
                    }

                }
                if (isOk){
                output.add(strOne + System.lineSeparator());}
            }

        }


        createCSV1(output);

    }



    public static void createCSV1(List list) throws Exception {
        SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
        String fileName = sdfDays.format(new Date()) + "明细改3.csv";//文件名称
        String filePath = "D:/xiaoma/change/"; //文件路径
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(filePath + fileName);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
            for (int i = 0; i < list.size(); i++) {
                String rowStr = (String) list.get(i);
                csvWtriter.write(rowStr.trim());
                csvWtriter.newLine();
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
