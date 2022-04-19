package dev.terry.utilities_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import dev.terry.utilities.ArrayList;
import dev.terry.utilities.List;

/**
 * @author Khenan_Terry
 */
public class ArrayList_add_get{
    /**
     * .size() method returns the size of the "letterArrayList" object.
     */
    @Test
    void check_size_after_add() {
        List<String> letterArrayList = new ArrayList();
        letterArrayList.add("X");
        letterArrayList.add("Y");
        letterArrayList.add("Z");

        Assertions.assertEquals(3, letterArrayList.size());
    }

    /**
     * .get() method returns the correct element at the specified index
     */
    @Test
    void check_get_by_index() {
        List<String> letterArrayList = new ArrayList();
        letterArrayList.add("X");
        letterArrayList.add("Y");
        letterArrayList.add("Z");

        String element = letterArrayList.get(1);

        Assertions.assertEquals("Y", element);
    }
}