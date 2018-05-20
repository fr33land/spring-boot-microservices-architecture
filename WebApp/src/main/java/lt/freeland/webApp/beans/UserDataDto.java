package lt.freeland.webApp.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author freeland
 */

public class UserDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;    
    private String firstName;
    private String lastName;
    private Date birthday;
    private String city;
    private String address;
    private String phone;
    private CountriesDto nationality;

    public UserDataDto() {
    }

    public UserDataDto(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CountriesDto getNationality() {
        return nationality;
    }

    public void setNationality(CountriesDto nationality) {
        this.nationality = nationality;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDataDto)) {
            return false;
        }
        UserDataDto other = (UserDataDto) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lt.freeland.users.beans.UsersData[ userId=" + userId + " ]";
    }
    
}
