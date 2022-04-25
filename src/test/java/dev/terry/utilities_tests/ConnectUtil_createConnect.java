package dev.terry.utilities_tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import dev.terry.utilities.*;

import java.sql.Connection;

/**
 * @author Khenan_Terry
 */
public class ConnectUtil_createConnect {
    @Test
    void can_connect(){
        Connection conn = ConnectUtil.createConnect();
        Assertions.assertNotNull(conn);
    }
}
