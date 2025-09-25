package com.sajid.Achievia.feature.items.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {

    private String itemName;
    private String itemType;
    private String rarity;

}
