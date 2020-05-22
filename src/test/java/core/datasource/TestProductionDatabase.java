package core.datasource;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestProductionDatabase {
    @Test
    public void  testGetConnection(){
        try {
            assertFalse(ProductionDatabase.getInstance().getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
