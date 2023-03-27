package lt.freeland.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lt.freeland.common.dto.UserStatus;
import lombok.Data;

/**
 *
 * @author freeland
 */
@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"userProfile", "password"})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="user_seq", sequenceName="users_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_seq")
    @Column(name = "id")
    private Long userId;

    @NotEmpty
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;

    @Email
    @NotEmpty
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private UserStatus status;
    
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    
    @Column(name = "edited_date")
    private LocalDateTime editedDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private UserProfile userProfile;
    
    public boolean isEnabled() {
        return status == UserStatus.ENABLED;
    }   
    
    public boolean isBlocked() {
        return status == UserStatus.BLOCKED;
    } 
}
