package com.sajid.Achievia.feature.achievements.controller;


import com.sajid.Achievia.feature.achievements.entity.Achievement;
import com.sajid.Achievia.feature.achievements.payload.request.AchievementRequestDto;
import com.sajid.Achievia.feature.achievements.payload.response.AchievementResponseDto;
import com.sajid.Achievia.feature.achievements.service.AchievementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievement")
public class AchievementController {

    private AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createAchievement(@RequestBody AchievementRequestDto achievementRequestDto) {
        achievementService.createAchievement(achievementRequestDto);
        return ResponseEntity.ok("Successfully created achievement");

    }

    @GetMapping("byId")
    public ResponseEntity<AchievementResponseDto> getAchievementById(@RequestParam Long id) {
       return ResponseEntity.ok(achievementService.getAchievement(id));

    }

    @GetMapping("all")
    public ResponseEntity<List<AchievementResponseDto>> getAllAchievements() {
        return ResponseEntity.ok(achievementService.getAchievements());
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteAchievementById(@RequestParam Long id) {
        achievementService.deleteAchievement(id);
        return ResponseEntity.ok("Successfully deleted achievement");
    }

    @PutMapping("update")
    public ResponseEntity<String>update(@RequestParam Long id ,@RequestBody AchievementRequestDto achievementRequestDto) {
        achievementService.updateAchievement(id, achievementRequestDto);
        return ResponseEntity.ok("Successfully updated achievement");
    }

}
