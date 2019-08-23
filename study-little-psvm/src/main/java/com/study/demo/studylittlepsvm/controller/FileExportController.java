package com.study.demo.studylittlepsvm.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBodyReturnValueHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @program: study-demo
 * @description:
 * @author: lxf
 * @create: 2019-08-23 16:24
 **/
@RestController
@RequestMapping("file")
public class FileExportController {


    @GetMapping("export")
    public ResponseEntity<StreamingResponseBody> fileEport() throws Exception {

        ClassPathResource classPathResource = new ClassPathResource("application.properties");
        InputStream inputStream = classPathResource.getInputStream();
        StreamingResponseBody stream = outputStream ->
                readAndWrite(inputStream, outputStream);

        String zipFileName = "ooxx.properties";
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + zipFileName)

                .contentType(MediaType.parseMediaType("application/zip"))
                .body(stream);

    }

    /**
     * 记一次奇葩要求 批量上传返回压缩包
     * @param file
     * @return
     * @throws Exception
     */

    @PostMapping("export1")
    public ResponseEntity<StreamingResponseBody> fileEport1(MultipartFile[] file) throws Exception {

        byte[] buf = new byte[2048];
        StreamingResponseBody stream = new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                ZipOutputStream zos = new ZipOutputStream(outputStream);
                for (int i = 0; i <= file.length - 1; i++) {
                    zos.putNextEntry(new ZipEntry(file[i].getOriginalFilename()));
                    int len;
                    InputStream inputStream = file[i].getInputStream();
                    while ((len = inputStream.read(buf)) != -1) {
                        zos.write(buf, 0, len);
                    }
                    zos.closeEntry();
                }
                zos.close();
            }
        };
        String zipFileName = "test.zip";
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + zipFileName)
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(stream);

    }


    @GetMapping("export2")
    public StreamingResponseBody fileExport2() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                ClassPathResource classPathResource = new ClassPathResource("application.properties");
                readAndWrite(classPathResource.getInputStream(), outputStream);
            }
        };
    }

    private void readAndWrite(final InputStream is, OutputStream os)
            throws IOException {
        byte[] data = new byte[2048];
        int read;
        while ((read = is.read(data)) >= 0) {
            os.write(data, 0, read);
        }
        os.flush();

    }


    private void readAndWrite2(final SequenceInputStream is, OutputStream os)
            throws IOException {

        byte[] data = new byte[2048];
        int read;
        while ((read = is.read(data)) >= 0) {
            os.write(data, 0, read);
        }
        os.flush();
        is.close();
        os.close();
    }

}
