package com.example.achivia.feature.wallettransaction.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletTransactionRequestDto {
    private UUID userId;
    private Long amount;
    private String reason;
    private UUID referenceId;
    private String metadata;
}
