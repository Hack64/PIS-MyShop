package DbInterface;

import java.util.ArrayList;

public class DbOperationExecutor {

    private ArrayList<IDbOperation> dbOperations = new ArrayList<>();

    public Object executeOperation(IDbOperation dbOperation){
        dbOperations.add(dbOperation);
        return dbOperation.execute();
    }

    public void closeOperation(IDbOperation dbOperation){
        dbOperation.close();
    }

}
