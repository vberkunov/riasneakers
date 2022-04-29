package com.shopservice.riasneakers.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterResponse {
    private String status;
    private String message;
}
