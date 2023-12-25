package service.impl;

import exception.NotFoundException;
import model.Hospital;
import model.Patient;
import repository.impl.HospitalDaoImpl;
import service.HospitalService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {
    private HospitalDaoImpl hospitalDao;

    public HospitalServiceImpl(HospitalDaoImpl hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @Override
    public String addHospital(Hospital hospital) {
        if (hospital.getHospitalName().isBlank() || hospital.getAddress().isBlank()) throw new RuntimeException("Information is blank!");
        return hospitalDao.addHospital(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        if (id >= 1) {
            try {

                return hospitalDao.findHospitalById(id);
            }
            catch (NotFoundException e){
                System.out.println(e.getMessage());
            }
            return null;

        }

        throw new RuntimeException("Incorrect id !");
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDao.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        if (id >=1){
            try {
                return hospitalDao.getAllPatientFromHospital(id);
            }
            catch (NotFoundException e){
                System.out.println(e.getMessage());
            }
            return null;
        }
        throw new RuntimeException("Incorrect id !");
    }

    @Override
    public String deleteHospitalById(Long id) {
        if (id >= 1){
            try{
                return hospitalDao.deleteHospitalById(id);
            }
            catch (NotFoundException m){
                System.out.println(m.getMessage());
            }
            return null;
        }
        throw new RuntimeException("Incorrect id !");
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        if (!address.isBlank()){
            try{
                return hospitalDao.getAllHospitalByAddress(address);
            }
            catch (NotFoundException n){
                System.out.println(n.getMessage());
            }
            return null;
        }
        throw new RuntimeException("Incorrect address!");
    }
}
