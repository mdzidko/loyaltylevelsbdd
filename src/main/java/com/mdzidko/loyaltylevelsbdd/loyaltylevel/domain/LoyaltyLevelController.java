package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loyaltylevels")
class LoyaltyLevelController {

    private final LoyaltyLevelFacade loyaltyLevelFacade;

    LoyaltyLevelController(LoyaltyLevelFacade loyaltyLevelFacade) {
        this.loyaltyLevelFacade = loyaltyLevelFacade;
    }

    @GetMapping
    List<LoyaltyLevelDto> getAllLoyaltyLevels(){
        return loyaltyLevelFacade.findAll();
    }

    @PostMapping
    void addLoyaltyLevel(@RequestBody LoyaltyLevelDto loyaltyLevelDto){
        loyaltyLevelFacade.add(loyaltyLevelDto);
    }

    @DeleteMapping("/{id}")
    void deleteLoyaltyLevel(@PathVariable("id") long id){
        loyaltyLevelFacade.remove(id);
    }
}
