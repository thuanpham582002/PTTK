package com.thuan.pttk.entity.shoes;

import jakarta.persistence.Column;
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
@Table(name = "tbl_for_woman")
public class ForWoman extends Shoes{
    private int size;

    @Column(name = "sole_height")
    private int soleHeight;
}
