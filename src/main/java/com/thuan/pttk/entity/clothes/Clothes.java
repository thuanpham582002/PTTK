package com.thuan.pttk.entity.clothes;

import com.thuan.pttk.entity.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_clothes")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Clothes extends Product {
    private String barcode;
    private String type;
    private int yearOfManufacture;
    private String size;
    private String color;
    private String material;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id", referencedColumnName = "origin_id", foreignKey = @ForeignKey(name = "fk_clothes_origin_id"))
    private Origin origin;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "trademark_id", referencedColumnName = "trademark_id", foreignKey = @ForeignKey(name = "fk_clothes_trademark_id"))
    private Trademark trademark;
}
