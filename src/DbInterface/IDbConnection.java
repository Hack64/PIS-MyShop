package DbInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface IDbConnection {

    ResultSet executeQuery(String sqlStatement);
    int executeUpdate(String sqlStatement);
    void close();
}
