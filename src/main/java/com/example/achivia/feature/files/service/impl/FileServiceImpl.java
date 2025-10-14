package com.example.achivia.feature.files.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.files.entity.File;
import com.example.achivia.feature.files.payload.request.FileRequestDto;
import com.example.achivia.feature.files.payload.response.FileResponseDto;
import com.example.achivia.feature.files.repository.FileRepository;
import com.example.achivia.feature.files.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final UserRepo userRepo;

    @Override
    public FileResponseDto createFile(FileRequestDto dto) {
        User uploader = userRepo.findById(dto.getUploaderUserId());

        File file = File.builder()
                .uploaderUser(uploader)
                .url(dto.getUrl())
                .mimeType(dto.getMimeType())
                .sizeBytes(dto.getSizeBytes())
                .build();

        file = fileRepository.save(file);
        return convertToDto(file);
    }

    @Override
    public FileResponseDto getFileById(UUID id) {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
        return convertToDto(file);
    }

    @Override
    public List<FileResponseDto> getAllFiles() {
        return fileRepository.findAllNative().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FileResponseDto> getFilesByUserId(UUID userId) {
        return fileRepository.findAllByUploaderUserId(userId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFile(UUID id) {
        fileRepository.deleteById(id);
    }

    private FileResponseDto convertToDto(File file) {
        return FileResponseDto.builder()
                .id(file.getId())
                .uploaderUserId(file.getUploaderUser() != null ? file.getUploaderUser().getId() : null)
                .url(file.getUrl())
                .mimeType(file.getMimeType())
                .sizeBytes(file.getSizeBytes())
                .createdAt(file.getCreatedAt())
                .build();
    }
}
