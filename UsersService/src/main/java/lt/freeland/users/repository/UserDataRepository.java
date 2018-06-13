/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.freeland.users.repository;

import lt.freeland.users.beans.UserProfile;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author freeland
 */
public interface UserDataRepository extends CrudRepository<UserProfile, Long> {
    
    UserProfile findByUserId(Long uid);
    UserProfile findByUser_username(String username);
    
}
