package com.iteso.wapi.beans;

public class Period {

    private int idPeriod;
    private String namePeriod;
    private String userName;

    public Period() {
        this.idPeriod = 0;
        this.namePeriod = "";
        this.userName = "";
    }

    public Period(int idPeriod, String namePeriod, String userName) {
        this.idPeriod = idPeriod;
        this.namePeriod = namePeriod;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Period{" +
                "idPeriod=" + idPeriod +
                ", namePeriod='" + namePeriod + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public int getIdPeriod() {
        return idPeriod;
    }

    public void setIdPeriod(int idPeriod) {
        this.idPeriod = idPeriod;
    }

    public String getNamePeriod() {
        return namePeriod;
    }

    public void setNamePeriod(String namePeriod) {
        this.namePeriod = namePeriod;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
