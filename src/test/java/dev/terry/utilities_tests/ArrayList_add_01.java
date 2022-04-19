package dev.terry.utilities_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import dev.terry.utilities.ArrayList;
import dev.terry.utilities.List;

/**
 * @author Khenan_Terry
 */
public class ArrayList_add_01 {
    /**
     * .size() method returns the size of the "letterArrayList" object.
     */
    @Test
    void check_size_after_add(){
        List<String> letterArrayList = new ArrayList();
        letterArrayList.add("X");
        letterArrayList.add("Y");
        letterArrayList.add("Z");

        Assertions.assertEquals(3,letterArrayList.size());
    }
}
