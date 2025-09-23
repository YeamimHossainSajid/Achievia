package com.sajid.Achievia.config.notification;

import com.sajid.Achievia.auth.model.User;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping

public class SSEController {
    private  SSEService<User> sseService;

    public SSEController(SSEService<User> sseService) {
        this.sseService = sseService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "Subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> subscribe() {
        return sseService.subscribe();
    }
}
