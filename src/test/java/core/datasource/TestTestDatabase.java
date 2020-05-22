package core.datasource;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestTestDatabase {
    @Test
    public void  testGetConnection(){
        try {
            assertFalse(TestDatabase.getInstance().getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
