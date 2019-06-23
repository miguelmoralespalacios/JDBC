package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {

	public static void main(String[] args) throws SQLException {
		String url="jdbc:oracle:thin:@localhost:32769:ORCLCDB",username="mike",password="mike";
		//jdbc:myDriver:myDatabase
		Connection con = DriverManager.getConnection(
                 url,
                 username,
                 password
        );
		
		String csString="{ call mexicancitiestuple(?,?) }";
		CallableStatement cs=con.prepareCall(csString);
		cs.setInt(1, 9);
		cs.setString(2,"Ciudad Victoria");
		cs.execute();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM mexicancities");
		

		while (rs.next()) {
			int x = rs.getInt("cityid");
			String s = rs.getString("cityname");
			System.out.println("City ID:"+x+", city name:"+s);				
		}

	}

}
