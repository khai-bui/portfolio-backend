package com.bmk.portfolio.controller;

import com.bmk.portfolio.model.Profile;
import com.bmk.portfolio.service.ProfileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.bmk.portfolio.dto.ProfileRequest;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {

        Profile profile = profileService.getProfileById(id);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(profile);
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(
            @Valid @RequestBody ProfileRequest request) {

        Profile createdProfile = profileService.createProfile(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody ProfileRequest request) {

        Profile profile = profileService.updateProfile(id, request);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {

        boolean deleted = profileService.deleteProfile(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}