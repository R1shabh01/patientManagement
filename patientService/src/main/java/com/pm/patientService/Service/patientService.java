package com.pm.patientService.Service;

import com.pm.patientService.DTO.patientRequestDto;
import com.pm.patientService.DTO.patientResponseDto;
import com.pm.patientService.Exception.EmailAlreadyExistsException;
import com.pm.patientService.Exception.PatientNotFoundException;
import com.pm.patientService.Mapper.patientMapper;
import com.pm.patientService.Modal.patientModal;
import com.pm.patientService.Repository.patientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class patientService {
    private final patientRepository repo;
    patientService(patientRepository repo){
        this.repo = repo;
    }

    public List<patientResponseDto> getPatient(){
        List<patientModal> patients = repo.findAll();
        return patients.stream().map(patientMapper::toDto).toList();
    }

    public patientResponseDto createPatient(patientRequestDto requestDto){
        if(repo.existsByEmail(requestDto.getEmail())){
            throw new EmailAlreadyExistsException("A Patient with this email already exists " + requestDto.getEmail());
        }
        patientModal newPatient = repo.save(patientMapper.toModel(requestDto));
        return patientMapper.toDto(newPatient);
    }

    public patientResponseDto updatePatient(UUID id , patientRequestDto requestDto){
        patientModal patient = repo.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found with ID: " + id));

        if(repo.existsByEmail(requestDto.getEmail())){
            throw new EmailAlreadyExistsException("A Patient with this email already exists " + requestDto.getEmail());
        }
        patient.setName(requestDto.getName());
        patient.setAddress(requestDto.getAddress());
        patient.setEmail(requestDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(requestDto.getDateOfBirth()));

        patientModal updatedPatient = repo.save(patient);
        return patientMapper.toDto(updatedPatient);

    }

}
