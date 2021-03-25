package controller;

import handler.ConnectionHandler;
import handler.DataHandler;
import interfaces.ConnectionHandlerDelegate;
import interfaces.ConnectionStateDelegate;
import interfaces.DataHandlerDelegate;
import model.ConnectionState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    ConnectionHandlerDelegate connectionHandler;
    DataHandlerDelegate dataHandler;

    public Controller() {
        connectionHandler = new ConnectionHandler();
    }


    /**
     * Requests connection from ConnectionHandler, dispatches connection to DataHandler if successful
     * @param username username string
     * @param pwd password string
     */
    public void login(String username, String pwd){
        ConnectionStateDelegate cs = connectionHandler.login(username, pwd);
        if (cs.isConnected()) {
            dataHandler = new DataHandler();
            dataHandler.setConnection(cs.getConnection());
        } else{
            System.err.println("Error initializing dataHandler: Not connected to oracle services");
        }
    }

    public void initializeSQLDDL(){
        if (dataHandler != null) {
            dataHandler.initializeDDL();;
        }
    }

    //Again added for SQL testing can be removed later
    public ResultSet executeSQL(String sql) throws SQLException {
        return dataHandler.getTableData(sql);
    }

    public void logout(){
        connectionHandler.close();
    }
}
