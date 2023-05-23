
package com.example.moneymoney.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "asset_type")

public class AssetType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_type_id")
    private Long assetTypeId;

    @Column(name = "asset_type_name", nullable = false)
    private String assetTypeName;

    @Column(name = "description")
    private String description;


}
