package lt.freeland.common.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lt.freeland.common.domain.User;
import lt.freeland.common.domain.UserProfile;
import lt.freeland.common.dto.UserDto;
import lt.freeland.common.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author freeland
 */
public class UsersMapper {
    
    private final CountriesMapper countriesMapper;

    @Autowired
    public UsersMapper(CountriesMapper countriesMapper) {
        this.countriesMapper = countriesMapper;
    }   

    public List<UserDto> usersToDtoMapper(List<User> users) {
        return users
                .stream()
                .map(this::userToDtoMapper)
                .collect(Collectors.toList());
    }

    public UserProfileDto userProfileToDtoMapper(UserProfile userProfile) {
        return Optional
                    .of(userProfile)
                    .map(up -> UserProfileDto
                                .builder()
                                .userId(up.getUserId())
                                .address(up.getAddress())
                                .birthday(up.getBirthday())
                                .city(up.getCity())
                                .firstName(up.getFirstName())
                                .lastName(up.getLastName())
                                .nationality(countriesMapper.countryToDtoMapper(up.getNationality()))
                                .phone(up.getPhone())
                                .user(userToDtoMapper(up.getUser()))  
                                .build()
                    )
                    .orElse(null);
    }

    public UserDto userToDtoMapper(User user) {
        return Optional
                    .of(user)
                    .map(u -> UserDto
                                .builder()
                                .userId(u.getUserId())
                                .username(u.getUsername())
                                .userProfile(this.userProfileToDtoMapper(u.getUserProfile()))
                                .status(u.getStatus())
                                .email(u.getEmail())
                                .createdDate(u.getCreatedDate())
                                .editedDate(u.getEditedDate())
                                .build()
                )
                .orElse(null);
    }
}
