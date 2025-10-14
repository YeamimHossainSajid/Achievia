package com.example.achivia.feature.files.service;


import com.example.achivia.feature.files.payload.request.FileRequestDto;
import com.example.achivia.feature.files.payload.response.FileResponseDto;

import java.util.List;
import java.util.UUID;

public interface FileService {

    FileResponseDto createFile(FileRequestDto dto);

    FileResponseDto getFileById(UUID id);

    List<FileResponseDto> getAllFiles();

    List<FileResponseDto> getFilesByUserId(UUID userId);

    void deleteFile(UUID id);
}
