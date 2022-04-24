package com.example.tracker;

public class User {


    User(String VehicleNo, String OperatorName, String Starttime, String Endtime, String location, String shift) {
        this.VehicleNo = VehicleNo;
        this.OperatorName = OperatorName;
        this.Starttime = Starttime;
        this.Endtime = Endtime;
        this.Location = location;
        this.Shift = shift;
    }

    String VehicleNo, OperatorName, Starttime, Endtime, Location, Shift;

    public String getVehicleNo() {
        return VehicleNo;
    }

    public String getOperatorName() {
        return OperatorName;
    }

    public String getStarttime() {
        return Starttime;
    }

    public String getEndtime() {
        return Endtime;
    }

    public String getLocation() {
        return Location;
    }

    public String getShift() {
        return Shift;
    }


}
