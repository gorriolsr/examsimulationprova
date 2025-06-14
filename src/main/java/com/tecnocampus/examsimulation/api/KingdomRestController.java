package com.tecnocampus.examsimulation.api;

import com.tecnocampus.examsimulation.aplication.DTO.KingdomDTO;
import com.tecnocampus.examsimulation.aplication.KingdomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kingdoms")
public class KingdomRestController {

    @Autowired
    private KingdomService kingdomService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KingdomDTO createKingdom(@RequestBody KingdomDTO dto) {
        return kingdomService.createKingdom(dto);
    }

    @PostMapping("/{id}")
    public KingdomDTO startProduction(@PathVariable Long id) {
        return kingdomService.startDailyProduction(id);
    }

    @PostMapping("/{id}/invest")
    public KingdomDTO invest(@PathVariable Long id,
                             @RequestParam String type,
                             @RequestBody KingdomDTO dto) {
        return kingdomService.invest(id, type, dto.getGold());
    }

    @GetMapping("/{id}")
    public KingdomDTO getKingdom(@PathVariable Long id) {
        return kingdomService.getKingdom(id);
    }

    @GetMapping("/richest")
    public KingdomDTO richest() {
        return kingdomService.richestKingdom();
    }

    @PostMapping("/{id}/attack/{targetId}")
    public KingdomDTO attack(@PathVariable Long id, @PathVariable Long targetId) {
        return kingdomService.attack(id, targetId);
    }
}
