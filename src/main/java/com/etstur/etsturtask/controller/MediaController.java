package com.etstur.etsturtask.controller;

import com.etstur.etsturtask.dto.response.FileSaveResponseDto;
import com.etstur.etsturtask.dto.response.MessageResponseDto;
import com.etstur.etsturtask.repository.entity.Media;
import com.etstur.etsturtask.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/media")
public class MediaController {

    private final MediaService service;

    @PostMapping(value ="/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageResponseDto> save(@RequestParam MultipartFile file) {
        String message = "";
        String path = "C:\\Users\\user\\Documents\\Etstur\\" + file.getOriginalFilename();
        System.out.println(file.getSize());
        try(OutputStream out = new FileOutputStream(new File(path))) {
            out.write(file.getBytes());
            service.store(file,path);


            message = "File is successfully uploaded " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponseDto.builder().message(message).build());
        } catch (Exception e) {
            message = "File could not be uploaded " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(MessageResponseDto.builder().message(message).build());
        }
    }



}
