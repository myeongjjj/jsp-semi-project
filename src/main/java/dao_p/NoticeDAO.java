package dao_p;
import dto_p.NoticeDTO;
import java.sql.Connection;			
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board_p.NoticeListPager;




public class NoticeDAO {
	Connection connection;
	PreparedStatement preparedStatement;//미리 컴파일/ 준비
	ResultSet resultSet;
	String query;
	public NoticeDAO() {
		
		try {
			Context init = new InitialContext();
			DataSource dataSource = (DataSource)init.lookup("java:comp/env/zaq");
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void close() {
		if(resultSet!=null) { try {resultSet.close();} catch (SQLException e) {}}
		if(preparedStatement!=null) { try {preparedStatement.close();} catch (SQLException e) {}}
		if(connection!=null) { try {connection.close();} catch (SQLException e) {}}
	}
	
	//게시판 목록
	public ArrayList<NoticeDTO> list(NoticeListPager page){
		ArrayList<NoticeDTO> result = new ArrayList<NoticeDTO>();
		query = "select * from notice order by noticeNum desc limit ?,?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, page.getStart());
			preparedStatement.setInt(2, page.getLimit());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setnoticeNum(((resultSet.getInt("noticeNum"))));
				dto.setnoticeTitle((resultSet.getString("noticeTitle")));
				dto.setUserId(resultSet.getString("userId"));
				dto.setnoticeDate(((resultSet.getDate("noticeDate"))));
				dto.setnoticeFile((resultSet.getString("noticeFile")));
				result.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(); // 예외가 발생하지 않더라도 데이터베이스를 닫도록 finally 선언
		}
		return result;
	}
	//게시판 글쓰기
	public void writePost(NoticeDTO dto) {
		query = "insert into notice (noticeTitle, noticeContent, noticeFile,noticeDate, userId) values (?,?,?,sysdate(), ?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, dto.getnoticeTitle());
			preparedStatement.setString(2, dto.getnoticeContent());
			preparedStatement.setString(3, dto.getnoticeFile());
			preparedStatement.setString(4, "커피창고");//관리자만 글쓸 수 있음(관리자 로그인이 되어있다는 가정)
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	//게시판 자세히보기
	public NoticeDTO getPostDetails(int noticeNum) {
		NoticeDTO dto = null;
		query = "select * from notice where noticeNum = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, noticeNum);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				dto = new NoticeDTO();
				dto.setnoticeNum(resultSet.getInt("noticeNum"));
				dto.setnoticeTitle(resultSet.getString("noticeTitle"));
				dto.setUserId(resultSet.getString("userId"));
				dto.setnoticeDate(resultSet.getDate("noticeDate"));
				dto.setnoticeFile((resultSet.getString("noticeFile")));
				dto.setnoticeContent(resultSet.getString("noticeContent"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	
	//게시물 삭제하기
	public void deletePost(int noticeNum) {
		query = "delete from notice where noticeNum = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, noticeNum);
			resultSet = preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	//게시물 수정하기
	public void editPost(NoticeDTO dto) {
		query = "update notice set noticeTitle=?, noticeContent=?, noticeFile=? where noticeNum=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, dto.getnoticeTitle());
			preparedStatement.setString(2, dto.getnoticeContent());
			preparedStatement.setString(3, dto.getnoticeFile());
			preparedStatement.setInt(4, dto.getnoticeNum());
			preparedStatement.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
			
	}
	
	//제목 검색
	public ArrayList<NoticeDTO> serachList(String search){
		ArrayList<NoticeDTO> result = new ArrayList<NoticeDTO>();
		NoticeDTO dto= null;
		
		    query = "select * from notice where noticeTitle like ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + search + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				dto = new NoticeDTO();
				dto.setnoticeNum(((resultSet.getInt("noticeNum"))));
				dto.setnoticeTitle((resultSet.getString("noticeTitle")));
				dto.setUserId(resultSet.getString("userId"));
				dto.setnoticeDate(((resultSet.getDate("noticeDate"))));
				dto.setnoticeFile((resultSet.getString("noticeFile")));					
				result.add(dto);
			}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}		
	   public int noticeTotal(){
		      int cnt = 0;
		      try {
		         query = "select count(*) from notice";
		         preparedStatement = connection.prepareStatement(query);
		         resultSet = preparedStatement.executeQuery();
		         resultSet.next();//다음 행 이동
		         cnt = resultSet.getInt(1);
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
		         close();
		      }
		      return cnt;
		   }
		   
		
	
}
