package dao_p;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto_p.MemberDTO;
import dto_p.ProfileDTO;





public class ProfileDAO {
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	public ProfileDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/zaq");
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//close();
	}
	
	void close() {
		if(rs!=null) { try { rs.close();} catch (SQLException e) {}}
		if(psmt!=null) { try { psmt.close();} catch (SQLException e) {}}
		if(con!=null) { try { con.close();} catch (SQLException e) {}}
	}
	
	public ArrayList<ProfileDTO> profileList(){
		ArrayList<ProfileDTO> res = new ArrayList<ProfileDTO>();
		sql = "select * from member where UserId = ?" ;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, "lsh");
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {

				ProfileDTO dto = new ProfileDTO();
				dto.setUserId(rs.getString("userId"));
				dto.setPw(rs.getString("pw"));
				dto.setUserName(rs.getString("userName"));
				dto.setAddr(rs.getString("addr"));
				dto.setTel(rs.getString("tel"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getString("gender"));
				dto.setBirthStr(rs.getString("birth"));
				
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
	
	public void modify(MemberDTO dto){

		sql = "update member set pw = ? , addr = ?, email = ?, tel = ? where userId = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getPw());
			psmt.setString(2, dto.getAddr());
			psmt.setString(3, dto.getEmail());
			psmt.setString(4, dto.getTel());
			psmt.setString(5, dto.getUserId());
			
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}

	}
	
	public void delete(String userId){

		sql = "update member set status = 0 where userId = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userId);
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}

	}
	
	
}
