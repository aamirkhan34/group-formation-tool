package core.datasource;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import java.sql.SQLException;

public class TestDevDatabase {
    @Test
    public void  testGetConnection(){
        try {
            assertFalse(DevDatabase.getInstance().getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
