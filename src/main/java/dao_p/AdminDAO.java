package dao_p;
/*
CREATE TABLE board(
	no int AUTO_INCREMENT PRIMARY KEY,
	title varchar(100),
	pname varchar(100),
	upfile varchar(100),
	content varchar(9999),
	reg_date datetime
);

INSERT INTO board (title,pname,content,reg_date) VALUES
('첫글이다','이상훈','첫글내용','2022-05-06'),
('두글이다','삼상훈','두글내용','2000-08-01'),
('세글이다','사상훈','셋글내용','2021-04-04'),
('네글이다','박건우','넷글내용','2023-02-06'),
('오글이다','박건수','오글내용','2024-09-04');
 * */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto_p.AdminDTO;

public class AdminDAO {
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	public AdminDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds =(DataSource)init.lookup("java:comp/env/zaq");
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//close();
	}
	
	void close() {
		if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
		if(psmt!=null) {try {psmt.close();} catch (SQLException e) {}}
		if(con!=null) {try {con.close();} catch (SQLException e) {}}
	}
	
	public ArrayList<AdminDTO> list(String start, String end){
		ArrayList<AdminDTO> res = new ArrayList<AdminDTO>();
		
		sql = "SELECT "
				+ "c.orderDate AS orderDate,"
				+ "a.orderNum  AS orderNum,"
				+ "b.prodTitle AS prodTitle,"
				+ "b.prodCate  AS prodCate,"
				+ "b.prodPrice AS prodPrice,"
				+ "a.orderCnt  AS orderCnt,"
				+ "b.prodPrice*a.orderCnt AS tot "
				+ "FROM orderinfo a "
				+ "LEFT OUTER JOIN product b "
				+ "ON a.prodNum = b.prodNum "
				+ "LEFT OUTER JOIN delivery c "
				+ "ON a.orderNum = c.orderNum "
				+ "ORDER BY c.orderDate DESC "
				;
		if(start!=null && end!=null){
			sql = "SELECT "
					+ "c.orderDate AS orderDate,"
					+ "a.orderNum  AS orderNum,"
					+ "b.prodTitle AS prodTitle,"
					+ "b.prodCate  AS prodCate,"
					+ "b.prodPrice AS prodPrice,"
					+ "a.orderCnt  AS orderCnt,"
					+ "b.prodPrice*a.orderCnt AS tot "
					+ "FROM orderinfo a "
					+ "LEFT OUTER JOIN product b "
					+ "ON a.prodNum = b.prodNum "
					+ "LEFT OUTER JOIN delivery c "
					+ "ON a.orderNum = c.orderNum "
					+ "WHERE date_format(c.orderDate,'%Y-%m') BETWEEN ? AND ? "
					+ "ORDER BY c.orderDate DESC "
					;
		}
		
		if(start==""&&end=="") {
			sql = "SELECT "
					+ "c.orderDate AS orderDate,"
					+ "a.orderNum  AS orderNum,"
					+ "b.prodTitle AS prodTitle,"
					+ "b.prodCate  AS prodCate,"
					+ "b.prodPrice AS prodPrice,"
					+ "a.orderCnt  AS orderCnt,"
					+ "b.prodPrice*a.orderCnt AS tot "
					+ "FROM orderinfo a "
					+ "LEFT OUTER JOIN product b "
					+ "ON a.prodNum = b.prodNum "
					+ "LEFT OUTER JOIN delivery c "
					+ "ON a.orderNum = c.orderNum "
					+ "ORDER BY c.orderDate DESC "
					;
		}

		try {
			psmt = con.prepareStatement(sql);
			if(start!=null && end!=null) {
				psmt.setString(1, start);
				psmt.setString(2, end);
			}

			
			rs = psmt.executeQuery();
			while(rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setOrderDate(rs.getDate("orderDate"));
				dto.setOrderNum(rs.getInt("orderNum"));
				dto.setProdTitle(rs.getString("prodTitle"));
				dto.setProdCate(rs.getString("prodCate"));
				dto.setProdPrice(rs.getInt("prodPrice"));
				dto.setOrderCnt(rs.getInt("orderCnt"));
				dto.setTot(rs.getInt("tot"));
				
				res.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		
		return res;
	}
	
	public int total(String start, String end){
		int cnt = 0;
		sql = "SELECT "
				+ "sum(b.prodPrice*a.orderCnt) AS total "
				+ "FROM orderinfo a "
				+ "LEFT OUTER JOIN product b "
				+ "ON a.prodNum = b.prodNum "
				+ "LEFT OUTER JOIN delivery c "
				+ "ON a.orderNum = c.orderNum"
				;
		if(start!=null && end!=null){
			sql = "SELECT "
					+ "sum(b.prodPrice*a.orderCnt) AS total "
					+ "FROM orderinfo a "
					+ "LEFT OUTER JOIN product b "
					+ "ON a.prodNum = b.prodNum "
					+ "LEFT OUTER JOIN delivery c "
					+ "ON a.orderNum = c.orderNum "
					+ "WHERE date_format(c.orderDate,'%Y-%m') BETWEEN ? AND ? ";
		}
		if(start==""&&end=="") {
			sql = "SELECT "
					+ "sum(b.prodPrice*a.orderCnt) AS total "
					+ "FROM orderinfo a "
					+ "LEFT OUTER JOIN product b "
					+ "ON a.prodNum = b.prodNum "
					+ "LEFT OUTER JOIN delivery c "
					+ "ON a.orderNum = c.orderNum";
		}
		try {
			psmt = con.prepareStatement(sql);
			if(start!=null && end!=null) {
				psmt.setString(1, start);
				psmt.setString(2, end);
			}
			rs = psmt.executeQuery();
			rs.next();			
			cnt = rs.getInt("total");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}

		return cnt;
	}
	
	public ArrayList<AdminDTO> orderList(){
		ArrayList<AdminDTO> res = new ArrayList<AdminDTO>();
		sql = "select "
				+ "a.orderNum as orderNum, "
				+ "a.orderDate as orderDate, "
				+ "a.userId as userId, "
				+ "c.prodTitle as prodTitle, "
				+ "a.deliveryStatus as deliveryStatus, "
				+ "a.wayBill as wayBill, "
				+ "a.orderStatus as orderStatus "
				+ "from delivery a "
				+ "LEFT OUTER JOIN orderinfo b "
				+ "ON a.orderNum = b.orderNum "
				+ "LEFT OUTER JOIN product c "
				+ "ON b.prodNum = c.prodNum "
				+ "ORDER BY orderDate DESC "
				;
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setOrderNum(rs.getInt("orderNum"));
				dto.setOrderDate(rs.getDate("orderDate"));
				dto.setUserId(rs.getString("userId"));
				dto.setProdTitle(rs.getString("prodTitle"));
				dto.setDeliveryStatus(rs.getString("deliveryStatus"));
				dto.setWayBill(rs.getString("wayBill"));
				dto.setOrderStatus(rs.getString("orderStatus"));

				res.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	public ArrayList<AdminDTO> adDeliList(String orderNum){
		ArrayList<AdminDTO> res = new ArrayList<AdminDTO>();
		sql = "select "
				+ "orderNum, "
				+ "userId, "
				+ "deliveryStatus, "
				+ "wayBill, "
				+ "orderStatus "
				+ "from delivery "
				+ "WHERE orderNum = ? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, orderNum);
			rs = psmt.executeQuery();
			while(rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setOrderNum(rs.getInt("orderNum"));
				dto.setUserId(rs.getString("userId"));
				dto.setDeliveryStatus(rs.getString("deliveryStatus"));
				dto.setWayBill(rs.getString("wayBill"));
				dto.setOrderStatus(rs.getString("orderStatus"));

				res.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	public ArrayList<AdminDTO> adDeliProd(String orderNum){
		ArrayList<AdminDTO> res = new ArrayList<AdminDTO>();
		sql = "SELECT "
				+ "a.prodTitle as prodTitle "
				+ "FROM product a "
				+ "LEFT OUTER JOIN orderinfo b "
				+ "ON a.prodNum = b.prodNum "
				+ "WHERE b.orderNum = ? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, orderNum);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setProdTitle(rs.getString("prodTitle"));

				res.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	public void deliModify(AdminDTO dto){
		
		sql = "update delivery set deliveryStatus = ? , wayBill = ? , orderStatus = ? where orderNum = ? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getDeliveryStatus());
			psmt.setString(2, dto.getWayBill());
			psmt.setString(3, dto.getOrderStatus());
			psmt.setInt(4, dto.getOrderNum());
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	
}
