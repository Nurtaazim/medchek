package repository.impl;

import database.Database;
import exception.NotFoundException;
import generate.Id;
import model.Department;
import model.Hospital;
import repository.GenericDao;
import service.DepartmentService;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentService, GenericDao<Department> {
    private Database database;

    public DepartmentDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(hospitalId)){
                department.setId(Id.generateId());
                hospital.getDepartments().add(department);
                return "Successfully added!";
            }
        }
        throw new NotFoundException("Hospital with this id not found!");
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(id)){
                    hospital.getDepartments().remove(department);
                    System.out.println("Successfully removed!");
                    return;

                }
            }
        }
        throw new NotFoundException("Department with this id not found!");
    }

    @Override
    public String updateById(Long id, Department department) {
        for (Hospital hospital : database.getHospitals()) {
            for (Department hospitalDepartment : hospital.getDepartments()) {
                if(hospitalDepartment.getId().equals(id)){
                    hospitalDepartment.setDepartmentName(department.getDepartmentName());
                    return "Successfully updated!";
                }
            }

        }
        throw new NotFoundException("Department with this id not found!");
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(id)){
                return hospital.getDepartments();
            }
        }
        throw new NotFoundException("Hospital with this id is not!");
    }

    @Override
    public Department findDepartmentById(long id) {
        for (Hospital hospital : database.getHospitals()) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(id)) return department;
            }
        }
        throw new NotFoundException("Department with this id not found!");
    }
}
