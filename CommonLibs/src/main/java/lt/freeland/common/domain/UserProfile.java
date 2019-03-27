package lt.freeland.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author freeland
 */
@Data
@Entity
@Table(name = "users_profiles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;
    
    @JoinColumn(name = "nationality", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Countries nationality;
    
    @MapsId
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;    
}
