package by.it_academy.jd2.service.dto.metrics;

/**
 * Created by Vitali Tsvirko
 */
public class UsersMetricsDTO {

    private Long usersTotal;

    private Long patientsTotal;

    private Long doctorsTotal;

    private Double patientsAvgAgeYear;

    private Double doctorsAvgAgeYear;

    public Long getUsersTotal() {
        return usersTotal;
    }

    public void setUsersTotal(Long usersTotal) {
        this.usersTotal = usersTotal;
    }

    public Long getPatientsTotal() {
        return patientsTotal;
    }

    public void setPatientsTotal(Long patientsTotal) {
        this.patientsTotal = patientsTotal;
    }

    public Long getDoctorsTotal() {
        return doctorsTotal;
    }

    public void setDoctorsTotal(Long doctorsTotal) {
        this.doctorsTotal = doctorsTotal;
    }

    public Double getPatientsAvgAgeYear() {
        return patientsAvgAgeYear;
    }

    public void setPatientsAvgAgeYear(Double patientsAvgAgeYear) {
        this.patientsAvgAgeYear = patientsAvgAgeYear;
    }

    public Double getDoctorsAvgAgeYear() {
        return doctorsAvgAgeYear;
    }

    public void setDoctorsAvgAgeYear(Double doctorsAvgAgeYear) {
        this.doctorsAvgAgeYear = doctorsAvgAgeYear;
    }

}
