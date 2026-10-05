package com.bmk.portfolio.service;

import com.bmk.portfolio.dto.SkillRequest;
import com.bmk.portfolio.model.Skill;
import com.bmk.portfolio.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill createSkill(SkillRequest request){
        Skill skill = new Skill();
        skill.setCategory(request.getCategory());
        skill.setLevel(request.getLevel());
        skill.setName(request.getName());
        return skillRepository.save(skill);
    }

    public Skill getSkillById(Long id){
        return skillRepository.findById(id).orElse(null);
    }

    public Skill updateSkill(Long id, SkillRequest request){
        Skill skill = skillRepository.findById(id).orElse(null);

        if (skill == null){
            return null;
        }

        skill.setCategory(request.getCategory());
        skill.setLevel(request.getLevel());
        skill.setName(request.getName());

        return skillRepository.save(skill);
    }

    public boolean deleteSkill(Long id){
        if (!skillRepository.existsById(id)){
            return false;
        }

        skillRepository.deleteById(id);
        return true;
    }

}
