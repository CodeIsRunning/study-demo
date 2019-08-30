package com.study.demo.studylittlepsvm.io;


import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.io.InputStream;


/**
 * @program: study-demo
 * @description:
 * @author: lxf
 * @create: 2019-08-23 15:08
 **/
public class NIODemo {



    public static void main(String[] args) throws Exception{
        nioReadLine();
    }


    private static void nioReadLine()throws Exception{

        List<String> list = Files.readAllLines(new DefaultResourceLoader().getResource("NIOTest.txt").getFile().toPath());
        for (String string: list
             ) {
            System.out.println(string);
        }
    }

    private  static void nioTest()throws Exception{
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        FileInputStream fileInputStream = new FileInputStream(resourceLoader.getResource("NIOTest.txt").getFile());
        FileOutputStream fileOutputStream = new FileOutputStream(resourceLoader.getResource("NIOTest1.txt").getFile());


       /* FileInputStream fileInputStream = new FileInputStream(new File("D:/个人工作/2019/下载/cenos7/CentOS-7-x86_64-DVD-1804.iso"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/个人工作/2019/下载/cenos7/CentOS-7.iso"));
*/

        FileChannel fileChannelIn = fileInputStream.getChannel();
        FileChannel fileChannelOut = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (fileChannelIn.read(byteBuffer)!=-1){
            //切换读模式
            byteBuffer.flip();
            //从缓冲区读到通道
            fileChannelOut.write(byteBuffer);
            //清空缓冲区
            byteBuffer.clear();

        }

    public static void main(String[] args) {

    }

    private  void nioTest(){
        InputStream inputStream = NIODemo.class.getClassLoader().getResourceAsStream("NIOTest.txt");



    }

}
