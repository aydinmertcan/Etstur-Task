package com.etstur.controller;

import com.etstur.dto.response.MessageResponseDto;
import com.etstur.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

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

            if(isValidExtension(file)) {
                out.write(file.getBytes());
                service.store(file,path);
                message = "File is successfully uploaded " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(MessageResponseDto.builder().message(message).build());
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            message = "File could not be uploaded " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(MessageResponseDto.builder().message(message).build());
        }
    }

    private boolean isValidExtension(MultipartFile file) {
        List<String> extensions = Arrays.asList("png", "jpeg", "jpg", "docx", "pdf", "xlsx");
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return extensions.contains(extension);
    }


}
