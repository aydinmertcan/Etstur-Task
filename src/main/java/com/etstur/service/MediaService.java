package com.etstur.service;

import com.etstur.repository.IMediaRepository;
import com.etstur.repository.entity.Media;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final IMediaRepository repository;

    public void store(MultipartFile file, String path) throws IOException {
        repository.save(Media.builder()
                        .name(file.getOriginalFilename())
                        .type(FilenameUtils.getExtension(file.getOriginalFilename()))
                        .photo(file.getBytes())
                        .size(file.getSize())
                        .path(path)
                .build());
    }

}
