package com.etstur.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DoLoginRequestDto {
    @NotNull
    @Size(min = 4, message = "Kullanıcı adı en az 4 karakterden oluşmalıdır (admin)")
    private String username;
}
