package com.example.achivia.feature.userwallet.payload.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWalletResponseDto {
    private UUID userId;
    private Long balance;
    private LocalDateTime updatedAt;
}
