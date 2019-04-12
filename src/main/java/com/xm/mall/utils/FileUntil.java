package com.xm.mall.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * 文件工具类
 *
 * @author huyan
 * @date 2019-04-11 15:51
 */
public class FileUntil {

    public static final char EXTENSION_SEPARATOR = '.';

    public static final String HORIZONTAL_SEPARATOR = "-";

    public static final String EMPTY_SEPARATOR = "";

    /**
     * 根据该文件的名称，获取后缀
     * @param fileName 文件名
     * @return 后缀名
     */
    public static String getExtension(String fileName){
        if (fileName==null){
            return null;
        }
//        获取下标索引
        int index = indexOfExtension(fileName);
        if (index == -1){
            return "";
        }else {
            return fileName.substring(index+1);
        }
    }

    /**
     * 根据文件名获取点的下标位置
     * @param fileName 文件名
     * @return 下标索引
     */
    public static int indexOfExtension(String fileName){
        if (fileName == null){
            return -1;
        }
        return fileName.lastIndexOf(EXTENSION_SEPARATOR);
    }

    /**
     * 获取UUID生成的名称
     * @return
     */
    public static String getName(){
        return UUID.randomUUID().toString().replace(HORIZONTAL_SEPARATOR, EMPTY_SEPARATOR);
    }


    /**
     * 文件上传，文件名的处理
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    public static Boolean upload(MultipartFile file, HttpServletRequest request) throws IOException {
        //获取真实地址
        String realPath = getRealPath(request);

        String newPath = getNewPath(realPath, file.getOriginalFilename());

        return upload(file.getInputStream(),newPath);
    }


    /**
     *  返回新的拼接好的地址
     * @param realPath 真实地址
     * @param oldFileName 旧文件名
     * @return 新地址
     */
    public static String getNewPath(String realPath,String oldFileName){
        //设置上传后的文件名称
        String fileName = FileUntil.getName();
        //获取上传的文件名
        String filename = oldFileName;
        //获取上传文件的后缀
        String extension = FileUntil.getExtension(filename);
        //将新的文件名进行拼接
        realPath = realPath + fileName + FileUntil.EXTENSION_SEPARATOR + extension;

//        File.separator == "/"
//        realPath = realPath + File.separator + fileName + FileUntil.EXTENSION_SEPARATOR + extension;

        System.out.println("new realPath: "+realPath);

        return realPath;
    }


    public static String getRealPath(HttpServletRequest request) {
        // 设置存储文件的地址
        String realPath = request.getSession().getServletContext().getRealPath("/upload/");
        System.out.println("request . realPath： "+realPath);
        File filePath = new File(realPath);
        //如果存储目录不存在,则创建
        if (!filePath.exists()){
            //创建文件目录
            filePath.mkdirs();
        }
        return realPath;
    }

    /**
     * 文件上传，文件的IO
     * @param inputStream
     * @param realPath
     * @return
     * @throws IOException
     */
    public static Boolean upload(InputStream inputStream,String realPath){
        try {

        OutputStream outputStream = new FileOutputStream(realPath);
            stream(inputStream, outputStream);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void stream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes))!= -1){
            outputStream.write(bytes,0,len);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }


    /**
     * 上传多个文件
     * @param request
     * @return
     * @throws IOException
     */
    public static Boolean uploadMulti(HttpServletRequest request) throws IOException {

        //获取绝对地址
        String realPath = getRealPath(request);

        //创建多部分解析器
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());
        //判断是否是文件上传
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取上传的所有文件名
            Iterator<String> fileNames = multiRequest.getFileNames();
            while (fileNames.hasNext()){

                //获取该字段中的所有的文件
                List<MultipartFile> files = multiRequest.getFiles(fileNames.next());
                for (MultipartFile multipartFile : files) {
                    //FileUntil.upload(file,request);

                    //上传
                    multipartFile.transferTo(new File(getNewPath(realPath,multipartFile.getOriginalFilename())));
                }
                return true;
            }
        }
        return false;
    }



//    文件下载

    public static Boolean downLoad(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String realPaht = getRealPath(request);

        //获取需下载的文件
        File file = new File(realPaht + File.separator + fileName);

        //设置强制下载打不开
        response.setContentType("application/force-download");
        //文件名设置编码格式
        new String(fileName.getBytes("UTF-8"),"ISO-8859-1");

        //设置下载的文件名
        response.setHeader("Content-Disposition","attachment;fileName="+fileName);

        //下载
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();

        if (null == inputStream){
            return false;
        }
        stream(inputStream,outputStream);
        return true;

    }



}
