package interfaces;

import model.table.TableModel;

import java.sql.Connection;
import java.util.Set;

public interface DataHandlerDelegate {

    /**
     * Sets the connection object to be used to communicate with oracle db
     * @param connection object to get/send data
     */
    void setConnection(Connection connection);

    /**
     * Runs SQL DDL create statements, drops tables.
     */
    void initializeDDL();


    /**
     * Insert the specified model into the database
     * @param data model to be inserted into the database
     */
    void insertTableData(TableModel data);

    /**
     * // to define
     * @param data // to define
     */
    //void updateTableData(TableRow data); // need some kind of parameter to specify what we want to update I am guessing

    /**
     * //TODO : what exactly do we want to do? (same for all the above)
     * @param dataToLookup list of string to lookup?
     * @return TableData returned
     */
    //TableRow getTableData(Set<String> dataToLookup); // is this what we want? Not quite sure yet
}
