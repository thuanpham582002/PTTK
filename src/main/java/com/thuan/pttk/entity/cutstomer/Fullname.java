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
@Table(name = "tbl_fullname")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Fullname {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fullname_id")
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;

    public String getName() {
        return this.firstName + " " + this.middleName + " " + this.lastName;
    }
}
