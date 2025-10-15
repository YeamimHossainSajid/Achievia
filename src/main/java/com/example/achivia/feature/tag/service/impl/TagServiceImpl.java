package com.example.achivia.feature.tag.service.impl;

import com.example.achivia.feature.tag.entity.Tag;
import com.example.achivia.feature.tag.payload.request.TagRequestDto;
import com.example.achivia.feature.tag.payload.response.TagResponseDto;
import com.example.achivia.feature.tag.repository.TagRepository;
import com.example.achivia.feature.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    @Override
    public TagResponseDto createTag(TagRequestDto requestDto) {
        UUID id = UUID.randomUUID();
        repository.insertTag(id, requestDto.getName());
        Tag tag = repository.findByIdNative(id).orElseThrow(() -> new RuntimeException("Tag creation failed"));
        return mapToDto(tag);
    }

    @Override
    public TagResponseDto getTagById(UUID id) {
        Tag tag = repository.findByIdNative(id).orElseThrow(() -> new RuntimeException("Tag not found"));
        return mapToDto(tag);
    }

    @Override
    public List<TagResponseDto> getAllTags() {
        List<Tag> tags = repository.findAllNative();
        return tags.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private TagResponseDto mapToDto(Tag tag) {
        return TagResponseDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}
