package com.study.demo.studylittlepsvm.string;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/**
 * @program: study-demo
 * @description:
 * @author: lxf
 * @create: 2019-08-28 12:14
 **/
public class StrToHex {

    public static void main(String[] args) throws Exception {
        int num=3000;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream out=new DataOutputStream(baos);
        out.writeInt(num);
        byte[] bs=baos.toByteArray();
        for(byte b:bs) {
            System.out.print("0x");
            System.out.print(Integer.toString(b>>4&0xF,16).toUpperCase());
            System.out.print(Integer.toString(b&0xF,16).toUpperCase());
            System.out.print(" ");
        }
    }
}
