package com.sajid.Achievia.feature.tags.service;

import com.sajid.Achievia.feature.tags.entity.Tag;
import com.sajid.Achievia.feature.tags.payload.request.TagRequestDto;
import com.sajid.Achievia.feature.tags.payload.response.TagResponseDto;

import java.util.List;

public interface TagService {

    public List<TagResponseDto> getTags();
    public TagResponseDto getTagById(Long id);
    public void createTag(TagRequestDto tag);
    public void updateTag(Long id,TagRequestDto tag);
    public void deleteTag(Long id);


}
