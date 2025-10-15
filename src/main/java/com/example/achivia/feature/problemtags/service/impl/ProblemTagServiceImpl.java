package com.example.achivia.feature.problemtags.service.impl;

import com.example.achivia.feature.problem.entity.Problem;
import com.example.achivia.feature.problem.repositroy.ProblemRepository;
import com.example.achivia.feature.problemtags.payload.request.ProblemTagRequestDto;
import com.example.achivia.feature.problemtags.payload.response.ProblemTagResponseDto;
import com.example.achivia.feature.tag.entity.Tag;
import com.example.achivia.feature.problemtags.entity.ProblemTag;
import com.example.achivia.feature.problemtags.entity.ProblemTagId;
import com.example.achivia.feature.problemtags.repository.ProblemTagRepository;
import com.example.achivia.feature.problemtags.service.ProblemTagService;
import com.example.achivia.feature.tag.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProblemTagServiceImpl implements ProblemTagService {

    private final ProblemTagRepository problemTagRepository;
    private final ProblemRepository problemRepository;
    private final TagRepository tagRepository;

    @Override
    public ProblemTagResponseDto assignTagToProblem(ProblemTagRequestDto dto) {
        Problem problem = problemRepository.findById(dto.getProblemId())
                .orElseThrow(() -> new EntityNotFoundException("Problem not found with ID: " + dto.getProblemId()));

        Tag tag = tagRepository.findById(dto.getTagId())
                .orElseThrow(() -> new EntityNotFoundException("Tag not found with ID: " + dto.getTagId()));

        ProblemTagId id = ProblemTagId.builder()
                .problemId(dto.getProblemId())
                .tagId(dto.getTagId())
                .build();

        ProblemTag problemTag = ProblemTag.builder()
                .id(id)
                .problem(problem)
                .tag(tag)
                .build();

        ProblemTag saved = problemTagRepository.save(problemTag);

        return mapToResponse(saved);
    }

    @Override
    public List<ProblemTagResponseDto> getTagsByProblem(UUID problemId) {
        return problemTagRepository.findByProblemId(problemId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProblemTagResponseDto> getProblemsByTag(UUID tagId) {
        return problemTagRepository.findByTagId(tagId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ProblemTagResponseDto mapToResponse(ProblemTag entity) {
        return ProblemTagResponseDto.builder()
                .problemId(entity.getProblem().getId())
                .tagId(entity.getTag().getId())
                .problemTitle(entity.getProblem().getTitle())
                .tagName(entity.getTag().getName())
                .build();
    }
}
