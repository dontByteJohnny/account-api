package com.agileengine.challenge.model.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class AccountRequestDTO {
    @NotNull(message = "Type is mandatory")
    @Pattern(message = "Type should be credit or debit", regexp = "(?i)credit|debit")
    private String type;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=999999999, fraction=2)
    private BigDecimal amount;
}