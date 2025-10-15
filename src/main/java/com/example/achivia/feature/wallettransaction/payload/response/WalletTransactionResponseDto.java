package com.example.achivia.feature.wallettransaction.payload.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletTransactionResponseDto {
    private UUID id;
    private UUID userId;
    private Long amount;
    private String reason;
    private UUID referenceId;
    private String metadata;
    private LocalDateTime createdAt;
}
