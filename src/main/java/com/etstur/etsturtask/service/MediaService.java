package com.etstur.etsturtask.service;

import com.etstur.etsturtask.repository.IMediaRepository;
import com.etstur.etsturtask.repository.entity.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final IMediaRepository repository;

    public void store(MultipartFile file, String path) throws IOException {
        String simplifiedType = file.getContentType().substring(file.getContentType().indexOf("/") + 1, file.getContentType().length() );
        repository.save(Media.builder()
                        .name(file.getOriginalFilename())
                        .type(simplifiedType)
                        .photo(file.getBytes())
                        .size(file.getSize())
                        .path(path)
                .build());
    }

}
