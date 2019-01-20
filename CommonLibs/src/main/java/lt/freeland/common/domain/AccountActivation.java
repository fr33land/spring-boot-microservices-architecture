package lt.freeland.common.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author r.sabaliauskas
 */
@Data
@Entity
@Table(name = "account_activation")
public class AccountActivation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column(name = "activation_token")
    private String activationToken;
    
    @NotNull
    @Column(name = "expire_date")
    private LocalDateTime expireDate;
}
