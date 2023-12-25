package repository.impl;

import database.Database;
import exception.NotFoundException;
import model.Hospital;
import model.Patient;
import repository.GenericDao;
import service.PatientService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PatientDaoImpl implements PatientService, GenericDao<Patient> {
    private Database database;

    public PatientDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        for (Hospital hospital : database.getHospitals()) {
            if(hospital.getId().equals(hospitalId)){
                hospital.getPatients().add(patient);
                return "Successfully added!";
            }
        }
        throw new NotFoundException("Hospital with this id not found!");
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            for (Patient patient : hospital.getPatients()) {
                if (patient.getId().equals(id)){
                    hospital.getPatients().remove(patient);
                    System.out.println("Successfully deleted!");
                    return;
                }
            }
        }
        throw new NotFoundException("Patient with this id not found!");
    }

    @Override
    public String updateById(Long id, Patient patient) {
        return null;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        return null;
    }

    @Override
    public Patient getPatientById(Long id) {
        return null;
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        return null;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = new ArrayList<>();
        for (Hospital hospital : database.getHospitals()) {
            patients.addAll(hospital.getPatients());
        }
        Collections.sort(patients);
        List<Patient> reversed = patients.reversed();
        if (ascOrDesc.equalsIgnoreCase("asc")) return patients;
        else if (ascOrDesc.equalsIgnoreCase("desc")) return reversed;
        else throw new NotFoundException("Asc or Desc");

    }
}
