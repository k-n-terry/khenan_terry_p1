package dev.terry.utilities_tests;

import dev.terry.entities.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import dev.terry.utilities.UniqueIdMD5;

/**
 * @author Khenan_Terry
 */
 public class UniqueIdMD5_makeUniqueId{
    @Test
    void can_make_unique_id(){
        /* set fields with different but similar values */
        // Employee 01:
        String fname01 = "Khenan";
        String lname01 = "Terry";
        String id01 = new UniqueIdMD5().makeUniqueId(fname01,lname01);

        // Employee 02:
        String fname02 = "Kenan";
        String lname02 = "Terry";
        String id02 = new UniqueIdMD5().makeUniqueId(fname02,lname02);

        // Employee 03:
        String fname03 = "Keenan";
        String lname03 = "Terry";
        String id03 = new UniqueIdMD5().makeUniqueId(fname03,lname03);

        // check uniquity
        Assertions.assertNotEquals(id01, id02);
        Assertions.assertNotEquals(id01, id03);
        Assertions.assertNotEquals(id02, id03);
    }
 }
