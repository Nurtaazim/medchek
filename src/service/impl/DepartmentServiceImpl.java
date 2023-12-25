package service.impl;

import exception.NotFoundException;
import model.Department;
import repository.impl.DepartmentDaoImpl;
import service.DepartmentService;
import service.GenericService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService, GenericService<Department> {
    private DepartmentDaoImpl departmentDao;

    public DepartmentServiceImpl(DepartmentDaoImpl departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        if (id >= 1){
            try{
                return departmentDao.getAllDepartmentByHospital(id);
            }
            catch (NotFoundException e){
                System.out.println(e.getMessage());
            }
            return null;
        }
        throw new RuntimeException("Incorrect id!");
    }

    @Override
    public Department findDepartmentById(long id) {
        if (id < 1) throw new RuntimeException("Incorrect information!");
        try{
            return departmentDao.findDepartmentById(id);
        }
        catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        if (!(hospitalId >=1) || department.getDepartmentName().isBlank()) throw new RuntimeException("Name is blank or incorrect id! ");
        try{
            return departmentDao.add(hospitalId, department);
        }
        catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(Long id) {
        if (id >= 1) departmentDao.removeById(id);
        else throw new RuntimeException("Incorrect id! ");
    }

    @Override
    public String updateById(Long id, Department department) {
        if (id<1 || department.getDepartmentName().isBlank()) throw new RuntimeException("Incorrect information !");
        return departmentDao.updateById(id, department);
    }
}
