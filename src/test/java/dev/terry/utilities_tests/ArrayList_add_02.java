package dev.terry.utilities_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import dev.terry.utilities.ArrayList;
import dev.terry.utilities.List;

/**
 * @author Khenan_Terry
 */
public class ArrayList_add_02 {
    /**
     * .add() works for many element insertions, and .size() gives the
     * correct size of the ArrayList.
     */
    @Test
    void check_many_add(){
        List<String> stringArrayList = new ArrayList<String>();
        for(int i=0; i<1000; i++){
            stringArrayList.add("String Test");
        }

        Assertions.assertEquals(1000,stringArrayList.size());
    }
}
