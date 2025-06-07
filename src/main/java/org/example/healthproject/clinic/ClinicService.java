package org.example.healthproject.clinic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Optional<Clinic> getClinicById(Long id) {
        return clinicRepository.findById(id);
    }

    public Clinic createClinic(Clinic clinic) {
        if (clinicRepository.findByName(clinic.getName()).isPresent()) {
            throw new IllegalArgumentException("Clinic name must be unique");
        }
        if (clinicRepository.findByPhone(clinic.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Clinic phone must be unique");
        }
        return clinicRepository.save(clinic);
    }

    public Clinic updateClinic(Long id, Clinic clinicDetails) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Clinic not found"));

        if (!clinic.getName().equals(clinicDetails.getName()) &&
                clinicRepository.findByName(clinicDetails.getName()).isPresent()) {
            throw new IllegalArgumentException("Clinic name must be unique");
        }

        if (!clinic.getPhone().equals(clinicDetails.getPhone()) &&
                clinicRepository.findByPhone(clinicDetails.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Clinic phone must be unique");
        }

        clinic.setName(clinicDetails.getName());
        clinic.setAddress(clinicDetails.getAddress());
        clinic.setPhone(clinicDetails.getPhone());

        return clinicRepository.save(clinic);
    }

    public void deleteClinic(Long id) {
        if (!clinicRepository.existsById(id)) {
            throw new IllegalArgumentException("Clinic not found");
        }
        clinicRepository.deleteById(id);
    }
}