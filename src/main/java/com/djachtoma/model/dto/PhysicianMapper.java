package com.djachtoma.model.dto;

import com.djachtoma.model.Physician;
import com.djachtoma.model.constant.Specialization;
import com.djachtoma.reference.entity.model.Address;
import com.djachtoma.reference.entity.model.Gender;
import com.djachtoma.reference.entity.model.IDCard;
import com.djachtoma.reference.entity.model.PhoneNumber;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PhysicianMapper {
    
    public PhysicianDTO toDTO(Physician physician) {
        return PhysicianDTO.builder()
                .id(physician.getId())
                .name(physician.getName())
                .surname(physician.getSurname())
                .gender(physician.getGender().name())
                .dateOfBirth(physician.getDateOfBirth())
                .phoneNumber(physician.getPhoneNumber().getNumber())
                .idCardSeriesNumber(physician.getIdCard().getSeriesNumber())
                .city(physician.getAddress().getCity())
                .zipCode(physician.getAddress().getZipCode())
                .address(physician.getAddress().getAddress())
                .specializations(physician.getSpecialization().stream()
                        .map(Specialization::getDescription)
                        .collect(Collectors.toSet()))
                .title(physician.getTitle().description)
                .build();
    }

    public Physician toEntity(PhysicianDTO physicianDTO) {
        return Physician.builder()
                .name(physicianDTO.getName())
                .surname(physicianDTO.getSurname())
                .dateOfBirth(physicianDTO.getDateOfBirth())
                .gender(Gender.valueOf(physicianDTO.getGender()))
                .idCard(IDCard.builder()
                        .seriesNumber(physicianDTO.getIdCardSeriesNumber())
                        .build())
                .phoneNumber(PhoneNumber.builder()
                        .number(physicianDTO.getPhoneNumber())
                        .build())
                .address(Address.builder()
                        .city(physicianDTO.getCity())
                        .zipCode(physicianDTO.getZipCode())
                        .address(physicianDTO.getAddress())
                        .build())
                .build();
    }
}
