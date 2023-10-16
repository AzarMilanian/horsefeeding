package com.accenture.horsefeeding.service;

import com.accenture.horsefeeding.dto.StableDto;
import com.accenture.horsefeeding.exception.ResourceNotFoundException;
import com.accenture.horsefeeding.mapper.StableMapper;
import com.accenture.horsefeeding.model.Stable;
import com.accenture.horsefeeding.repository.StableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StableService {
    @Autowired
    private StableRepository stableRepository;
    @Autowired
    private StableMapper stableMapper;

    /**
     * Creates a new stable.
     *
     * @param stableDto The DTO representation of the stable to be created.
     * @return The DTO representation of the saved stable.
     */
    public StableDto createStable(StableDto stableDto) {
        Stable stable = stableMapper.toEntity(stableDto);
        stable = stableRepository.save(stable);
        return stableMapper.toDto(stable);
    }

    /**
     * Updates an existing stable by its ID.
     *
     * @param id The ID of the stable to be updated.
     * @param stableDto The DTO representation with updated stable details.
     * @return The DTO representation of the updated stable.
     * @throws ResourceNotFoundException if no stable with the specified ID is found.
     */
    public StableDto updateStable(Long id, StableDto stableDto) {
        Stable stable = stableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stable", "id", id));
        stable.setStableName(stableDto.getStableName());
        stable = stableRepository.save(stable);
        return stableMapper.toDto(stable);

    }


    /**
     * Delete a stable.
     *
     * @param id The id of the stable to delete.
     */
    public void deleteStable(Long id) {
        stableRepository.deleteById(id);
    }

    /**
     * Get a stable by id.
     *
     * @param id The id of the stable to retrieve.
     * @return The retrieved stable.
     */
    public StableDto getStable(Long id) {
        Stable stable = stableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stable", "id", id));
        return stableMapper.toDto(stable);
    }
}