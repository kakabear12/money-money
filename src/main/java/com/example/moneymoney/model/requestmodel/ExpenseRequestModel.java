package com.example.moneymoney.model.requestmodel;

import com.example.moneymoney.entity.Asset;
import com.example.moneymoney.entity.ExpenseCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExpenseRequestModel {
    @Schema(description = "Nhập loại chi tiêu",example = "Ăn uống")
    private String expenseCategoryName;

    @Schema(description = "Nhập ngày giờ chi tiêu",example = "2023-05-28 10:30:00")
    private Timestamp date;
    @Schema(description = "Nhập số tiền đã chi tiêu",example = "100000")
    private BigDecimal amount;

    @Schema(description = "Nhập mô tả",example = "Mua bánh mì")

    private String description;

    @Schema(description = "Nhập số loại tiền",example = "Tiền mặt trong túi")
    private String assetName;

}
