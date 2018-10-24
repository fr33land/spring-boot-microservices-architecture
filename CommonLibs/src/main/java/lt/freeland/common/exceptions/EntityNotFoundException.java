package lt.freeland.common.exceptions;

/**
 *
 * @author freeland
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String searchParam, Object id) {
        super("Entity " + clazz.getSimpleName() + " with " + searchParam + ":" + id.toString() + " not found");
    }
    
}
