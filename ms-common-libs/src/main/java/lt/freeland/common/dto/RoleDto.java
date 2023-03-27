package lt.freeland.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author freeland
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"users"})
public class RoleDto {

    private Long id;
    private String name;
    private String description;

}
