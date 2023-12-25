import database.Database;
import enums.Gender;
import exception.NotFoundException;
import generate.Id;
import model.Department;
import model.Doctor;
import model.Hospital;
import model.Patient;
import repository.impl.DepartmentDaoImpl;
import repository.impl.DoctorDaoImpl;
import repository.impl.HospitalDaoImpl;
import repository.impl.PatientDaoImpl;
import service.impl.DepartmentServiceImpl;
import service.impl.DoctorServiceImpl;
import service.impl.HospitalServiceImpl;
import service.impl.PatientServiceImpl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerForNum = new Scanner(System.in);
        Database database = new Database();
        HospitalDaoImpl hospitalDao = new HospitalDaoImpl(database);
        HospitalServiceImpl hospitalService = new HospitalServiceImpl(hospitalDao);
        PatientDaoImpl patientDao = new PatientDaoImpl(database);
        PatientServiceImpl patientService = new PatientServiceImpl(patientDao);
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl(database);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentDao);
        DoctorDaoImpl doctorDao = new DoctorDaoImpl(database);
        DoctorServiceImpl doctorService = new DoctorServiceImpl(doctorDao);
        while (true) {
            System.out.println("""
                    1 - Add hospital
                    2 - Find hospital by id
                    3 - Get all hospital
                    4 - Get all patient from hospital
                    5 - Delete hospital by id
                    6 - Get all hospital by address
                    7 - Add department
                    8 - Find department by id
                    9 - Get all department from hospital
                    10 - Delete department by id
                    11 - Update department's name
                    12 - Add doctor
                    13 - Find doctor by id
                    14 - Get all doctors by hospital id
                    15 - Get all doctors by department id
                    16 - Assign doctor to department
                    17 - Remove by id
                    18 - Update by id
                    0 - Exit
                    """);
            switch (scanner.nextLine()) {
                case "1" -> {
                    Hospital hospital = new Hospital();
                    System.out.println("Write hospital name: ");
                    hospital.setHospitalName(scanner.nextLine());
                    System.out.println("Write hospital address: ");
                    hospital.setAddress(scanner.nextLine());
                    try {
                        System.out.println(hospitalService.addHospital(hospital));
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                }
                case "2" -> {
                    boolean istrue = true;
                    Loop:
                    while(istrue){
                    System.out.println("Write hospital id: ");
                    long id = 0;
                    try {
                        id = new Scanner(System.in).nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break Loop;
                    try {
                        System.out.println(hospitalService.findHospitalById(id));
                    }
                    catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    istrue = false;
                    }
                }
                case "3" -> {
                    System.out.println(hospitalService.getAllHospital());
                }
                case "4" -> {
                    boolean istrue = true;
                    Loop:
                    while (istrue) {
                        System.out.println("Write hospital id: ");
                        long id = 0;
                        try {
                            id = new Scanner(System.in).nextLong();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        if (id == 0) break Loop;
                        try {
                            System.out.println(hospitalService.getAllPatientFromHospital(id));
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        istrue = false;
                    }

                }
                case "5" ->{
                    boolean istrue = true;
                    Loop:
                    while (istrue) {
                        System.out.println("Write hospital id: ");
                        long id = 0;
                        try {
                            id = new Scanner(System.in).nextLong();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        if (id == 0) break Loop;
                        try {
                            System.out.println(hospitalService.deleteHospitalById(id));
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        istrue = false;
                    }
                }
                case "6"->{
                    try {
                        System.out.println(hospitalService.getAllHospitalByAddress(scanner.nextLine()));
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "7"->{
                        System.out.println("Write the ID of the hospital you want to add: ");
                        long id = 0;
                        try {
                            id = new Scanner(System.in).nextLong();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        if (id == 0) break ;
                        Department department = new Department();
                        System.out.println("Write new department name :");
                        department.setDepartmentName(scanner.nextLine());
                        try {
                            System.out.println(departmentService.add(id, department));
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                }
                case "8"->{
                    System.out.println("Write department id: ");
                    long id = 0;
                    try {
                        id = new Scanner(System.in).nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break;
                    try {
                        System.out.println(departmentService.findDepartmentById(id));
                    }
                    catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "9"->{
                    System.out.println("Write hospital id: ");
                    long id = 0;
                    try {
                        id = new Scanner(System.in).nextLong();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break ;
                    try {
                        System.out.println(departmentService.getAllDepartmentByHospital(id));
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "10"->{
                    System.out.println("Write department id: ");
                    long id = 0;
                    try {
                        id = new Scanner(System.in).nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break;
                    try {
                        departmentService.removeById(id);
                    }
                    catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "11"->{
                    System.out.println("Write department id:");
                    long id = 0;
                    try {
                        id = new Scanner(System.in).nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break;
                    System.out.println("Write new name:");
                    try {
                        System.out.println(departmentService.updateById(id, new Department(null, scanner.nextLine(), null)));
                    }
                    catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "12" ->{
                    System.out.println("Write the ID of the hospital you want to add: ");
                    long id = 0;
                    try {
                        id = new Scanner(System.in).nextLong();
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break;
                    Doctor doctor = new Doctor();
                    System.out.println("Write doctor's name: ");
                    String name = scanner.nextLine();
                    if (name.isBlank()) break;
                    else doctor.setFirstName(name);
                    System.out.println("Write surname:");
                    String sname = scanner.nextLine();
                    if (sname.isBlank()) break;
                    else doctor.setLastName(sname);
                    System.out.println("Write gender:");
                    String gen = scanner.nextLine();
                    if (gen.equalsIgnoreCase("m") || gen.equalsIgnoreCase("male")) doctor.setGender(Gender.MALE);
                    else if (gen.equalsIgnoreCase("f") || gen.equalsIgnoreCase("female")) {
                        doctor.setGender(Gender.FEMALE);
                    }
                    else {
                        System.out.println("Male(M) or Female(F)") ;
                        break;
                    }
                    System.out.println("Doctor's experience year :");
                    int san = 0;
                    int san1 = 0;
                    try {
                        san = new Scanner(System.in).nextInt();
                    } catch (Exception e) {
                        san1 = san1 + 1;
                        System.out.println(e.getMessage());
                    }
                    if (san<0 || san1==1) break;
                    else doctor.setExperienceYear(san);

                    try {
                        System.out.println(doctorService.add(id, doctor));
                    }
                    catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }

                }
                case "13"->{
                    System.out.println("Write doctor id:");
                    long id = 0;
                    try{
                        id = new Scanner(System.in).nextLong();
                    }
                    catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break;

                    try {
                        System.out.println(doctorService.findDoctorById(id));
                    }
                    catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "14" ->{
                    System.out.println("Write hospital id: ");
                    long id = 0;
                    try {
                        id = new Scanner(System.in).nextLong();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break ;
                    try {
                        System.out.println(doctorService.getAllDoctorsByHospitalId(id));
                    }
                    catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "15"->{
                    System.out.println("Write department id: ");
                    long id = 0;
                    try {
                        id = new Scanner(System.in).nextLong();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break ;
                    try {
                        System.out.println(doctorService.getAllDoctorsByDepartmentId(id));
                    }
                    catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "16" ->{
                    System.out.println("Write the ID of the department you want to assign: ");
                    long id = 0;
                    try {
                        id = new Scanner(System.in).nextLong();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break ;
                    List<Long> ids = new ArrayList<>();
                    Loop:
                    while (true){
                        System.out.println("Ввыведите id тех докторов которых хотите назначить в эту отделению! \nЕсли закончили можете нажать на 0 \nЗамечание: доктора и отделение должны быть на одной больнице!");
                        long san = new Scanner(System.in).nextLong();
                        if (san == 0 ) break Loop;
                        ids.add(san);
                    }
                    System.out.println(doctorService.assignDoctorToDepartment(id, ids));
                }
                case "17"->{
                    System.out.println("Write doctor id:");
                    long id = 0;
                    try{
                        id = new Scanner(System.in).nextLong();
                    }
                    catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break;

                    try {
                        doctorService.removeById(id);
                    }
                    catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "18"->{
                    System.out.println("Write doctor id:");
                    long id = 0;
                    try{
                        id = new Scanner(System.in).nextLong();
                    }
                    catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    if (id == 0) break;
                    Doctor updateDoctor = new Doctor();
                    System.out.println("Write new doctor name:");
                    updateDoctor.setFirstName(scanner.nextLine());
                    System.out.println("Write new surename:");
                    updateDoctor.setLastName(scanner.nextLine());
                    System.out.println("Write new experience year:");
                    updateDoctor.setExperienceYear(scannerForNum.nextInt());
                    System.out.println(doctorService.updateById(id, updateDoctor));
                }
                case "0" -> {
                    System.exit(0);
                }
                default -> System.out.println("!Incorrect symbol!");
            }
        }

    }
}