package com.example.achivia.feature.userwallet.payload.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWalletRequestDto {
    private Long userId;
    private Long balance;
}
