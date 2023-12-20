package com.thuan.pttk.entity.cutstomer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long id;
    @Column(name = "number_house")
    private int numberHouse;
    private String street;
    private String district;
    private String city;
    private String nation;
}
