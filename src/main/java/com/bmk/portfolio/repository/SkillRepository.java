package com.bmk.portfolio.repository;

import com.bmk.portfolio.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long>{
}