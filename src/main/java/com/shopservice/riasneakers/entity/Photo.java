package com.shopservice.riasneakers.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;

    private String type;
    @OneToOne(mappedBy = "photo", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private Item item;

    @Lob
    private byte[] data;


    public Photo() {
    }

    public Photo(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
