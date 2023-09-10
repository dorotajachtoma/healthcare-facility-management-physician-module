package com.djachtoma.service;

import com.djachtoma.exception.PhysicianNotFoundException;
import com.djachtoma.model.Physician;
import com.djachtoma.model.constant.Specialization;
import com.djachtoma.model.constant.Title;
import com.djachtoma.model.dto.PhysicianDTO;
import com.djachtoma.model.dto.PhysicianMapper;
import com.djachtoma.reference.entity.model.Gender;
import com.djachtoma.reference.entity.model.IDCard;
import com.djachtoma.reference.entity.model.PhoneNumber;
import com.djachtoma.repository.PhysicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.djachtoma.utils.ObjectUtils.nullSafeUpdate;

@Service
@RequiredArgsConstructor
public class PhysicianService {

    private final PhysicianRepository physicianRepository;
    private final PhysicianMapper physicianMapper;
    
    public Flux<PhysicianDTO> getPhysicians() {
        Iterable<PhysicianDTO> physicians = Stream.of(physicianRepository.findAll().iterator())
                .map(physician -> physicianMapper.toDTO(physician.next()))
                .collect(Collectors.toSet());
        return Flux.fromIterable(physicians);
    }
    
    public Mono<PhysicianDTO> getPhysician(String id) {
        return Mono.just(physicianMapper.toDTO(getPhysicianById(id)));
    }
    
    @Transactional
    public Mono<PhysicianDTO> createPhysician(PhysicianDTO physicianDTO) {
        Physician physician = physicianMapper.toEntity(physicianDTO);
        return Mono.just(physicianMapper.toDTO(physicianRepository.save(physician)));
    }
    
    @Transactional
    public Mono<PhysicianDTO> updatePhysician(String id, PhysicianDTO physicianDTO) {
        Physician physician = getPhysicianById(id);
        updatePhysicianEntity(physician, physicianDTO);
        return Mono.just(physicianMapper.toDTO(physician));
    }

    @Transactional
    public void deletePhysician(String id) {
        Physician physician = getPhysicianById(id);
        physicianRepository.delete(physician);
    }
    
    
    private void updatePhysicianEntity(Physician physician, PhysicianDTO physicianDTO) {
        nullSafeUpdate(physicianDTO.getName(), physicianDTO::getName, physician::setName);
        nullSafeUpdate(physicianDTO.getSurname(), physicianDTO::getSurname, physician::setName);
        nullSafeUpdate(physicianDTO.getGender(), physicianDTO::getGender, x -> physician.setGender(Gender.valueOf(x)));
        nullSafeUpdate(physician.getDateOfBirth(), physicianDTO::getDateOfBirth, physician::setDateOfBirth);
        nullSafeUpdate(physicianDTO.getTitle(), physicianDTO::getTitle, x -> physician.setTitle(Title.valueOf(x)));
        nullSafeUpdate(physicianDTO.getSpecializations(), physicianDTO::getSpecializations, x -> physician.getSpecialization()
                .add(Specialization.valueOf(x.iterator().next())));
        nullSafeUpdate(physicianDTO.getIdCardSeriesNumber(), physicianDTO::getIdCardSeriesNumber, x -> physician.setIdCard(IDCard.builder()
                .seriesNumber(x)
                .build()));
        nullSafeUpdate(physicianDTO.getPhoneNumber(), physicianDTO::getPhoneNumber, x -> physician.setPhoneNumber(PhoneNumber.builder()
                .number(x)
                .build()));
        physicianRepository.save(physician);
    }
    
    
    private Physician getPhysicianById(String id) {
        return physicianRepository.findById(id)
                .orElseThrow(() -> new PhysicianNotFoundException("Physician with provided id does not exist."));
    }

}
