/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.freeland.common.exceptions;

/**
 *
 * @author freeland
 */
public class EntityListEmptyException extends RuntimeException {

    public EntityListEmptyException(Class clazz) {
        super("Entity " + clazz.getSimpleName() + " list is empty ");
    }
    
}
