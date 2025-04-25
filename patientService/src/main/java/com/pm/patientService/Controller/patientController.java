package com.pm.patientService.Controller;

import com.pm.patientService.DTO.patientRequestDto;
import com.pm.patientService.DTO.patientResponseDto;
import com.pm.patientService.Service.patientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("patients")
public class patientController {

    private final patientService pService;

    public patientController(patientService pService){
        this.pService = pService;
    }
    @GetMapping
    public ResponseEntity<List<patientResponseDto>> getPatients(){
        List<patientResponseDto> patients = pService.getPatient();
        return ResponseEntity.ok().body(patients);
    }
    @PostMapping
    public ResponseEntity<patientResponseDto> createPatient(@Valid @RequestBody patientRequestDto requestDto) {
        patientResponseDto responseDto = pService.createPatient(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<patientResponseDto> updatedPatient(@PathVariable UUID id , @RequestBody patientRequestDto requestDto){
        patientResponseDto dto = pService.updatePatient(id, requestDto);
        return ResponseEntity.ok().body(dto);
    }


}
