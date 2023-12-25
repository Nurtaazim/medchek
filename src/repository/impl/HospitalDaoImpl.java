package repository.impl;

import database.Database;
import exception.NotFoundException;
import generate.Id;
import model.Hospital;
import model.Patient;
import service.HospitalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalDaoImpl implements HospitalService {
    private Database database;

    public HospitalDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String addHospital(Hospital hospital) {
        hospital.setId(Id.generateId());
        database.getHospitals().add(hospital);
        return "Successfully!";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(id))
                return hospital;
        }
        throw new NotFoundException("Hospital with this id not found!");
    }

    @Override
    public List<Hospital> getAllHospital() {
        return database.getHospitals();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(id)) return hospital.getPatients();
        }
        throw new NotFoundException("Hospital with this id not found!");
    }

    @Override
    public String deleteHospitalById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(id)) {
                database.getHospitals().remove(hospital);
                return "Successfully deleted !";
            }
        }
        throw new NotFoundException("Hospital with this id not found!");
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String, Hospital> map = new HashMap<>();
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getAddress().equalsIgnoreCase(address)) {
                map.put(hospital.getAddress(), hospital);
                return map;
            }
        }
        throw new NotFoundException("Hospital with this address not found!");
    }
}
