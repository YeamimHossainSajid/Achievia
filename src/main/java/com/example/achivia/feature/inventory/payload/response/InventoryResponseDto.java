package com.example.achivia.feature.inventory.payload.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class InventoryResponseDto {
    private UUID id;
    private UUID userId;
    private UUID shopItemId;
    private Integer quantity;
    private LocalDateTime acquiredAt;
}
