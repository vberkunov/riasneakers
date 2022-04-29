package com.shopservice.riasneakers.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthResponse {
    private String token;
    private String error;

}
