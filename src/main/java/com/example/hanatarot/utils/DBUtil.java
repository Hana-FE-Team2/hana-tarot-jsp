package com.example.hanatarot.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {
    public static final Connection con = getConnection();

    private static Connection getConnection() {
        Connection con = null;
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);
            String url = "jdbc:oracle:thin:@(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.ap-seoul-1.oraclecloud.com))(connect_data=(service_name=g56e711c2a2b221_dinkdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";
            String userId = "Da2402";
            String passWord = "Data2402";

            con = DriverManager.getConnection(url, userId, passWord);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void close(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String dmlData(String sql, Connection con) {
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            if (sql.contains("INSERT INTO TB_PRODUCT")) {
                stmt.executeUpdate(sql, new String[]{"NO_PRODUCT"});
            } else if (sql.contains("insert into tb_order")) {
                stmt.executeUpdate(sql, new String[]{"id_order"});
            } else {
                stmt.executeUpdate(sql);
            }
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static ResultSet queryData(String sql, Connection con) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
