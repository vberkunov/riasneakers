package com.shopservice.riasneakers.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name= "item_table")
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private double priceBeg;
    private String description;
    @OneToOne(fetch= FetchType.LAZY)
    private Brand brand;
    @OneToOne(fetch= FetchType.LAZY)
    private Photo photo;
    private double priceFinal;

    public Item(String title, double priceBeg, String description, Brand brand, Photo photo, double priceFinal) {
        this.title = title;
        this.priceBeg = priceBeg;
        this.description = description;
        this.brand = brand;
        this.photo = photo;
        this.priceFinal = priceFinal;
    }
}
