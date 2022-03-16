package com.etstur.dto.response;

import com.etstur.repository.entity.Media;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FileInfoResponseDto {
    private String name;
    private String type;
    private String path;
    private long size;

    public FileInfoResponseDto(Media media) {
        this.name = media.getName();
        this.type = media.getType();
        this.path = media.getPath();
        this.size = media.getSize();
    }
}
