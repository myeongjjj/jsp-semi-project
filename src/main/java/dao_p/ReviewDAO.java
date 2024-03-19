package dao_p;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto_p.ProductDTO;
import dto_p.ReviewDTO;

public class ReviewDAO {
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	public ReviewDAO() {
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
	
	public ArrayList<ReviewDTO> list(int prodNum){
		ArrayList<ReviewDTO> res = new ArrayList<ReviewDTO>();
		
		sql = "select * from review where prodNum=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, prodNum);
			rs = psmt.executeQuery();
			while(rs.next()) {

				ReviewDTO dto = new ReviewDTO();
				dto.setProdNum(rs.getInt("prodNum"));
				dto.setReviewNum(rs.getInt("reviewNum"));
				dto.setReviewStar(rs.getInt("reviewStar"));
				dto.setReviewTitle(rs.getString("reviewTitle"));
				dto.setReviewContent(rs.getString("reviewContent"));
				dto.setUserId(rs.getString("userId"));
				res.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	public void write(ReviewDTO dto){
		sql = "insert into review (reviewStar,prodNum,reviewTitle,userId,reviewNum) values (?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
	
	
			psmt.setInt(1,dto.getReviewStar());
			psmt.setInt(2,dto.getProdNum());
			psmt.setString(3,dto.getReviewTitle());
			psmt.setString(4,dto.getUserId());
			psmt.setInt(5,dto.getReviewNum());
			
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	
	
	public ArrayList<ReviewDTO> detail(String userId){
		ArrayList<ReviewDTO> res = new ArrayList<ReviewDTO>();
		ReviewDTO dto = null;
		
		sql = "select * from review where userId = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				dto = new ReviewDTO();
				dto.setProdNum(rs.getInt("prodNum"));
				dto.setReviewTitle(rs.getString("reviewTitle"));
				dto.setReviewStar(rs.getInt("reviewStar"));
				dto.setReviewNum(rs.getInt("reviewNum"));
				dto.setUserId(rs.getString("userId"));
				res.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}

	public  void delete(int reviewNum){
		
		sql = "delete from review where reviewNum = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, reviewNum);
			
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}	
}
