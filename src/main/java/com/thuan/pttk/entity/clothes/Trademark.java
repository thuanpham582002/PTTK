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
@Table(name = "tbl_trademark")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Trademark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trademark_id")
    private long id;
    private String name;
    private String address;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trademark")
    @JsonIgnore
    private List<Clothes> clothesList;
}
