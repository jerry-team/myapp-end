package com.jerry.entity;

import lombok.Data;

@Data
public class FileEntity {
    private String fileOriginalName;
    private String fileName;
    private String fileUrl;
    private Long fileSize;
}
