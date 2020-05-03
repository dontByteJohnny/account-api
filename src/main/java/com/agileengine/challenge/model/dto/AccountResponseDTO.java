package com.agileengine.challenge.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Calendar;

@Data
public class AccountResponseDTO {
    private Long id;
    private BigDecimal amount;
    private String type;
    private Calendar effectiveDate;
}
