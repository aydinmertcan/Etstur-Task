package com.etstur.etsturtask.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FileSaveResponseDto {
    private String name;
    private String url;
    private String type;
    private long size;
}
