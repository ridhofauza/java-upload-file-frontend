/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileupload.testingfrontend.service;

import com.fileupload.testingfrontend.model.request.FileMeta;
import com.fileupload.testingfrontend.model.request.FileSiswaRequest;
import com.fileupload.testingfrontend.model.response.FileSiswaResponse;
import java.io.File;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author user
 */
@Service
public class FileService {
    private RestTemplate restTemplate;
    
    @Value("${server.baseUrl}/file")
    private String url;

    public FileService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public Object fileUpload(FileSiswaRequest fileSiswaRequest) throws IOException {;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("description", fileSiswaRequest.getDescription());
        MultipartFile fileTugas = fileSiswaRequest.getFileTugas();
        FileMeta fileMeta = new FileMeta(fileTugas.getBytes(), fileTugas.getContentType(), fileTugas.getOriginalFilename(), fileTugas.getSize());
        body.add("fileMeta", fileMeta);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate
            .exchange(url, HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<FileSiswaResponse>(){}).getBody();
    }
    
//    public Resource downloadFile() {
//    
//    }
    
}
