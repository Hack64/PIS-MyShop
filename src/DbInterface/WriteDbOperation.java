package DbInterface;

public class WriteDbOperation implements IDbOperation{

    IDbConnection conn = DbConnection.getInstance();
    String sql;

    public WriteDbOperation(String sql){
        this.sql = sql;
    }

    @Override
    public Integer execute() {
        return conn.executeUpdate(sql);
    }

    @Override
    public void close() {
        conn.close();
    }
}