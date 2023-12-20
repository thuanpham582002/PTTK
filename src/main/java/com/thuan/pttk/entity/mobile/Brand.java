package com.thuan.pttk.entity.mobile;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_brand")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private long id;
    private String name;
    private String address;
    private String company;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
    @JsonIgnore
    private List<MobilePhone> brandList;
}
