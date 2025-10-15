package com.example.achivia.feature.tag.service;
import com.example.achivia.feature.tag.payload.request.TagRequestDto;
import com.example.achivia.feature.tag.payload.response.TagResponseDto;

import java.util.List;
import java.util.UUID;

public interface TagService {
    TagResponseDto createTag(TagRequestDto requestDto);
    TagResponseDto getTagById(UUID id);
    List<TagResponseDto> getAllTags();
}
