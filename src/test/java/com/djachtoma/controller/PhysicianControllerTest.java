package com.djachtoma.controller;


import com.djachtoma.configuration.RedisContainerSetup;
import com.djachtoma.configuration.TestSetup;
import com.djachtoma.model.constant.Specialization;
import com.djachtoma.model.constant.Title;
import com.djachtoma.model.dto.PhysicianDTO;
import com.djachtoma.reference.entity.model.Gender;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PhysicianControllerTest extends TestSetup {

    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final LocalDateTime DATE_OF_BIRTH = LocalDateTime.of(2023, 5, 10, 0, 0);
    private static final String PHONE_NUMBER = "552-123-565";
    private static final String ID_CARD_SERIES_NUMBER = "DFG2312";
    private static final String CITY = "CITY";
    private static final String ZIP_CODE = "ZIP_CODE";
    private static final String STREET = "STREET";
    private static final String TITLE = Title.ACF.name();

    @Autowired
    private WebTestClient client;

    private RedisContainerSetup redisContainerSetup;

    @BeforeEach
    public void setup() {
        redisContainerSetup.start();
    }

    @Test
    public void getAllPatientsShouldReturnAllPatient() {
        //given
        PhysicianDTO physicianDTO = getPhysicianDTO();
        this.client.post()
                .uri("/api/physician")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(physicianDTO)
                .exchange()
                .expectBody(PhysicianDTO.class)
                .returnResult()
                .getResponseBody();

        //when
        List<PhysicianDTO> result = this.client.get()
                .uri("/api/physician")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBodyList(PhysicianDTO.class)
                .returnResult()
                .getResponseBody();

        //then
        PhysicianDTO created = result.get(0);
        assertThat(created.getName()).isEqualTo(physicianDTO.getName());
        assertThat(created.getSurname()).isEqualTo(physicianDTO.getSurname());
        assertThat(created.getGender()).isEqualTo(physicianDTO.getGender());
        assertThat(created.getPhoneNumber()).isEqualTo(physicianDTO.getPhoneNumber());
        assertThat(created.getDateOfBirth()).isEqualTo(physicianDTO.getDateOfBirth().toString());
        assertThat(created.getAddress()).isEqualTo(physicianDTO.getAddress());
        assertThat(created.getCity()).isEqualTo(physicianDTO.getCity());
        assertThat(created.getZipCode()).isEqualTo(physicianDTO.getZipCode());
        assertThat(created.getIdCardSeriesNumber()).isEqualTo(physicianDTO.getIdCardSeriesNumber());
        assertThat(created.getSpecializations()).isEqualTo(physicianDTO.getSpecializations());
        assertThat(created.getTitle()).isEqualTo(physicianDTO.getTitle());
    }

    @Test
    public void getPatientShouldReturnPhysicianDTO() {
        //given
        PhysicianDTO physicianDTO = getPhysicianDTO();
        PhysicianDTO created = this.client.post()
                .uri("/api/physician")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(physicianDTO)
                .exchange()
                .expectBody(PhysicianDTO.class)
                .returnResult()
                .getResponseBody();

        //when
        this.client.get()
                .uri("/api/physician/" + created.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(PhysicianDTO.class)
                .returnResult()
                .getResponseBody();

        //then
        assertThat(created.getName()).isEqualTo(physicianDTO.getName());
        assertThat(created.getSurname()).isEqualTo(physicianDTO.getSurname());
        assertThat(created.getGender()).isEqualTo(physicianDTO.getGender());
        assertThat(created.getPhoneNumber()).isEqualTo(physicianDTO.getPhoneNumber());
        assertThat(created.getDateOfBirth()).isEqualTo(physicianDTO.getDateOfBirth().toString());
        assertThat(created.getAddress()).isEqualTo(physicianDTO.getAddress());
        assertThat(created.getCity()).isEqualTo(physicianDTO.getCity());
        assertThat(created.getZipCode()).isEqualTo(physicianDTO.getZipCode());
        assertThat(created.getIdCardSeriesNumber()).isEqualTo(physicianDTO.getIdCardSeriesNumber());
        assertThat(created.getSpecializations()).isEqualTo(physicianDTO.getSpecializations());
        assertThat(created.getTitle()).isEqualTo(physicianDTO.getTitle());
    }

    @Test
    public void createPatientShouldReturnPhysicianDTO() {
        //given
        PhysicianDTO physicianDTO = getPhysicianDTO();

        //when
        PhysicianDTO created = this.client.post()
                .uri("/api/physician")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(physicianDTO)
                .exchange()
                .expectBody(PhysicianDTO.class)
                .returnResult()
                .getResponseBody();

        //then
        assertThat(created.getName()).isEqualTo(physicianDTO.getName());
        assertThat(created.getSurname()).isEqualTo(physicianDTO.getSurname());
        assertThat(created.getGender()).isEqualTo(physicianDTO.getGender());
        assertThat(created.getPhoneNumber()).isEqualTo(physicianDTO.getPhoneNumber());
        assertThat(created.getDateOfBirth()).isEqualTo(physicianDTO.getDateOfBirth().toString());
        assertThat(created.getAddress()).isEqualTo(physicianDTO.getAddress());
        assertThat(created.getCity()).isEqualTo(physicianDTO.getCity());
        assertThat(created.getZipCode()).isEqualTo(physicianDTO.getZipCode());
        assertThat(created.getIdCardSeriesNumber()).isEqualTo(physicianDTO.getIdCardSeriesNumber());
        assertThat(created.getSpecializations()).isEqualTo(physicianDTO.getSpecializations());
        assertThat(created.getTitle()).isEqualTo(physicianDTO.getTitle());
    }

    @Test
    public void updatePatientShouldReturnPhysicianDTO() {
        //given
        PhysicianDTO physicianDTO = getPhysicianDTO();
        PhysicianDTO created = this.client.post()
                .uri("/api/physician")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(physicianDTO)
                .exchange()
                .expectBody(PhysicianDTO.class)
                .returnResult()
                .getResponseBody();

        //when
        created.setName(SURNAME);

        PhysicianDTO result = this.client.patch()
                .uri("/api/physician/" + created.getId())
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(physicianDTO)
                .exchange()
                .expectBody(PhysicianDTO.class)
                .returnResult()
                .getResponseBody();
        //then
        assertThat(result).isEqualTo(created);
    }

    @Test
    public void deletePatientShouldDeletePatient() {
        //given
        PhysicianDTO physicianDTO = getPhysicianDTO();
        PhysicianDTO created = this.client.post()
                .uri("/api/physician")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(physicianDTO)
                .exchange()
                .expectBody(PhysicianDTO.class)
                .returnResult()
                .getResponseBody();

        //when
        this.client.delete()
                .uri("/api/physician/" + created.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();
    }

    private static PhysicianDTO getPhysicianDTO() {
        return PhysicianDTO.builder()
                .name(NAME)
                .surname(SURNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .phoneNumber(PHONE_NUMBER)
                .idCardSeriesNumber(ID_CARD_SERIES_NUMBER)
                .gender(Gender.FEMALE.toString())
                .city(CITY)
                .zipCode(ZIP_CODE)
                .address(STREET)
                .specializations(
                    Set.of(Specialization.EMERGENCY_MEDICINE.name(),
                            Specialization.RADIOLOGY.name(),
                            Specialization.INTERNAL_MEDICINE.name()))
                .title(TITLE)
                .build();
    }
    
}
