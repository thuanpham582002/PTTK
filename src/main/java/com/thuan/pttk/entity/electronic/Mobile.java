package com.thuan.pttk.entity.electronic;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_mobile")
public class Mobile extends Electronic {
    private String chip;
    private String camera;
    private String accessory;
    private String power;
    private String ram;
    private String rom;
    private String resolution;
}