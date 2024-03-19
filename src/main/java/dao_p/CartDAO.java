package dao_p;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto_p.CartDTO;


public class CartDAO {

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	public CartDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/zaq");
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void close() {
		if(rs!=null) { try { rs.close();} catch (SQLException e) {}}
		if(psmt!=null) { try { psmt.close();} catch (SQLException e) {}}
		if(con!=null) { try { con.close();} catch (SQLException e) {}}
	}
	
	public ArrayList<CartDTO> list(String userId){
		ArrayList<CartDTO> res = new ArrayList<CartDTO>();
		sql = "select * from cart where userId = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1,userId);
			rs = psmt.executeQuery();
			while(rs.next()) {

				CartDTO dto = new CartDTO();
				dto.setNo(rs.getInt("no"));
				dto.setCartFile(rs.getString("cartFile"));
				dto.setCartTitle(rs.getString("cartTitle"));
				dto.setProdPrice(rs.getInt("prodPrice"));
				dto.setProdCnt(rs.getInt("prodCnt"));
				dto.setOption1(rs.getString("option1"));
				dto.setOption2(rs.getString("option2"));
				dto.setUserId(rs.getString("userId"));
				dto.setProdNum(rs.getInt("prodNum"));

				res.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	public ArrayList<CartDTO> payList(String userId,int no){
		ArrayList<CartDTO> res = new ArrayList<CartDTO>();
		sql = "select * from cart where userId = ? and no = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1,userId);
			psmt.setInt(2,no);
			rs = psmt.executeQuery();
			while(rs.next()) {

				CartDTO dto = new CartDTO();
				dto.setNo(rs.getInt("no"));
				dto.setCartFile(rs.getString("cartFile"));
				dto.setCartTitle(rs.getString("cartTitle"));
				dto.setProdPrice(rs.getInt("prodPrice"));
				dto.setProdCnt(rs.getInt("prodCnt"));
				dto.setOption1(rs.getString("option1"));
				dto.setOption2(rs.getString("option2"));
				dto.setUserId(rs.getString("userId"));
				dto.setProdNum(rs.getInt("prodNum"));

				res.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	
	public  void cartAdd(CartDTO dto){
		
		sql = "insert into cart (prodPrice, prodCnt,option1,option2,userId,prodNum, cartFile, cartTitle, no) values (?,?,?,?,?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, dto.getProdPrice());
			psmt.setInt(2, dto.getProdCnt());
			psmt.setString(3, dto.getOption1());
			psmt.setString(4, dto.getOption2());
			psmt.setString(5, dto.getUserId());
			psmt.setInt(6, dto.getProdNum());
			psmt.setString(7, dto.getCartFile());
			psmt.setString(8, dto.getCartTitle());
			psmt.setInt(9, dto.getNo());
		
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	
	public  void delete(int no){
		
		sql = "delete from cart where no = ? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, no);
		
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public void orderinfo(CartDTO dto,int no){
		
		sql = "INSERT INTO orderinfo (orderCnt, prodNum, orderNum) "
				+ "SELECT prodCnt, prodNum, ? "
				+ "FROM cart "
				+ "WHERE userId = ? and no = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setLong(1, dto.getProdNum());
			psmt.setString(2, dto.getUserId());
			psmt.setInt(3, no);

			
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
	}
	
	public void delivery(CartDTO dto2){
		
		sql = "INSERT INTO delivery "
				+ "(orderNum, deliveryStatus, "
				+ "receiver, phoneNum, postNum, "
				+ "orderAddress, message, orderDate, userId,orderStatus) "
				+ "values (?,?,?,?,?,?,?,sysdate(),?,?) ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, dto2.getProdNum());
			psmt.setString(2, "결제완료");
			psmt.setString(3, dto2.getReceiver());
			psmt.setString(4, dto2.getPhoneNum());
			psmt.setString(5, dto2.getPostNum());
			psmt.setString(6, dto2.getOrderAddress());
			psmt.setString(7, dto2.getMessage());
			psmt.setString(8, dto2.getUserId());
			psmt.setString(9, "");
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
	}
	
	public void orderDelete(String userId,int no){
		
		sql = "delete from cart where userId = ? and no = ? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userId);
			psmt.setInt(2, no);
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
}
