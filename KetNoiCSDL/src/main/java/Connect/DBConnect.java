package Connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private final String serverName = "localhost";
    private final String dbName = "LTWEB";
    private final String portNumber = "1433";
    private final String instance = "";  // Nếu bạn dùng instance khác thì set tên ở đây

    public Connection getConnection() throws Exception {
        String url;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber +
                    ";databaseName=" + dbName +
                    ";encrypt=true;trustServerCertificate=true;" +
                    "integratedSecurity=true";  // Dùng Windows Authentication
        } else {
            url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber +
                    ";databaseName=" + dbName +
                    ";encrypt=true;trustServerCertificate=true;" +
                    "integratedSecurity=true";
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url);
    }

    public static void main(String[] args) {
        try {
            Connection conn = new DBConnect().getConnection();
            System.out.println("Kết nối thành công: " + conn);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
