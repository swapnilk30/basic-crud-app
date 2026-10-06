package com.example.pg.entity;

import com.example.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "institutions")
@Getter
@Setter
@AllArgsConstructor
public class Institution extends BaseEntity {

    private long institutionId;
    private String institutionName;



}
