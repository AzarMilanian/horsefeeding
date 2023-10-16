package com.accenture.horsefeeding.controller;

import com.accenture.horsefeeding.dto.StableDto;
import com.accenture.horsefeeding.service.StableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stable")
public class StableController {
    @Autowired
    private StableService stableService;

    /**
     * Retrieve a stable.
     *
     * @param id The id of the stable to retrieve.
     * @return The retrieved stable.
     */
    @GetMapping("/{id}")
    public StableDto getStable(@PathVariable Long id) {
        return stableService.getStable(id);
    }
    @PostMapping
    public StableDto createStable(@RequestBody StableDto stableDto) {

        return stableService.createStable(stableDto);
    }

    /**
     * Update the stable data.
     *
     * @param id        The id of the stable to update.
     * @param stableDto The new stable data.
     * @return The updated stable.
     */
    @PutMapping("/{id}")
    public StableDto updateStable(@PathVariable Long id, @RequestBody StableDto stableDto) {
        return stableService.updateStable(id, stableDto);
    }

    /**
     * Delete a stable.
     *
     * @param id The id of the stable to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteStable(@PathVariable Long id) {
        stableService.deleteStable(id);
    }

}