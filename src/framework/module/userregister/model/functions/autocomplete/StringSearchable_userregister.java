package framework.module.userregister.model.functions.autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author angel
 */
public class StringSearchable_userregister implements Searchable_userregister<String,String>{
    
    private List<String> terms = new ArrayList<String>();

    /**
     * Constructs a new object based upon the parameter terms. 
     * @param terms The inventory of terms to search.
     */

    public StringSearchable_userregister(List<String> terms){
        this.terms.addAll(terms);
    }

    @Override
    public Collection<String> search(String value) {
        List<String> founds = new ArrayList<String>();
        for ( String s : terms ){
            if ( s.indexOf(value) == 0 )
                founds.add(s);
        }
        return founds;
    }
}
