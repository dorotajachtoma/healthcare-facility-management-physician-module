package com.djachtoma.service;

import com.djachtoma.exception.PhysicianNotFoundException;
import com.djachtoma.model.Physician;
import com.djachtoma.model.dto.PhysicianDTO;
import com.djachtoma.model.dto.PhysicianMapper;
import com.djachtoma.repository.PhysicianRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

import static com.djachtoma.utils.TestObjectUtil.getPhysician;
import static com.djachtoma.utils.TestObjectUtil.getPhysicianDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PhysicianServiceTest {

    @InjectMocks
    private PhysicianService physicianService;

    @Mock
    private PhysicianRepository physicianRepository;

    @Mock
    private PhysicianMapper physicianMapper;

    @Test
    public void getFacilities_shouldReturnAllFacilities() {
        //given
        Set<Physician> physicians = Set.of(getPhysician());
        when(physicianRepository.findAll()).thenReturn(physicians);

        //when
        physicianService.getPhysicians();

        //then
        verify(physicianRepository, times(1)).findAll();
    }

    @Test
    public void getPhysician_shouldReturnPhysicianById() {
        //given
        Physician physician = getPhysician();
        PhysicianDTO physicianDTO = getPhysicianDTO();
        when(physicianRepository.findById(physician.getId())).thenReturn(Optional.of(physician));
        when(physicianMapper.toDTO(physician)).thenReturn(physicianDTO);

        //when
        physicianService.getPhysician(physician.getId());

        //then
        verify(physicianRepository, times(1)).findById(physician.getId());
    }

    @Test
    public void getPhysician_shouldThrowExceptionPhysicianNotFound() {
        //given
        Physician physician = getPhysician();

        //when
        Throwable throwable = catchThrowable(() -> physicianService.getPhysician(physician.getId()));

        //then
        assertThat(throwable).isInstanceOf(PhysicianNotFoundException.class);
        assertThat(throwable).hasMessageContaining("Physician with provided ID: ID does not exist.");
    }

    @Test
    public void createPhysician_shouldReturnPhysician() {
        //given
        PhysicianDTO physicianDTO = getPhysicianDTO();
        Physician physician = getPhysician();
        when(physicianRepository.findById(physician.getId())).thenReturn(Optional.of(physician));
        when(physicianRepository.save(physician)).thenReturn(physician);
        when(physicianMapper.toDTO(physician)).thenReturn(physicianDTO);

        //when
        physicianService.updatePhysician(physician.getId(), physicianDTO);

        //then
        verify(physicianRepository, times(1)).save(physician);
    }

    @Test
    public void updatePhysician_shouldReturnUpdatedPhysician() {
        //given
        PhysicianDTO physicianDTO = getPhysicianDTO();
        Physician physician = getPhysician();
        when(physicianMapper.toEntity(physicianDTO)).thenReturn(physician);
        when(physicianRepository.save(physician)).thenReturn(physician);
        when(physicianMapper.toDTO(physician)).thenReturn(physicianDTO);

        //when
        physicianService.createPhysician(physicianDTO);

        //then
        verify(physicianRepository, times(1)).save(physician);
    }

    @Test
    public void updatePhysician_shouldThrowException() {
        //given
        Physician physician = getPhysician();

        //when
        Throwable throwable = catchThrowable(() -> physicianService.getPhysician(physician.getId()));

        //then
        assertThat(throwable).isInstanceOf(PhysicianNotFoundException.class);
        assertThat(throwable).hasMessageContaining("Physician with provided ID: ID does not exist.");
    }

    @Test
    public void deletePhysician_shouldDelete() {
        //given
        Physician physician = getPhysician();
        when(physicianRepository.findById(physician.getId())).thenReturn(Optional.of(physician));

        //when
        physicianService.deletePhysician(physician.getId());

        //then
        verify(physicianRepository, times(1)).delete(physician);
    }

    @Test
    public void deletePhysician_shouldThrowException() {
        //given
        Physician physician = getPhysician();

        //when
        Throwable throwable = catchThrowable(() -> physicianService.getPhysician(physician.getId()));

        //then
        assertThat(throwable).isInstanceOf(PhysicianNotFoundException.class);
        assertThat(throwable).hasMessageContaining("Physician with provided ID: ID does not exist.");
    }
}
