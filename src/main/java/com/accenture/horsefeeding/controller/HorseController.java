package com.accenture.horsefeeding.controller;

import com.accenture.horsefeeding.dto.HorseDto;
import com.accenture.horsefeeding.service.HorseService;
import com.accenture.horsefeeding.mapper.HorseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/horse")
public class HorseController {
    @Autowired
    private HorseService horseService;
    @Autowired
    private HorseMapper horseMapper;
    @PostMapping
    public HorseDto createHorse(@RequestBody HorseDto horseDto) {
        return horseService.createHorse(horseDto);
    }

    /**
     * Gets a horse by ID.
     *
     * @param horseId the ID of the horse to retrieve
     * @return the horse as a data transfer object
     */
    @GetMapping("/{horseId}")
    public HorseDto getHorse(@PathVariable Long horseId) {
        return horseService.findHorseById(horseId);
    }

    /**
     * Updates a horse's details.
     *
     * @param horseId  the ID of the horse to update
     * @param horseDto the data transfer object containing the new details
     * @return the updated horse as a data transfer object
     */
    @PutMapping("/{horseId}")
    public HorseDto updateHorse(@PathVariable Long horseId, @RequestBody HorseDto horseDto) {
        return horseService.updateHorse(horseId, horseDto);
    }

    /**
     * Deletes a horse by ID.
     *
     * @param horseId the ID of the horse to delete
     * @return a message confirming deletion
     */
    @DeleteMapping("/{horseId}")
    public ResponseEntity<String> deleteHorse(@PathVariable Long horseId) {
        horseService.deleteHorse(horseId);
        return ResponseEntity.ok("Horse with ID " + horseId + " deleted successfully.");
    }

    /**
     * Fetches a list of horses that have not been fed for a specified number of hours.
     *
     * @param hours The number of hours to check against.
     * @return A list of HorseDTOs representing horses that haven't been fed for the specified hours.
     */
    @GetMapping("/not-fed-for-hours")
    public ResponseEntity<List<HorseDto>> getHorsesNotFedForHours(@RequestParam int hours) {
        try {
            List<HorseDto> horsesNotFedDtos = horseService.getHorsesNotFedForHours(hours);
            return new ResponseEntity<>(horsesNotFedDtos, HttpStatus.OK);
        } catch (Exception e) {
            // For simplicity, returning a generic error message. Consider logging the error or returning a specific error response.
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a list of horses with incomplete meals.
     *
     * <p>This endpoint fetches horses that haven't completed their meals. If any
     * exception occurs during the process, it responds with an INTERNAL_SERVER_ERROR status.</p>
     *
     * @return A ResponseEntity containing a list of {@link HorseDto} representing horses with
     *         incomplete meals, or an INTERNAL_SERVER_ERROR status if an exception occurs.
     */
    @GetMapping("/incomplete-meals")
    public ResponseEntity<List<HorseDto>> getHorsesWithIncompleteMeals() {
        try {
            List<HorseDto> horsesWithIncompleteMeals = horseService.getHorsesWithIncompleteMeals();

            return new ResponseEntity<>(horsesWithIncompleteMeals, HttpStatus.OK);
        } catch (Exception e) {
            // For simplicity, returning a generic error message.
            // Consider logging the error or returning a more specific error response.
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

