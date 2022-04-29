package com.shopservice.riasneakers.dto;

import com.shopservice.riasneakers.entity.Brand;
import com.shopservice.riasneakers.entity.Photo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ItemDto {
    private String title;
    private double priceBeg;
    private String description;
    private Brand brand;
    private Photo photo;
    private double priceFinal;
}

