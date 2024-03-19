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

import dto_p.OrderDTO;


public class OrderDAO {
   
   Connection con;
   PreparedStatement psmt;
   ResultSet rs;
   String sql;
   
   public OrderDAO() {
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
   
   public ArrayList<OrderDTO> detailList(String userId, String start, String end, String deliveryStatus){
         ArrayList<OrderDTO> res = new ArrayList<OrderDTO>();
         sql = "select * from delivery d left outer join orderinfo o on d.orderNum = o.orderNum left outer join product p on o.prodNum = p.prodNum where d.userId = ? ";
         
         try {
            if(start=="" && end=="" && deliveryStatus == "") {
               sql += "order by d.orderNum desc";
               psmt = con.prepareStatement(sql);
               psmt.setString(1, userId);
               //System.out.println(userId);
               //System.out.println(sql);
            }
            
            if(start != "" && end != "" && deliveryStatus == "") {
               sql += "and d.orderDate between ? and ? order by d.orderNum desc ";
               psmt = con.prepareStatement(sql);
               psmt.setString(1, userId);
               psmt.setString(2, start);
               psmt.setString(3, end);
            }
            
            if(start != "" && end != "" && deliveryStatus != "") {
               sql += "and d.orderDate between ? and ? and d.deliveryStatus = ? order by d.orderNum desc ";
               psmt = con.prepareStatement(sql);
               psmt.setString(1, userId);
               psmt.setString(2, start);
               psmt.setString(3, end);
               psmt.setString(4, deliveryStatus);
            }
            
            if(start == "" && end == "" && deliveryStatus != "") {
               sql += "and d.deliveryStatus = ? order by d.orderNum desc ";
               psmt = con.prepareStatement(sql);
               psmt.setString(1, userId);
               psmt.setString(2, deliveryStatus);
            }
            
            
           rs = psmt.executeQuery();
            while(rs.next()) {

               OrderDTO dto = new OrderDTO();
               dto.setOrderNum(rs.getInt("orderNum"));
               dto.setOrderDateStr(rs.getString("orderDate"));
               dto.setProdFile(rs.getString("prodFile"));
               dto.setProdTitle(rs.getString("prodTitle"));
               dto.setOrderCnt(rs.getInt("orderCnt"));
               dto.setProdPrice(rs.getInt("prodPrice"));
               dto.setProdNum(rs.getInt("prodNum"));
               dto.setDeliveryStatus(rs.getString("deliveryStatus"));
               dto.setOrderStatus(rs.getString("orderStatus"));
               
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
   
   public ArrayList<OrderDTO> refundList(String userId, String start, String end,String orderStatus ){
      ArrayList<OrderDTO> res = new ArrayList<OrderDTO>();
      sql = "select * from delivery d left outer join orderinfo o on d.orderNum = o.orderNum left outer join product p on o.prodNum = p.prodNum where d.userId = ? ";
      try {
         if(start=="" && end=="" && orderStatus=="") {
            sql += " order by d.orderNum desc ";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, userId);
         }
         
         if(start != "" && end != "" && orderStatus=="") {
            sql += " and d.orderDate between ? and ? order by d.orderNum desc";
      
            psmt = con.prepareStatement(sql);
            psmt.setString(1, userId);
            psmt.setString(2, start);
            psmt.setString(3, end);
        
            
            System.out.println(sql);
         }
         if(start != "" && end != "" && orderStatus != "") {
             sql += "and d.orderDate between ? and ? and  d.orderStatus like ? order by d.orderNum desc ";
             psmt = con.prepareStatement(sql);
             psmt.setString(1, userId);
             psmt.setString(2, start);
             psmt.setString(3, end);
             psmt.setString(4, "%"+orderStatus+"%");
          }
         if(start == "" && end == "" && orderStatus != "") {
             sql += "and  d.orderStatus like ? order by d.orderNum desc ";
             psmt = con.prepareStatement(sql);
             psmt.setString(1, userId);
             psmt.setString(2,  "%"+orderStatus+"%");
          }
          
         rs = psmt.executeQuery();
         while(rs.next()) {

            OrderDTO dto = new OrderDTO();
               dto.setOrderNum(rs.getInt("orderNum"));
               dto.setOrderDateStr(rs.getString("orderDate"));
               dto.setProdFile(rs.getString("prodFile"));
               dto.setProdTitle(rs.getString("prodTitle"));
               dto.setOrderCnt(rs.getInt("orderCnt"));
               dto.setProdPrice(rs.getInt("prodPrice"));
               dto.setDeliveryStatus(rs.getString("deliveryStatus"));
               dto.setOrderStatus(rs.getString("orderStatus"));
            
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
 
   
   public ArrayList<OrderDTO> historyList(String userId,String start, String end){
      ArrayList<OrderDTO> res = new ArrayList<OrderDTO>();
      sql = "select * from delivery d left outer join orderinfo o on d.orderNum = o.orderNum left outer join product p on o.prodNum = p.prodNum "
            + "where d.userId = ?";
      try {
         psmt = con.prepareStatement(sql);
         psmt.setString(1, userId);


          if(start != "" && end != "") {
              sql += "and d.orderDate between ? and ? ";
              psmt = con.prepareStatement(sql);
              psmt.setString(1, userId);
              psmt.setString(2, start);
              psmt.setString(3, end);
        
           }
         
           
         
         rs = psmt.executeQuery();
         while(rs.next()) {

            OrderDTO dto = new OrderDTO();
               dto.setOrderNum(rs.getInt("orderNum"));
               dto.setOrderDateStr(rs.getString("orderDate"));
               dto.setProdFile(rs.getString("prodFile"));
               dto.setProdTitle(rs.getString("prodTitle"));
               dto.setOrderCnt(rs.getInt("orderCnt"));
               dto.setProdPrice(rs.getInt("prodPrice"));
               dto.setDeliveryStatus(rs.getString("deliveryStatus"));
               dto.setOrderStatus(rs.getString("orderStatus"));
            
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
   
   public int cancel(int orderNum) {
      int res = 0;
      
      sql = "update delivery set orderStatus = '취소' where orderNum = ?";
      try {
         psmt = con.prepareStatement(sql);
         psmt.setInt(1, orderNum);
         
         res = psmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close();
      }
      
      return res;
   }
   
   public int change(int orderNum) {
      int res = 0;
      
      sql = "update delivery set orderStatus = '교환요청' where orderNum = ?";
      try {
         psmt = con.prepareStatement(sql);
         psmt.setInt(1, orderNum);
         
         res = psmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close();
      }
      
      return res;
   }
   
   public int clear(int orderNum) {
      int res = 0;
      
      sql = "update delivery set orderStatus = '환불요청' where orderNum = ?";
      try {
         psmt = con.prepareStatement(sql);
         psmt.setInt(1, orderNum);
         
         res = psmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close();
      }
      
      return res;
   }
   public void autoConfirm(int orderNum) {
	      
	      sql = "update delivery set deliveryStatus = '구매확정' where orderNum = ?";
	      try {
	         psmt = con.prepareStatement(sql);
	         psmt.setInt(1, orderNum);
	         
	         psmt.executeUpdate();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         close();
	      }
	      
	   }
   public int confirm(int orderNum) {
	      int res = 0;
	      
	      sql = "update delivery set deliveryStatus = '구매확정' where orderNum = ?";
	      try {
	         psmt = con.prepareStatement(sql);
	         psmt.setInt(1, orderNum);
	         
	         res = psmt.executeUpdate();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         close();
	      }
	      
	      return res;
	   }

   
   
//   public int total(){
//      int cnt = 0;
//      sql = "select count(*) from board";
//      try {
//         psmt = con.prepareStatement(sql);
//         
//         rs = psmt.executeQuery();
//         rs.next();
//         
//         cnt = rs.getInt(1);
//         
//      } catch (SQLException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }finally {
//         close();
//      }
//
//      return cnt;
//   }
   

}