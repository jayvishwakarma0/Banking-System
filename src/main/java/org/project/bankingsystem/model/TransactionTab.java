package org.project.bankingsystem.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @NotNull(message = "Account cannot be null")
    private Account account;

    @Min(value = 0, message = "Transaction amount must be positive")
    @Column(nullable = false)
    private Double amount;

    @NotBlank(message = "Transaction type cannot be blank")
    private String transactionType;  // "DEPOSIT", "WITHDRAWAL", "TRANSFER"

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date transactionDate = new Date(); // Automatically sets the current date and time
}

