package com.example.pg.entity;

import com.example.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "terminals")
@Getter
@Setter
@AllArgsConstructor
public class Terminal extends BaseEntity{

    @Column(name = "term_id")
    private String termId;

    @Column(name = "tranportal_id", nullable = false, unique = true)
    private String tranportalId;

    @Column(name = "inst_id")
    private String instId;

    @Column(name = "mrch_id")
    private String mrchId;

    @Column(name = "status")
    private Integer status;               // 0 = disabled, 1 = active
}
