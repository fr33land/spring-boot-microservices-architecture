package lt.freeland.common.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author r.sabaliauskas
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_password_reset")
public class PasswordResetToken implements Serializable {
    
   private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "reset_token")
    private String resetToken; 
    
    @Column(name ="expire_date")
    private LocalDateTime expireDate;
}
