package com.sajid.Achievia.feature.items.service.impl;

import com.sajid.Achievia.feature.items.entity.Item;
import com.sajid.Achievia.feature.items.payload.request.ItemRequestDto;
import com.sajid.Achievia.feature.items.payload.response.ItemResponseDto;
import com.sajid.Achievia.feature.items.repository.ItemRepository;
import com.sajid.Achievia.feature.items.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private Item convertToEntity(ItemRequestDto itemRequestDto) {
        Item item = new Item();
        item.setItemType(itemRequestDto.getItemType());
        item.setItemName(itemRequestDto.getItemName());
        item.setRarity(itemRequestDto.getRarity());
        return item;
    }

    private ItemResponseDto convertToDto(Item item) {
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        itemResponseDto.setId(item.getId());
        itemResponseDto.setItemType(item.getItemType());
        itemResponseDto.setItemName(item.getItemName());
        itemResponseDto.setRarity(item.getRarity());
        return itemResponseDto;
    }

    @Override
    public List<ItemResponseDto> getItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemResponseDto getItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
        return convertToDto(item);
    }

    @Override
    public void createItem(ItemRequestDto requestDto) {
        Item item = convertToEntity(requestDto);
        itemRepository.save(item);
    }

    @Override
    public void updateItem(Long id, ItemRequestDto requestDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        item.setItemType(requestDto.getItemType());
        item.setItemName(requestDto.getItemName());
        item.setRarity(requestDto.getRarity());

        itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
        itemRepository.delete(item);
    }
}

