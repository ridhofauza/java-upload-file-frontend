/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileupload.testingfrontend.controller;

import com.fileupload.testingfrontend.model.request.FileSiswaRequest;
import com.fileupload.testingfrontend.service.FileService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author user
 */
@Controller
@AllArgsConstructor
@RequestMapping("/file")
public class FileSiswaController {
    
    private FileService fileService;
    
    @GetMapping
    public String index(FileSiswaRequest fileSiswaRequest) {
        return "index";
    }
    
    @PostMapping
    public Object uploadFile(FileSiswaRequest fileSiswaRequest) throws IOException {
        fileService.fileUpload(fileSiswaRequest);
        return "redirect:/file";
    }
    
}
