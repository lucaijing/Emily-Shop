package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping(value = "/Picture",produces = {"image/jpeg"})
public class PictureController {

    @PostMapping("/getP")
    public void setP(@RequestParam(value="file",required=false) File file){
        String filePath="E:\\zzzpic\\";

        System.out.println(filePath);
        File one=new File("E:\\zzzpic\\one.txt");
        System.out.println(file.toString());
    }
}
