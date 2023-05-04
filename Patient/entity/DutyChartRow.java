package Patient.entity;


import java.io.Serializable;

public class DutyChartRow implements Serializable {

    private String doctorName;
    private String doctorDutyTime;
    private String doctorMobileNo;

    public DutyChartRow(String doctorName,String doctorDutyTime,String doctorMobileNo ){
        this.doctorName = doctorName;
        this.doctorDutyTime =doctorDutyTime;
        this.doctorMobileNo = doctorMobileNo;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorDutyTime() {
        return doctorDutyTime;
    }

    public String getDoctorMobileNo() {
        return doctorMobileNo;
    }
}
