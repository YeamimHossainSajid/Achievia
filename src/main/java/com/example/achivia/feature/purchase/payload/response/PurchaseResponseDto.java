package com.example.achivia.feature.purchase.payload.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponseDto {
    private UUID id;
    private UUID userId;
    private String userName;
    private UUID shopItemId;
    private String shopItemName;
    private Long pricePaid;
    private LocalDateTime createdAt;
}
