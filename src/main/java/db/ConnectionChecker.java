package db;


import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;


public class ConnectionChecker {
    public void check() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost, dbUser, dbPassword );
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("show databases");
        rs = stmt.getResultSet();
        while (rs.next()) {
            String line = rs.getString(1); // 하나의 컬럼만 가져옴
            System.out.println(line);
        }
    }


    public void add() throws ClassNotFoundException, SQLException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost, dbUser, dbPassword );

//        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        PreparedStatement pstmt = conn.prepareStatement("insert into users(name, password) values(?, ?)");
//        pstmt.setString(1, "1"); // id를 ai 선택했다면 insert 보내지 않아도 됨
        pstmt.setString(1, "세번째");
        pstmt.setString(2, "4321");
        pstmt.executeUpdate();
    }

    public void select() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost, dbUser, dbPassword );

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users");
        rs = stmt.getResultSet();
        while (rs.next()) {
            String str1 = rs.getString(1);
            String str2 = rs.getString(2);
            String str3 = rs.getString(3);
            System.out.println(str1 + str2 + str3);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionChecker cc = new ConnectionChecker();
        cc.check();
//        cc.add();
//        cc.select();
    }
}
