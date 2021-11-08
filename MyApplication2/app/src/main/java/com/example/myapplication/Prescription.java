package com.example.myapplication;

public class Prescription
{
    int drugId;
    String drugPrescribed;
    Long dosage;

    public Prescription() {

    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getDrugPrescribed() {
        return drugPrescribed;
    }

    public void setDrugPrescribed(String drugPrescribed) {
        this.drugPrescribed = drugPrescribed;
    }

    public Long getDosage() {
        return dosage;
    }

    public void setDosage(Long dosage) {
        this.dosage = dosage;
    }
}
