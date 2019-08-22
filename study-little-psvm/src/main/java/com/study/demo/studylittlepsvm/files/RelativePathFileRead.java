package com.study.demo.studylittlepsvm.files;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * @program: study-demo
 * @description: 部分相对路径文件的读取
 * @author: lxf
 * @create: 2019-08-22 15:30
 **/
public class RelativePathFileRead {

    public static void main(String[] args)throws Exception {
        FileRead();
    }


    /**
     * 相对路径读取程序文件
     */
    public static void FileRead()throws Exception{


        //以下写法从当前类所在package下查找文件
        //InputStream  inputStream= RelativePathFileRead.class.getResourceAsStream("application.properties");

        //以下写法,从class根目录查找文件
        InputStream  inputStream= RelativePathFileRead.class.getClassLoader().getResourceAsStream("application.properties");


        //spring boot  打包后不能使用file读取文件
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:test-environment.yml");
        System.out.println("文件名称: " + resource.getFilename());
        InputStream inputStreamJar = resource.getInputStream();


        //读取yaml 需要引入相应的jar包 snakeyaml
        Yaml yaml = new Yaml();
        Map<String, Map<String, String>> result = yaml.load(inputStreamJar);
        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();


        //读取properties
        Properties properties  = new Properties();
        properties.load(inputStream);

        String string;
        while ((string = bufferedInputStream.readLine())!=null){
            stringBuffer.append(string);
        }
        inputStream.close();
        bufferedInputStream.close();
        System.out.println(stringBuffer);
    }

}
