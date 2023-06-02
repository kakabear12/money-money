package com.example.moneymoney.model.requestmodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeRequestModel {

    @Schema(description = "Nhập tên loại thu nhập",example = "Tiền lương")
    private String incomeCategoryName;
    @Schema(description = "Nhập ngày giờ thu nhập",example = "2023-05-28 10:30:00")
    private Timestamp date;
    @Schema(description = "Nhập số tiền đã thu",example = "100000000")
    private BigDecimal amount;

    @Schema(description = "Nhập mô tả",example = "Nhận lương tháng 5")
    private String description;

    @Schema(description = "Nhập số loại tiền",example = "Tiền mặt trong túi")
    private String assetName;

}
