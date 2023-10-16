package com.accenture.horsefeeding.controller;

import com.accenture.horsefeeding.dto.FeedingRecordDto;
import com.accenture.horsefeeding.service.FeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feeding")
public class FeedingController {
    @Autowired
    private FeedingService feedingService;

    /**
     * Endpoint to mark feeding as done.
     *
     * @param horseId ID of the horse that approached the feeding station.
     * @return Response indicating the status of the operation.
     */
    @PostMapping("/markDone")
    public ResponseEntity<?> markFeedingDone(@RequestParam Long horseId) {
        try {
            FeedingRecordDto feedingRecordDTO = feedingService.createFeedingRecord(horseId);
            return new ResponseEntity<>(feedingRecordDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint to edit an existing feeding record.
     *
     * @param feedingRecordDto The DTO containing details of the feeding record to be edited.
     * @return ResponseEntity containing the updated feeding record if successful, or an error message if an exception occurs.
     */
    @PutMapping("/edit")
    public ResponseEntity<?> editFeedingRecord(@RequestBody FeedingRecordDto feedingRecordDto) {
        try {
            FeedingRecordDto updatedRecord = feedingService.updateFeedingRecord(feedingRecordDto);
            return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

