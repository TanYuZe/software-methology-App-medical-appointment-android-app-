package com.example.myapplication;

public class Prescription {
    Long drugId;
    String drugPrescribed;
    Long dosage;
    int quantity = 0;
    public Prescription(Long drugId, String drugPrescribed, Long dosage, int quantity) {
        this.drugId = drugId;
        this.drugPrescribed = drugPrescribed;
        this.dosage = dosage;
        this.quantity = quantity;
    }

    public Prescription() {

    }


    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
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

    public void setQuantity(int text)
    {
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }


    @Override
    public String toString() {
        return "Drugs Selected: " + drugPrescribed + '\n' +
                "Quantity :" + quantity + "\n";
    }
}
