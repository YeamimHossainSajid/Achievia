package com.example.achivia.feature.files.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileRequestDto {
    private UUID uploaderUserId;
    private String url;
    private String mimeType;
    private Long sizeBytes;
}
