package com.pm.patientService.Mapper;

import com.pm.patientService.DTO.patientRequestDto;
import com.pm.patientService.DTO.patientResponseDto;
import com.pm.patientService.Modal.patientModal;

import java.time.LocalDate;

public class patientMapper {

    public static patientResponseDto toDto(patientModal patient){
        patientResponseDto patientDto = new patientResponseDto();
        patientDto.setId(patient.getId().toString());
        patientDto.setName(patient.getName());
        patientDto.setEmail(patient.getEmail());
        patientDto.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientDto;
    }

    public static patientModal toModel(patientRequestDto patientrequest){
        patientModal patient = new patientModal();
        patient.setName(patientrequest.getName());
        patient.setEmail(patientrequest.getEmail());
        patient.setAddress(patientrequest.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientrequest.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientrequest.getRegisteredDate()));

        return patient;
    }
}