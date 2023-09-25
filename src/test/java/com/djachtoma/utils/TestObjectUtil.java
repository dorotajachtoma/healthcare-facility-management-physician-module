package com.djachtoma.utils;

import com.djachtoma.model.Physician;
import com.djachtoma.model.constant.Specialization;
import com.djachtoma.model.constant.Title;
import com.djachtoma.model.dto.PhysicianDTO;
import com.djachtoma.reference.entity.model.Gender;
import com.djachtoma.reference.entity.model.IDCard;
import com.djachtoma.reference.entity.model.PhoneNumber;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Set;

@UtilityClass
public class TestObjectUtil {

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String CITY = "CITY";
    public static final String ZIP_CODE = "ZIP_CODE";
    public static final String ADDRESS = "ADDRESS";
    public static final String SURNAME = "SURNAME";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String ID_CARD_SERIES_NUMBER = "ID_CARD_SERIES_NUMBER";
    public static final LocalDateTime DATE_OF_BIRTH = LocalDateTime.of(2023, 1, 1, 1, 1, 0);

    public static PhysicianDTO getPhysicianDTO() {
        return PhysicianDTO.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .idCardSeriesNumber(ID_CARD_SERIES_NUMBER)
                .gender(Gender.MALE.name())
                .phoneNumber(PHONE_NUMBER)
                .address(ADDRESS)
                .city(CITY)
                .zipCode(ZIP_CODE)
                .title(Title.AP.name())
                .specializations(Set.of(Specialization.NUCLEAR_MEDICINE.name()))
                .build();
    }

    public static Physician getPhysician() {
        return Physician.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .gender(Gender.MALE)
                .idCard(IDCard.builder()
                        .seriesNumber(ID_CARD_SERIES_NUMBER)
                        .build())
                .phoneNumber(PhoneNumber.builder()
                        .number(PHONE_NUMBER)
                        .build())
                .address(com.djachtoma.reference.entity.model.Address.builder()
                        .address(ADDRESS)
                        .city(CITY)
                        .zipCode(ZIP_CODE)
                        .build())
                .title(Title.AP)
                .specialization(Set.of(Specialization.NUCLEAR_MEDICINE))
                .build();
    }
}
