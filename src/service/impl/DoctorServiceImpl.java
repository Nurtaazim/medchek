package service.impl;

import exception.NotFoundException;
import model.Doctor;
import repository.impl.DoctorDaoImpl;
import service.DoctorService;
import service.GenericService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService, GenericService<Doctor> {
    private DoctorDaoImpl doctorDao ;

    public DoctorServiceImpl(DoctorDaoImpl doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        if (id > 0 ) {
            try{
                return doctorDao.findDoctorById(id);
            }
            catch (NotFoundException e ){
                System.out.println(e.getMessage());
            }
            return null;
        }
        throw new RuntimeException("Incorrect id:");
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        try {
            return doctorDao.assignDoctorToDepartment(departmentId, doctorsId);
        }
        catch (NotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        if (id > 0){
            try {
                return doctorDao.getAllDoctorsByHospitalId(id);
            }
            catch (NotFoundException e){
                System.out.println(e.getMessage());
            }
            return null;
        }
        throw new RuntimeException("Incorrect id!");
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        if (id > 0){
            try {
                return doctorDao.getAllDoctorsByDepartmentId(id);
            }
            catch (NotFoundException e){
                System.out.println(e.getMessage());
            }
            return null;
        }
        throw new RuntimeException("Incorrect id!");

    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        if (hospitalId>0){
            try{
               return doctorDao.add(hospitalId, doctor);
            }
            catch (NotFoundException e){
                System.out.println(e.getMessage());

            }
            return null;

        }
        throw new RuntimeException("Incorrect id!");
    }

    @Override
    public void removeById(Long id) {
        if (id > 0 ) doctorDao.removeById(id);
        else throw new RuntimeException("Incorrect id!");
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        try{
            return doctorDao.updateById(id, doctor);
        }
        catch (NotFoundException r){
            System.out.println(r.getMessage());
            return null;
        }
    }
}
