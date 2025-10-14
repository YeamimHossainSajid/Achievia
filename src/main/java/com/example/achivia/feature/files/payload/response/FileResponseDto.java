package com.example.achivia.feature.files.payload.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponseDto {
    private UUID id;
    private UUID uploaderUserId;
    private String url;
    private String mimeType;
    private Long sizeBytes;
    private LocalDateTime createdAt;
}
