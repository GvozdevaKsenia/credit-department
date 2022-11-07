package com.gvozdeva.creditdepartment2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credit {

    private Long id;
    private Bank bank;
    private Client client;
    private NonNaturalClient nonNaturalClient;
    private Guarantor guarantor;
    private Consultant consultant;
    private CreditType type;
    private BigDecimal sum;
    private BigDecimal frontMoney;
    private Integer interestRate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Currency currency;
    private CreditStatus status;
}
