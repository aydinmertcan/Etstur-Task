package com.etstur.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tbl_media")
public class Media implements Serializable {
    /*
    *  Bu api dosyaları, isimlerini, uzantılarını bir rest endpoint aracılığı ile almalı,
    *  dosyayı uygulamanın çalıştığı sunucu üzerinde dosya sistemine kayıt etmeli,
    *  dosyanın bulunduğu path, boyutu, ismi, uzantısını ilişkisel bir veritabanında tutmalıdır.
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long size;
    private String type;
    private String path;
    @Lob
    private byte[] photo;
}
