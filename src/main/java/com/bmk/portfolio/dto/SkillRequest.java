package com.bmk.portfolio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class SkillRequest {

    @NotBlank(message = "Skill name cannot be empty")
    private String name;

    @NotBlank(message = "Category cannot be empty")
    private String category;

    @Min(value = 1, message = "Level must be at least 1")
    @Max(value = 5, message = "Level must not exceed 5")
    private int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}