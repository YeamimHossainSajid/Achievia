package com.sajid.Achievia.feature.items.service;

import com.sajid.Achievia.feature.items.payload.request.ItemRequestDto;
import com.sajid.Achievia.feature.items.payload.response.ItemResponseDto;

import java.util.List;

public interface ItemService {

    public List<ItemResponseDto> getItems();
    public ItemResponseDto getItem(Long id);
    public void createItem(ItemRequestDto requestDto);
    public void updateItem(Long id, ItemRequestDto requestDto);
    public void deleteItem(Long id);

}
