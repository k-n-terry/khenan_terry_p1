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
        Employee khenan = new Employee();
        Employee brandon = new Employee();

        // set needed parameters
        // firstname
        khenan.setFirstname("Khenan");
        brandon.setFirstname("Brandon");
        // lastname
        khenan.setLastname("Terry");
        brandon.setLastname("Terry");
        // password
        khenan.setKeyword("abc123");
        brandon.setKeyword("abc123");

        UniqueIdMD5 uniqueId_01 = new UniqueIdMD5(khenan);
        UniqueIdMD5 uniqueId_02 = new UniqueIdMD5(brandon);

        khenan.setEmpId(uniqueId_01.makeUniqueId());
        brandon.setEmpId(uniqueId_02.makeUniqueId());

        // check theoretical equality
        String theoretical_01 = "0aa3105b5d68cddc6c1062755b85999f";
        Assertions.assertEquals(khenan.getEmpId(), theoretical_01.toUpperCase());

        String theoretical_02 = "1332c0c759cd30f95c1ee92d1dfd5c7e";
        Assertions.assertEquals(brandon.getEmpId(), theoretical_02.toUpperCase());

        // check uniquity
        Assertions.assertNotEquals(khenan.getEmpId(), brandon.getEmpId());
    }
 }
