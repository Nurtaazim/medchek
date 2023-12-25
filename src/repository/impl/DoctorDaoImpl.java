package repository.impl;

import database.Database;
import exception.NotFoundException;
import generate.Id;
import model.Department;
import model.Doctor;
import model.Hospital;
import service.DoctorService;
import service.GenericService;

import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorService, GenericService<Doctor> {
    private Database database;

    public DoctorDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(id)) return doctor;
            }
        }
        throw new NotFoundException("Doctor with this id not found!");
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        List<Doctor> doctors = new ArrayList<>();
        for (Hospital hospital : database.getHospitals()) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(departmentId)){
                    for (Doctor doctor : hospital.getDoctors()) {
                        for (Long l : doctorsId) {
                            if (l.equals(doctor.getId())) doctors.add(doctor);
                        }
                    }
                }
            }
        }
        for (Hospital hospital : database.getHospitals()) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(departmentId)){
                    department.getDoctors().addAll(doctors);
                    return "Successfully assigned!";
                }
            }
        }
        throw new NotFoundException("We can't find object with this id!");
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(id)) return hospital.getDoctors();
        }
        throw new NotFoundException("Hospital with this id not found!");
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(id)) return department.getDoctors();
            }
        }
        throw new NotFoundException("Department with this id not found");
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(hospitalId)) {
                doctor.setId(Id.generateId());
                hospital.getDoctors().add(doctor);
                return "Successfully added!";
            }

        }
        throw new NotFoundException("Hospital with this id not found!");
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            Loop:
            for (Department department : hospital.getDepartments()) {
                for (Doctor doctor : department.getDoctors()) {
                    if (doctor.getId().equals(id)){
                        department.getDoctors().remove(doctor);
                        break Loop;
                    }
                }
            }
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(id)){
                    hospital.getDoctors().remove(doctor);
                    System.out.println("Successfully removed!");
                    return;
                }
            }
        }
        throw new NotFoundException("Doctor with this id not found!");
    }

    @Override
    public String updateById(Long id, Doctor doctor1) {
        for (Hospital hospital : database.getHospitals()) {
            Loop:
            for (Department department : hospital.getDepartments()) {
                for (Doctor doctor : department.getDoctors()) {
                    if (doctor.getId().equals(id)){
                        doctor.setFirstName(doctor1.getFirstName());
                        doctor.setLastName(doctor1.getLastName());
                        doctor.setExperienceYear(doctor1.getExperienceYear());
                        break Loop;
                    }
                }
            }
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(id)){
                    doctor.setFirstName(doctor1.getFirstName());
                    doctor.setLastName(doctor1.getLastName());
                    doctor.setExperienceYear(doctor1.getExperienceYear());
                    return "Successfully updated!";
                }
            }
        }
        throw new NotFoundException("Doctor with this id not found!");
    }
}
