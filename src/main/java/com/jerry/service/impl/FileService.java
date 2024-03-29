package com.jerry.service.impl;

import com.jerry.entity.FileEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class FileService {

    public Map<String, Object> addFile(MultipartFile file) {

        FileEntity fileEntity = new FileEntity();
        //把文件保存的方法抽离出来了
        Map<String, Object> map = uploadFile(file);
        String fileOriginalName = file.getOriginalFilename();
        String fileName = (String) map.get("fileName");
        String fileUrl = (String) map.get("fileUrl");
        Long fileSize = file.getSize();

        fileEntity.setFileOriginalName(fileOriginalName);
        fileEntity.setFileName(fileName);
        fileEntity.setFileUrl(fileUrl);
        fileEntity.setFileSize(fileSize);

//        baseMapper.insert(fileEntity);
//        map.put("fileId",fileEntity.getId());
        map.put("fileId",1);
        return map;
    }

    // 文件上传，保存到本地,如果需要直接修改本方法进行文件上传
    private Map<String,Object> uploadFile(MultipartFile file){
        // 这个map用于返回imageUrl，imageId之类的数据
        Map<String,Object> map = new HashMap<>();
        try {
            // 1）设置文件的目录
            //按照月份进行分类：
//            Calendar calendar = Calendar.getInstance();
//            calendar.get(Calendar.YEAR);
//            String yearMonth = calendar.get(Calendar.YEAR) + "年" +(calendar.get(Calendar.MONTH) + 1)+"月";
//            String filePath = "src/main/resources/static/commodityImages" ; //项目路径+年月份
            File filePath = new File("src/main/resources/static/commodityImages");
            if (!filePath.exists()){
                boolean mkdirs = filePath.mkdirs();//创建文件目录，可以创建多层目录
            }
            // 创建文件目录
            String realPath = filePath.getCanonicalPath();  //上传文件保存地址：realPath = "E:\\图片保存\\upload\\2022年5月"

            //2）设置文件名字
            String fileRealName=file.getOriginalFilename();
//            String suffix = fileRealName.substring(fileRealName.lastIndexOf(".") + 1);  //后缀  jpg  png
            //解决文件名字问题：使用uuid的方式;
//            String fileName = "file-"+ UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
            String fileName = fileRealName;//这里就使用真实文件名字
            //3）进行文件保存，通过CommonsMultipartFile的方法直接写文件
            file.transferTo(new File(realPath +"/"+ fileName));

            //4）返回图片的名字，url，如果只是保存文件，下面的语句可以不写
            String fileUrl = "http://localhost:8001/commodityImages" + "/" +fileName;
            map.put("fileName",fileName);
            map.put("fileUrl",fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
