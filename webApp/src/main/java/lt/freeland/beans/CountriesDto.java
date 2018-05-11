package lt.freeland.beans;

import java.io.Serializable;

/**
 *
 * @author freeland
 */
public class CountriesDto implements Serializable {

    private static final long serialVersionUID = 1L;
    

    private Integer id;
    private String name;
    private String isoCode;
    private String phoneCode;

    public CountriesDto() {
    }

    public CountriesDto(Integer id) {
        this.id = id;
    }

    public CountriesDto(Integer id, String name, String isoCode) {
        this.id = id;
        this.name = name;
        this.isoCode = isoCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CountriesDto)) {
            return false;
        }
        CountriesDto other = (CountriesDto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lt.freeland.beans.Countries[ id=" + id + " ]";
    }
    
}
