package com.tests4.Game_xxx.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "item_type_t")
public class ItemType {
    @Id
    @Column(name = "item_type_id")
    private int itemTypeId;

    @Column(name = "item_type_name")
    private String itemTypeName;
}






