package com.sajid.Achievia.feature.tags.service.impl;

import com.sajid.Achievia.feature.tags.entity.Tag;
import com.sajid.Achievia.feature.tags.payload.request.TagRequestDto;
import com.sajid.Achievia.feature.tags.payload.response.TagResponseDto;
import com.sajid.Achievia.feature.tags.repository.TagRepository;
import com.sajid.Achievia.feature.tags.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    private TagResponseDto convertToDto(Tag tag) {
        TagResponseDto tagResponseDto = new TagResponseDto();
        tagResponseDto.setId(tag.getId());
        tagResponseDto.setTagName(tag.getTagName());
        return tagResponseDto;
    }

    private Tag convertToEntity(TagRequestDto tagRequestDto) {
        Tag tag = new Tag();
        tag.setTagName(tagRequestDto.getTagName());
        return tag;
    }

    @Override
    public List<TagResponseDto> getTags() {
        return tagRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagResponseDto getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));
        return convertToDto(tag);
    }

    @Override
    public void createTag(TagRequestDto tagRequestDto) {
        Tag tag = convertToEntity(tagRequestDto);
        tagRepository.save(tag);
    }

    @Override
    public void updateTag(Long id, TagRequestDto tagRequestDto) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));

        tag.setTagName(tagRequestDto.getTagName());

        tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));
        tagRepository.delete(tag);
    }
}
