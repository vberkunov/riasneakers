package com.shopservice.riasneakers.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name= "brand_table")
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @OneToOne(mappedBy = "brand", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private Item item;
}
