package com.thuan.pttk.entity.mobile;

import com.thuan.pttk.entity.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_mobile")
public class MobilePhone extends Product {
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id", foreignKey = @ForeignKey(name = "fk_mobile_brand_id"))
    private Brand brand;
    private String color;
    private String model;
    private String warranty;
    private Float screenSize;
}
