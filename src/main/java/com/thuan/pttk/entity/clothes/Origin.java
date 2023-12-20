package com.thuan.pttk.entity.clothes;

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
@Table(name = "tbl_origin")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "origin_id")
    private long id;
    private String nation;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "origin")
    @JsonIgnore
    private List<Clothes> clothesList;
}
