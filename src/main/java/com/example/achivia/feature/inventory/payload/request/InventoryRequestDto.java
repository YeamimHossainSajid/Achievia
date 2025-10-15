package com.example.achivia.feature.inventory.payload.request;

import lombok.Data;
import java.util.UUID;

@Data
public class InventoryRequestDto {
    private UUID userId;
    private UUID shopItemId;
    private Integer quantity;
}
