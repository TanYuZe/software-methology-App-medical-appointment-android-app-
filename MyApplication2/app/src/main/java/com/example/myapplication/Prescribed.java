package com.example.myapplication;

public class Prescribed
{
    String _ID;
    Long drugID;
    int quantity;
    String token;
    boolean isPrescribed;

//    public Prescribed(String _ID, Long drugID, int quantity, int quantity) {
//        this.drugId = drugId;
//        this.drugPrescribed = drugPrescribed;
//        this.dosage = dosage;
//        this.quantity = quantity;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public Long getDrugID() {
        return drugID;
    }

    public void setDrugID(Long drugID) {
        this.drugID = drugID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPrescribed() {
        return isPrescribed;
    }

    public void setPrescribed(boolean prescribed) {
        isPrescribed = prescribed;
    }
}
