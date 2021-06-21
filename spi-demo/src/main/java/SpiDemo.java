import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author liulei
 * @Description
 * @create 2021/6/20 16:36
 */
public class SpiDemo {

    public static void main2(String[] args) {
        ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loadedDrivers.iterator();
        while(iterator.hasNext()){
            Driver next = iterator.next();
            if(next instanceof com.mysql.cj.jdbc.Driver){
                System.out.println("aaaa");
            }
        }
    }

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://rm-2ze78y0ndx308xfldfo.mysql.rds.aliyuncs.com:3306/", "my_root", "my_root1");
        connection.setCatalog("jdbc");
        DriverManager.setLogWriter(new PrintWriter(new ByteArrayOutputStream(),true));
        String query = "select * from COFFEE_HOUSES";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getInt("STORE_ID") + ", " +
                        rs.getString("CITY") + ", " + rs.getInt("COFFEE") +
                        ", " + rs.getInt("MERCH") + ", " +
                        rs.getInt("TOTAL"));
            }
        }
    }
}
