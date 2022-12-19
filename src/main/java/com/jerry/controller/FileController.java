package com.jerry.controller;

import com.jerry.entity.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController extends BaseController{
    @PostMapping("/addFile")
    public Result addFile(MultipartFile file){
        Map<String,Object> map = fileService.addFile(file);
        return Result.succ(map);
    }

}
