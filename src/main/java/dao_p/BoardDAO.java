package dao_p;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board_p.ListPager;
import dto_p.BoardDTO;

public class BoardDAO {
	Connection connection;
	PreparedStatement preparedStatement;//미리 컴파일/ 준비
	ResultSet resultSet;
	String query;
	public BoardDAO() {
		
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
	public ArrayList<BoardDTO> list(ListPager page, String userId, int admin){
		ArrayList<BoardDTO> result = new ArrayList<BoardDTO>();
		if(admin == 1) {
		query = "select * from personal  where userId = ? order by perNum desc limit ?,?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			preparedStatement.setInt(2, page.getStart());
			preparedStatement.setInt(3, page.getLimit());
		
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setPerNum(((resultSet.getInt("perNum"))));
				dto.setBoardCate(((resultSet.getString("boardCate"))));
				dto.setPerTitle((resultSet.getString("perTitle")));
				dto.setUserId(resultSet.getString("userId"));
				dto.setPerDate(((resultSet.getDate("perDate"))));
				dto.setPerFile((resultSet.getString("perFile")));
				dto.setPerAnswer((resultSet.getString("perAnswer")));
				result.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(); // 예외가 발생하지 않더라도 데이터베이스를 닫도록 finally 선언
		}
		}else {
			query = "select * from personal order by perNum desc limit ?,?";
			
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, page.getStart());
				preparedStatement.setInt(2, page.getLimit());
			
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setPerNum(((resultSet.getInt("perNum"))));
					dto.setBoardCate(((resultSet.getString("boardCate"))));
					dto.setPerTitle((resultSet.getString("perTitle")));
					dto.setUserId(resultSet.getString("userId"));
					dto.setPerDate(((resultSet.getDate("perDate"))));
					dto.setPerFile((resultSet.getString("perFile")));
					dto.setPerAnswer((resultSet.getString("perAnswer")));
					result.add(dto);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(); // 예외가 발생하지 않더라도 데이터베이스를 닫도록 finally 선언
			}
		}
		return result;
	}
	//게시판 글쓰기
	public void writePost(BoardDTO dto,String userId) {
		query = "insert into personal (boardCate, perTitle, perContent, perFile,perDate,userId,perAnswer) values (?,?,?,?,sysdate(),?,?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, dto.getBoardCate());
			preparedStatement.setString(2, dto.getPerTitle());
			preparedStatement.setString(3, dto.getPerContent());
			preparedStatement.setString(4, dto.getPerFile());
			preparedStatement.setString(5, userId);
			preparedStatement.setString(6, "답변전");
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	//게시판 자세히보기
	public BoardDTO getPostDetails(int perNum) {
		BoardDTO dto = null;
		query = "select * from personal where perNum = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, perNum);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				dto = new BoardDTO();
				dto.setPerNum(resultSet.getInt("perNum"));
				dto.setPerTitle(resultSet.getString("perTitle"));
				dto.setUserId(resultSet.getString("userId"));
				dto.setPerDate(resultSet.getDate("perDate"));
				dto.setPerFile((resultSet.getString("perFile")));
				dto.setPerContent(resultSet.getString("perContent"));
				dto.setPerAnswer(resultSet.getString("perAnswer"));
				dto.setAnswer(resultSet.getString("answer"));
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
	public void deletePost(int perNum) {
		query = "delete from personal where perNum = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, perNum);
			resultSet = preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	//게시물 수정하기
	public void editPost(BoardDTO dto) {
		query = "update personal set perTitle=?, userId=?, perContent=?, perFile=? where perNum=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, dto.getPerTitle());
			preparedStatement.setString(2, dto.getUserId());
			preparedStatement.setString(3, dto.getPerContent());
			preparedStatement.setString(4, dto.getPerFile());
			preparedStatement.setInt(5, dto.getPerNum());
			preparedStatement.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
			
	}
	
	//제목 검색
	public ArrayList<BoardDTO> serachList(String cate, String search){
		ArrayList<BoardDTO> result = new ArrayList<BoardDTO>();
		BoardDTO dto= null;
		
		if("카테고리".equals(cate)) {
		    query = "select * from personal where boardCate like ?";
		}else if("제목".equals(cate)) {
		    query = "select * from personal where perContent like ?";
		}
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + search + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				dto = new BoardDTO();
				dto.setPerNum(((resultSet.getInt("perNum"))));
				dto.setBoardCate(((resultSet.getString("boardCate"))));
				dto.setPerTitle((resultSet.getString("perTitle")));
				dto.setUserId(resultSet.getString("userId"));
				dto.setPerDate(((resultSet.getDate("perDate"))));
				dto.setPerFile((resultSet.getString("perFile")));					
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
	
	
	   public int boardTotal(){
		      int cnt = 0;
		      try {
		         query = "select count(*) from personal";
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
	   
	   public void answer(BoardDTO dto) {
			query = "update personal set   perAnswer=?, answer=? where perNum=?";
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, dto.getPerAnswer());
				preparedStatement.setString(2, dto.getAnswer());
				preparedStatement.setInt(3, dto.getPerNum());
				

				preparedStatement.executeUpdate();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
				
		}
	
}
