package com.tecnocampus.examsimulation.aplication;

import com.tecnocampus.examsimulation.aplication.DTO.KingdomDTO;
import com.tecnocampus.examsimulation.domain.Kingdom;
import com.tecnocampus.examsimulation.persistence.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class KingdomService {

    @Autowired
    private KingdomRepository kingdomRepository;

    public KingdomDTO createKingdom(KingdomDTO dto) {
        validateRange(dto.getGold());
        validateRange(dto.getCitizens());
        validateRange(dto.getFood());

        Kingdom kingdom = new Kingdom(dto.getGold(), dto.getCitizens(), dto.getFood());
        Kingdom saved = kingdomRepository.save(kingdom);
        return toDTO(saved);
    }

    public KingdomDTO startDailyProduction(Long id) {
        Kingdom kingdom = getExistingKingdom(id);

        int availableFood = kingdom.getFood();
        int citizens = kingdom.getCitizens();

        int survivingCitizens = Math.min(availableFood, citizens);
        int producedGold = survivingCitizens * 2;

        kingdom.setFood(availableFood - survivingCitizens);
        kingdom.setGold(kingdom.getGold() + producedGold);
        kingdom.setCitizens(survivingCitizens);

        if (survivingCitizens == 0) {
            kingdomRepository.deleteById(id);
            throw new ResponseStatusException(NOT_ACCEPTABLE);
        }

        kingdomRepository.save(kingdom);
        return toDTO(kingdom);
    }

    public KingdomDTO invest(Long id, String type, int gold) {
        Kingdom kingdom = getExistingKingdom(id);

        if (gold > kingdom.getGold()) {
            throw new ResponseStatusException(NOT_ACCEPTABLE);
        }

        switch (type) {
            case "food" -> {
                kingdom.setGold(kingdom.getGold() - gold);
                kingdom.setFood(kingdom.getFood() + gold * 2);
            }
            case "citizens" -> {
                kingdom.setGold(kingdom.getGold() - gold);
                kingdom.setCitizens(kingdom.getCitizens() + gold);
            }
            default -> throw new ResponseStatusException(NOT_ACCEPTABLE);
        }

        kingdomRepository.save(kingdom);
        return toDTO(kingdom);
    }

    public KingdomDTO getKingdom(Long id) {
        Kingdom kingdom = getExistingKingdom(id);
        return toDTO(kingdom);
    }

    public KingdomDTO richestKingdom() {
        Optional<Kingdom> richest = kingdomRepository.findAll().stream()
                .max(Comparator.comparingInt(Kingdom::getGold));
        return richest.map(this::toDTO)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    public KingdomDTO attack(Long attackerId, Long targetId) {
        Kingdom attacker = getExistingKingdom(attackerId);
        Kingdom target = getExistingKingdom(targetId);

        Kingdom winner;
        Kingdom loser;

        if (attacker.getCitizens() > target.getCitizens()) {
            winner = attacker;
            loser = target;
        } else if (attacker.getCitizens() < target.getCitizens()) {
            winner = target;
            loser = attacker;
        } else {
            winner = target;
            loser = attacker;
        }

        int stolenCitizens = loser.getCitizens() / 2;
        winner.setGold(winner.getGold() + loser.getGold());
        winner.setCitizens(winner.getCitizens() + stolenCitizens);

        loser.setGold(0);
        loser.setCitizens(loser.getCitizens() - stolenCitizens);

        kingdomRepository.save(winner);
        kingdomRepository.save(loser);

        return toDTO(attacker);
    }

    private Kingdom getExistingKingdom(Long id) {
        return kingdomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    private void validateRange(int value) {
        if (value < 0 || value > 60) {
            throw new ResponseStatusException(NOT_ACCEPTABLE);
        }
    }

    private KingdomDTO toDTO(Kingdom kingdom) {
        return new KingdomDTO(
                kingdom.getId(),
                kingdom.getGold(),
                kingdom.getCitizens(),
                kingdom.getFood(),
                kingdom.getDateOfCreation());
    }
}
