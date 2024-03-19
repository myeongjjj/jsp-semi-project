package dao_p;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.catalina.tribes.Member;

import dto_p.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


public class MemberDAO {

	private static MemberDAO dao;
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	private int result;
	

	public static synchronized MemberDAO getInstance() {
		// TODO Auto-generated method stub
		if(dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}
	
	public MemberDAO() {
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
	
	//회원가입
	public void write(MemberDTO dto) {
		
		sql = "insert into member(userId, pw, userName, gender, tel, email, addr,postNum) values (?,?,?,?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1,  dto.getUserId());
			psmt.setString(2,  dto.getPw());
			psmt.setString(3,  dto.getUserName());
			psmt.setString(4,  dto.getGender());
			psmt.setString(5,  dto.getTel());
			psmt.setString(6,  dto.getEmail());
			psmt.setString(7,  dto.getAddr());
			psmt.setString(8,  dto.getPostNum());
			
		
			
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}

	
	public MemberDTO loginChk(String userId, String pw) {
	      MemberDTO user = null;
	      sql = "select * from member where userId = ? and pw = ?";
	      
	      try {
	         psmt = con.prepareStatement(sql);
	         psmt.setString(1, userId);
	         psmt.setString(2, pw);
	         
	         rs = psmt.executeQuery();
	         
	         if(rs.next()) {
	            user = new MemberDTO();
	            user.setUserId(rs.getString("userId"));
	            user.setPw(rs.getString("pw"));
	            user.setUserName(rs.getString("userName"));
	            user.setGender(rs.getString("gender"));
	            user.setBirth(rs.getDate("birth"));
	            user.setTel(rs.getString("tel"));
	            user.setAddr(rs.getString("addr"));
	            user.setEmail(rs.getString("email"));
	            user.setAdmin(rs.getInt("admin"));
	            user.setStatus(rs.getInt("status"));
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      
	      return user;
	   }
	
	//회원가입 유효성 검사
		//id 중복체크
		public boolean idExist(String userId) {
			sql = "select count(*) from member where userId=?";
			int cnt=0;
			try {
				psmt = con.prepareStatement(sql);
				psmt.setString(1,  userId);
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					cnt = rs.getInt(1);
					return cnt > 0 ? true: false;//내역이 있는 경우(사용 불가능)
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return false;//내역 없는 경우(사용 가능)
		}
}
