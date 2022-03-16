package com.etstur.service;

import com.etstur.repository.IMediaRepository;
import com.etstur.repository.entity.Media;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final IMediaRepository repository;

    public Media store(MultipartFile file, String path) throws IOException {
        return repository.save(Media.builder()
                        .name(file.getOriginalFilename())
                        .type(FilenameUtils.getExtension(file.getOriginalFilename()))
                        .file(file.getBytes())
                        .size(file.getSize())
                        .path(path)
                .build());
    }

    public Media update(MultipartFile file, String path, long id) throws IOException {
        return repository.save(Media.builder()
                        .id(id)
                        .name(file.getOriginalFilename())
                        .type(FilenameUtils.getExtension(file.getOriginalFilename()))
                        .file(file.getBytes())
                        .size(file.getSize())
                        .path(path)
                .build());
    }

    public List<Media> findAllFiles() {
        return repository.findAll();
    }

    public Optional<Media> findById(Long id) {
        return repository.findById(id);
    }


    public void delete(Media media) {
        repository.delete(media);
    }


}
