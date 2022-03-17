package com.etstur.controller;

import com.etstur.dto.response.FileInfoResponseDto;
import com.etstur.dto.response.MessageResponseDto;
import com.etstur.exception.ErrorMessage;
import com.etstur.repository.entity.Media;
import com.etstur.service.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/media")
@SecurityRequirement(name = "bearerAuth")
public class MediaController {

    private final MediaService service;

    @Value("${SAVE_DIRECTORY}")
    public String PATH;


    @GetMapping("/findall")
    @Operation(summary = "Localde bulunan bütün dosyaları getirir.")
    public ResponseEntity<List<FileInfoResponseDto>> findAllFiles() {
        return ResponseEntity.ok().body(service.findAllFiles().stream().map(FileInfoResponseDto::new).collect(Collectors.toList()));
    }
    @GetMapping("/findbyid")
    @Operation(summary = "Verilen id'ye göre dosyayı getirir.")
    public ResponseEntity<FileInfoResponseDto> findFile(@RequestParam long id) {
        Media media = service.findById(id).get();
        return ResponseEntity.ok().body(new FileInfoResponseDto(media));
    }

    @PostMapping(value ="/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Eklenen dosyanın local'de ve database'de kayıt işlemini gerçekleştirir.")
    public ResponseEntity<FileInfoResponseDto> uploadFile(@RequestBody MultipartFile file) {
        String filePath = PATH  + file.getOriginalFilename();
        try(OutputStream out = new FileOutputStream(new File(filePath))) {

            if(isValidExtension(file)) {
                out.write(file.getBytes());
                Media media = service.store(file, filePath);
                return ResponseEntity.status(HttpStatus.OK).body(new FileInfoResponseDto(media));
            } else {
                throw new Exception("Error occured at " + filePath);
            }

        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException( "File could not be uploaded " + file.getOriginalFilename());
        }
    }


    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Dosyanın local'de ve database'de güncelleme işlemini gerçekleştirir.")
    public ResponseEntity<FileInfoResponseDto> updateFile(@PathVariable(value = "id") long id, @RequestParam MultipartFile file) throws IOException {
        String filePath = PATH + file.getOriginalFilename();
        Media media = service.findById(id).get();
        File existingFile = new File(media.getPath());
        existingFile.delete();
        try(OutputStream out = new FileOutputStream(new File(filePath))) {
            if(isValidExtension(file)) {
                out.write(file.getBytes());
                Media newMedia = service.update(file, filePath, id);
                return ResponseEntity.status(HttpStatus.OK).body(new FileInfoResponseDto(media));
            } else {
                throw new Exception("Error occured at " + filePath);
            }
        } catch (Exception e) {
            throw new RuntimeException("File could not be updated " + file.getOriginalFilename());
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Verilen id'ye göre dosyayı silme işlemini yapar.")
    public ResponseEntity<String> deleteFile(@PathVariable(value = "id") long id) throws IOException {
        Media media = service.findById(id).get();
        File file = new File(media.getPath());
        file.delete();
        service.delete(media);
        return ResponseEntity.ok().body("File is successfully deleted: " + media.getName());
    }

    private boolean isValidExtension(MultipartFile file) {
        List<String> extensions = Arrays.asList("png", "jpeg", "jpg", "docx", "pdf", "xlsx");
        String extension = FilenameUtils.getExtension(file.getOriginalFilename().toLowerCase());
        return extensions.contains(extension);
    }



}
