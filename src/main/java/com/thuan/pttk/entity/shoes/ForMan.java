package com.thuan.pttk.entity.shoes;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Table(name = "tbl_for_man")
public class ForMan extends Shoes {
    private int size;
}
