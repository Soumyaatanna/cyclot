package hcl.esg.ebike.application;

import android.widget.Button;

public class Cycles {
    private String cid;

    public String getAvailability() {
        return availability;
    }

    private String availability;

    public String getLocation() {
        return location;
    }

    public String getColor() {
        return color;
    }

    private String color;
    private String location,RequestTime,ReturnTime;

    private Button Damage,Approve,Repaired;

    public String getCid() {
        return cid;
    }

    public Button getRepaired() {
        return Repaired;
    }

    public void setRepaired(Button repaired) {
        Repaired = repaired;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Button getDamage() {
        return Damage;
    }

    public String getRequestTime() {
        return RequestTime;
    }

    public String getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(String returnTime) {
        ReturnTime = returnTime;
    }

    public void setRequestTime(String requestTime) {
        RequestTime = requestTime;
    }

    public Button getApprove() {
        return Approve;
    }

    public void setApprove(Button approve) {
        Approve = approve;
    }

    public void setDamage(Button damage) {
        Damage = damage;
    }

    public Cycles() {
        // Default constructor required for calls to DataSnapshot.getValue(Cycles.class)
    }
    public Cycles(String cid,String RequestTime,String ReturnTime,Button Damage,Button Approve) {

        this.cid = cid;
        this.Damage = Damage;
        this.Approve = Approve;
        this.RequestTime = RequestTime;
        this.ReturnTime = ReturnTime;

    }
    public Cycles(String cid,Button Repaired) {

        this.cid = cid;
        this.Repaired = Repaired;


    }

    public Cycles(String cid, String availability, String color, String location) {
        this.location = location;
        this.cid = cid;
        this.availability = availability;
        this.color = color;
    }

}
