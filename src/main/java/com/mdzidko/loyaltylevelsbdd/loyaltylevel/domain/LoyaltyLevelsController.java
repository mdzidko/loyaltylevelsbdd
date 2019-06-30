package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loyaltylevels")
class LoyaltyLevelsController {

    private final LoyaltyLevelsFacade loyaltyLevelsFacade;

    LoyaltyLevelsController(LoyaltyLevelsFacade loyaltyLevelsFacade) {
        this.loyaltyLevelsFacade = loyaltyLevelsFacade;
    }

    @GetMapping
    List<LoyaltyLevelDto> getAllLoyaltyLevels(){
        return loyaltyLevelsFacade.findAll();
    }

    @PostMapping
    void addLoyaltyLevel(@RequestBody LoyaltyLevelDto loyaltyLevelDto){
        loyaltyLevelsFacade.add(loyaltyLevelDto);
    }

    @DeleteMapping("/{name}")
    void deleteLoyaltyLevel(@PathVariable("name") String name){
        loyaltyLevelsFacade.remove(name);
    }
}
