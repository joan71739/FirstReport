package build.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class BuildDAO {
	
	private Connection conn;
	

	//開連線
	
	public void createConn() throws SQLException  {
		String urlString = "jdbc:sqlserver://localhost:1433;databaseName=building;user=sa;password=1234";
		conn = DriverManager.getConnection(urlString);
		
		boolean status = !conn.isClosed();
		
		if(status) {
			System.out.println("連線成功");
		}
		
	}
	
	//關連線
	
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("連線關閉");
		}
	}
	
	
	//新增資料方法
	
	public void addBuildItem(BuildItem b) throws SQLException  {
		String sqlStr = "Insert into building Values (?,?,?,?,?,?,?,?)";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1,b.getBd_region());
		preState.setString(2,b.getBd_dealdate());
		preState.setString(3,b.getBd_buildtype());
		preState.setString(4,b.getBd_loc());
		preState.setDouble(5,b.getBd_area());
		preState.setInt(6,b.getBd_price());
		preState.setString(7,b.getBd_parking());
		preState.setString(8,b.getBd_remark());
		
		preState.execute();
		preState.close();
	}
	

	//修改方法：利用ID及區域修改房屋面積
	
	public Integer updateAreaByIdAndRe(BuildItem b) throws SQLException {
		String sqlStr = "Update building Set area=? where id=? and region=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setDouble(1, b.getBd_area());
		preState.setInt(2, b.getBd_id());
		preState.setString(3, b.getBd_region());
		
		int sta = preState.executeUpdate();
		preState.close();
		return sta;
		
	}
	
	
	
	//查詢方法：利用ID資料查詢
	
	public BuildItem findByID(int id) throws SQLException {
		String sqlStr = "Select * from building where id = ?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setInt(1, id);
		ResultSet rs = preState.executeQuery();
		
		BuildItem b = null;
		
		if(rs.next()) {
			
			b = new BuildItem();
			b.setBd_id(rs.getInt("id"));
			b.setBd_region(rs.getString("region"));
			b.setBd_dealdate(rs.getString("dealdate"));
			b.setBd_buildtype(rs.getString("buildtype"));
			b.setBd_loc(rs.getString("loc"));
			b.setBd_area(rs.getDouble("area"));
			b.setBd_price(rs.getInt("price"));
			b.setBd_parking(rs.getString("parking"));
			b.setBd_remark(rs.getString("remark"));
			
		}
		
		rs.close();
		preState.close();
		return b;
			
		}
		
	
	
	//刪除資料方法
	
	public void deleteMemberById(int id) throws SQLException {
		String sqlStr = "Delete from building where id=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setInt(1, id);
		preState.execute();
		preState.close();
	}
	
	//查詢全部資料
	
	public   void  querryALL() throws SQLException {
		String sqlStr = "Select * from building";
		Statement stmt = conn.createStatement();
		ResultSet rs =stmt.executeQuery(sqlStr);
		System.out.println("ID"+","+"區域"+ "," +"交易日期"+ "," +"房屋型態"+ "," +"路段"+
				","+"面積"+","+"價格"+","+"是否含車位"+ "," +"備註");
		
		while(rs.next()) {
	          
			
			
			
			System.out.println(rs.getInt(1)+ "," +rs.getString(2)+ "," + rs.getString(3) + "," + rs.getString(4) + "," +rs.getString(5)+
			","+rs.getDouble(6)+","+rs.getInt(7)+","+rs.getString(8)+ "," +rs.getString(9));
		}
	}
	
	
}
	


	
	
	
	
	
	
	


