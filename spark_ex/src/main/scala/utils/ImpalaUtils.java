package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ImpalaUtils {
    public static void main(String args[]){
        ImpalaUtils impalaUtils = new ImpalaUtils();
        try {
            impalaUtils.executeQuery(args[0], args[1], args[2]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ImpalaUtils() {
    }

    public void refreshTable(String driver, String url, String table) throws SQLException, ClassNotFoundException {
        System.setProperty("java.security.auth.login.config", "/opt/krb5renew/etl/conf/jaas.conf");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        String sql = "REFRESH " + table;
        statement.execute(sql);
        statement.close();
        connection.close();
    }

    public void executeQuery(String driver, String url, String sql) throws ClassNotFoundException, SQLException {
        String[] sqlSub = sql.split(";");
        System.setProperty("java.security.auth.login.config", "/opt/krb5renew/etl/conf/jaas.conf");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        String[] var7 = sqlSub;
        int var8 = sqlSub.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            String string = var7[var9];
            statement.execute(string);
        }

        statement.close();
        connection.close();
    }
}
