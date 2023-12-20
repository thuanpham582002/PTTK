package com.thuan.pttk.entity.shoes;

import com.thuan.pttk.entity.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Setter
@Getter
@SuperBuilder
@Table(name = "tbl_shoes")
@AllArgsConstructor
@NoArgsConstructor
public class Shoes extends Product{
    private String type;
    private String brand;
    private String color;
    private String origin;
}
