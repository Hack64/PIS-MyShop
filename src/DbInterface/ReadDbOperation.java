package DbInterface;

import java.sql.ResultSet;

public class ReadDbOperation implements IDbOperation{

    IDbConnection conn = DbConnection.getInstance();
    private String sql;

    public ReadDbOperation(String sql){
        this.sql = sql;
    }

    @Override
    public ResultSet execute() {
        return conn.executeQuery(sql);
    }

    public void close(){
        conn.close();
    }
}
