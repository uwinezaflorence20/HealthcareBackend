package org.example.healthproject.medicalrecord;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    public Optional<MedicalRecord> getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        // recordDate is set by default to current date/time
        return medicalRecordRepository.save(medicalRecord);
    }

    public MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecordDetails) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medical record not found"));

        medicalRecord.setPatient(medicalRecordDetails.getPatient());
        medicalRecord.setDoctor(medicalRecordDetails.getDoctor());
        medicalRecord.setDiagnosis(medicalRecordDetails.getDiagnosis());
        medicalRecord.setPrescription(medicalRecordDetails.getPrescription());
        medicalRecord.setRecordDate(medicalRecordDetails.getRecordDate());

        return medicalRecordRepository.save(medicalRecord);
    }

    public void deleteMedicalRecord(Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            throw new IllegalArgumentException("Medical record not found");
        }
        medicalRecordRepository.deleteById(id);
    }
}