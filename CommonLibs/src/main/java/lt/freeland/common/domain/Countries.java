package lt.freeland.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author freeland
 */
@Entity
@Table(name = "countries")
@JsonIgnoreProperties({"usersProfiles"})
public class Countries implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "iso_code")
    private String isoCode;
    
    @Size(max = 10)
    @Column(name = "phone_code")
    private String phoneCode;
    
    @OneToMany(mappedBy = "nationality", fetch = FetchType.LAZY)
    private List<UserProfile> usersProfiles;

    public Countries() {
    }

    public Countries(Integer id) {
        this.id = id;
    }

    public Countries(Integer id, String name, String isoCode) {
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

    public List<UserProfile> getUsersProfiles() {
        return usersProfiles;
    }

    public void setUsersProfiles(List<UserProfile> usersProfiles) {
        this.usersProfiles = usersProfiles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Countries)) {
            return false;
        }
        Countries other = (Countries) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lt.freeland.users.beans.Countries[ id=" + id + " ]";
    }
    
}
