package com.study.demo.studylittlepsvm.io;

import java.io.InputStream;

/**
 * @program: study-demo
 * @description:
 * @author: lxf
 * @create: 2019-08-23 15:08
 **/
public class NIODemo {


    public static void main(String[] args) {

    }

    private  void nioTest(){
        InputStream inputStream = NIODemo.class.getClassLoader().getResourceAsStream("NIOTest.txt");


    }

}
