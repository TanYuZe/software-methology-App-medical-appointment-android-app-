package com.example.myapplication;

public class Prescribed
{
    String _ID;
    Long drugID;
    int quantity;
    String token;
    boolean isPrescribed;
    String Date;
    String DrugName;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDrugName() {
        return DrugName;
    }

    public void setDrugName(String drugName) {
        DrugName = drugName;
    }

    public Prescribed()
    {

    }

    public Prescribed(String _ID, Long drugID, String token, int quantity, boolean isPrescribed) {
        this.drugID = drugID;
        this._ID = _ID;
        this.isPrescribed = isPrescribed;
        this.token = token;
        this.quantity = quantity;
    }


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
