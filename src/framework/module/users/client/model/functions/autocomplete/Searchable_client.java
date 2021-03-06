package framework.module.users.client.model.functions.autocomplete;

import java.util.Collection;

/**
 * 
 * @author angel
 * @param <E>
 * @param <V> 
 */
public interface Searchable_client<E, V>{
    
    /**
     * Searches an underlying inventory of items consisting of type E
     * @param value A searchable value of type V
     * @return A Collection of items of type E.
     */
    
    public Collection<E> search(V value);
}
