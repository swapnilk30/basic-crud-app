package com.example.merchant.entity;

import com.example.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "merchants")
@Getter
@Setter
@AllArgsConstructor
public class Merchant extends BaseEntity {

    private int merchantId;
    private String merchantName;
}
