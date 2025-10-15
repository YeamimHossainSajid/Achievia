package com.example.achivia.feature.purchase.payload.request;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequestDto {
    private UUID userId;
    private UUID shopItemId;
    private Long pricePaid;
}
