package DAO.Posizione;

import DAO.Prodotto.IProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbOperation;
import DbInterface.ReadDbOperation;
import DbInterface.WriteDbOperation;
import Model.Posizione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PosizioneDAO implements IPosizioneDAO {

    private final static PosizioneDAO instance = new PosizioneDAO();

    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;
    private ResultSet rs;

    private PosizioneDAO(){
        executor = null;
        dbOperation = null;
        sql = null;
        rs = null;
    }

    public static PosizioneDAO getInstance() {
        return instance;
    }

    @Override
    public Posizione findByID(int idPosizione) {
        executor = new DbOperationExecutor();
        sql = "SELECT idPosizione, scaffale, corsia, idProdotto FROM Posizione WHERE idPosizione = '" + idPosizione + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        Posizione posizione = new Posizione();
        int idProdotto=-1;
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1) {
                posizione.setIdPosizione(rs.getInt("idPosizione"));
                posizione.setCorsia(rs.getInt("corsia"));
                posizione.setScaffale(rs.getInt("scaffale"));
                idProdotto = rs.getInt("idProdotto");
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            executor.closeOperation(dbOperation);
        }
        posizione.setProdotto(prodottoDAO.findByID(idProdotto));
        return posizione;
    }

    @Override
    public Posizione findByProductID(int idProdotto) {
        executor = new DbOperationExecutor();
        sql = "SELECT idPosizione, scaffale, corsia, idProdotto FROM Posizione WHERE idProdotto = '" + idProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        Posizione posizione = new Posizione();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1) {
                posizione.setIdPosizione(rs.getInt("idPosizione"));
                posizione.setCorsia(rs.getInt("corsia"));
                posizione.setScaffale(rs.getInt("scaffale"));
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            executor.closeOperation(dbOperation);
        }
        posizione.setProdotto(prodottoDAO.findByID(idProdotto));
        return posizione;
    }

    @Override
    public int add(Posizione posizione) {
        executor = new DbOperationExecutor();
        sql = "INSERT INTO Posizione (scaffale, corsia, idProdotto) VALUES ('" + posizione.getScaffale() + "','" + posizione.getCorsia() + "','" + posizione.getProdotto().getIdProdotto() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int)executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idPosizione) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM Posizione WHERE idPosizione = '" + idPosizione + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int)executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Posizione posizione) {
        executor = new DbOperationExecutor();
        sql = "UPDATE Posizione SET scaffale = '" + posizione.getScaffale() + "', corsia = '" + posizione.getCorsia() + "' WHERE idProdotto = '" + posizione.getIdPosizione() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int)executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
