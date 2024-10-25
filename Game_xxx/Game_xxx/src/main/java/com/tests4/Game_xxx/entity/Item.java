package com.tests4.Game_xxx.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "item_t")
public class Item {
    @Id
    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "item_type_id", referencedColumnName = "item_type_id")
    private ItemType itemType;

    @Column(name = "price")
    private BigDecimal price;

}