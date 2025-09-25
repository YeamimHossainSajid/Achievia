package com.sajid.Achievia.feature.items.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponseDto {

    private Long id;
    private String itemName;
    private String itemType;
    private String rarity;

}
