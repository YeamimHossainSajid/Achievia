package com.example.achivia.feature.shopitem.payload.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopItemResponseDto {
    private UUID id;
    private String slug;
    private String name;
    private String description;
    private String iconUrl;
    private Long price;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
