package uom.team2.weball_statistics.Model.Statistics;

import androidx.annotation.NonNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLDataRecovery implements Runnable {

    private Connection con;
    private String sql;
    private String user;
    private String pass;
    private String server;
    private String database;

    public  SQLDataRecovery(String s, String d, String u, String p, ArrayList<String> arrayFields, String table) {

        user = u;
        pass = p;
        server = s;
        database = d;
        createSqlQuery(arrayFields, table);

        System.out.println(sql);

    }

    private void createSqlQuery(@NonNull ArrayList<String> fields, String arrayInDatabase){
        sql = "SELECT (";
        for(int i=0; i< fields.size(); i++){
            sql  +=  fields.get(i) + ",";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += ") ";
        sql += "FROM " + arrayInDatabase;
    }

    private void recoveryData(){

    }

    private void executeSqlQuery(){
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                recoveryData();
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
            s.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mariadb://" + server + ":3306/" + database, user, pass);

            executeSqlQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
