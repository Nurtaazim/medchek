package database;

import model.Hospital;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Hospital> hospitals = new ArrayList<>();

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }
}
