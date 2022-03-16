package com.etstur.config.openapi;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Etstur Task")
                        .description("•\t Dosya saklama ve listeleme işlemlerimizi yapabilmek için bir API oluşturulması gerekmektedir.\n\n" +
                                "•\t Bu api dosyaları, isimlerini, uzantılarını bir rest endpoint aracılığı ile almalı, dosyayı uygulamanın çalıştığı sunucu üzerinde dosya sistemine kayıt etmeli, dosyanın bulunduğu path, boyutu, ismi, uzantısını ilişkisel bir veritabanında tutmalıdır. \n\n" +
                                "•\t Dosya boyutu en fazla 5mb olmalı, uzantısı 'png, jpeg, jpg, docx, pdf, xlsx' uzantılarından biri olmalı, bu kurallara uymayan dosyalar kayıt edilmemeli ve sistem hata mesajı dönmelidir.\n\n" +
                                "•\t Gerektiğinde yine bir rest endpoint aracılığı ile dosyaların tüm bilgileri dönülmelidir.\n\n" +
                                "•\t Dosya içeriği bir rest endpoint aracılığı ile byte arrray olarak dönülmelidir.\n\n")
                        .termsOfService("terms")
                        .contact(new Contact().email("ayd.mertcan@gmail.com"))
                        .license(new License().name("Github Linki").url("https://github.com/aydinmertcan/Etstur-Task.git"))
                        .version("1.0")
                );
    }
}
