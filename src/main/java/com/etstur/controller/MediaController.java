package com.etstur.controller;

import com.etstur.dto.response.MessageResponseDto;
import com.etstur.repository.entity.Media;
import com.etstur.service.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/media")
@SecurityRequirement(name = "bearerAuth")
public class MediaController {

    private final MediaService service;

    @PostMapping(value ="/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "açıklama")
    public ResponseEntity<MessageResponseDto> uploadFile(@RequestParam MultipartFile file) {
        String message = "",
                path = "C:\\Users\\user\\Documents\\Etstur\\" + file.getOriginalFilename();
        try(OutputStream out = new FileOutputStream(new File(path))) {

            if(isValidExtension(file)) {
                out.write(file.getBytes());
                service.store(file, path);
                message = "File is successfully uploaded " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDto(message));
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            message = "File could not be uploaded " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponseDto(message));
        }
    }

    private boolean isValidExtension(MultipartFile file) {
        List<String> extensions = Arrays.asList("png", "jpeg", "jpg", "docx", "pdf", "xlsx");
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return extensions.contains(extension);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Media>> findAllFiles() {
        return ResponseEntity.ok(service.findAllFiles());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageResponseDto> updateFile(@PathVariable(value = "id") long id, @RequestParam MultipartFile file){
        String path = "C:\\Users\\user\\Documents\\Etstur\\" + file.getOriginalFilename();
        String message = "";
        try {
            service.update(file, path, id);
            message = "File is successfully updated " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseDto(message));
        } catch (IOException e) {
            message = "File could not be updated " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponseDto(message));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable(value = "id") long id) {
        Media media = service.findById(id).get();
        File file = new File(media.getPath());
        file.delete();
        service.delete(media);
        return ResponseEntity.ok().build();
    }



}
