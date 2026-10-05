package com.bmk.portfolio.service;

import com.bmk.portfolio.dto.ProfileRequest;
import com.bmk.portfolio.model.Profile;
import com.bmk.portfolio.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    public Profile createProfile(ProfileRequest request) {

        Profile profile = new Profile();

        profile.setName(request.getName());
        profile.setHeadline(request.getHeadline());
        profile.setBio(request.getBio());
        profile.setEmail(request.getEmail());
        profile.setLocation(request.getLocation());
        profile.setGithub(request.getGithub());
        profile.setLinkedin(request.getLinkedin());

        return profileRepository.save(profile);
    }

    public Profile updateProfile(Long id, ProfileRequest request) {

        Profile profile = profileRepository.findById(id).orElse(null);

        if (profile == null) {
            return null;
        }

        profile.setName(request.getName());
        profile.setHeadline(request.getHeadline());
        profile.setBio(request.getBio());
        profile.setEmail(request.getEmail());
        profile.setLocation(request.getLocation());
        profile.setGithub(request.getGithub());
        profile.setLinkedin(request.getLinkedin());

        return profileRepository.save(profile);
    }

    public boolean deleteProfile(Long id) {
        if (!profileRepository.existsById(id)) {
            return false;
        }

        profileRepository.deleteById(id);
        return true;
    }
}