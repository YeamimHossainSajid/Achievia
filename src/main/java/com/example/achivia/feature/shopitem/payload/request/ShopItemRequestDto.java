package com.example.achivia.feature.shopitem.payload.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopItemRequestDto {
    private String slug;
    private String name;
    private String description;
    private String iconUrl;
    private Long price;
    private Boolean isActive;
}
