package com.example.achivia.feature.userwallet.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWalletRequestDto {
    private UUID userId;
    private Long balance;
}
