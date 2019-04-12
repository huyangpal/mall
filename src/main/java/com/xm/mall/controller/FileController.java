package com.xm.mall.controller;

import com.xm.mall.utils.FileUntil;
import com.xm.mall.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huyan
 * @date 2019-04-11 15:39
 */
@Controller
public class FileController {

//    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    @GetMapping("/upload")
    public String toUpload(){
        return "upload";
    }


//    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @PostMapping("/upload")
    @ResponseBody
    public Result uploadOne(MultipartFile file, HttpServletRequest request) throws IOException {
        //上传
//        Boolean upload = FileUntil.upload(file, request);
//        if (!upload){
//            return Result.error();
//        }
        //多上传
        Boolean aBoolean = FileUntil.uploadMulti(request);
        if (!aBoolean){
            return Result.error();
        }

        return Result.success();
    }

    @GetMapping("/download")
    public String toDownload(){
        return "download";
    }


    @PostMapping("/download")
    @ResponseBody
    public Result downloadOne(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //下载
        Boolean downLoad = FileUntil.downLoad(fileName, request, response);

        if (!downLoad){
            return Result.error();
        }

        return Result.success();
    }





}
