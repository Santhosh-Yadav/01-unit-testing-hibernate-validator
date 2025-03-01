package in.santhosh.entity;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Demographics {

    public Demographics() {
    }

    @NotNull(message = "Mandatory field is missing - name")
    private String name;

    @NotNull(message = "Mandatory field is missing - pan")
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]$" , message = "Invalid field data - pan")
    private String pan;

    @NotNull(message = "Mandatory field is missing - gender")
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "Demographics{" +
                "name='" + name + '\'' +
                ", pan='" + pan + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
