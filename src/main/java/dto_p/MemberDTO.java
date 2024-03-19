package dto_p;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MemberDTO {
   
   String userId,pw,userName,gender,tel,email,postNum,streetAddr,addr,detailAddr;
   Date birth;
   int admin, status;
   Date createDate;
   
   public String getUserId() {
      return userId;
   }
   public void setUserId(String userId) {
      this.userId = userId;
   }
   public String getPw() {
      return pw;
   }
   public void setPw(String pw) {
      this.pw = pw;
   }
   public String getUserName() {
      return userName;
   }
   public void setUserName(String userName) {
      this.userName = userName;
   }
   public String getGender() {
      return gender;
   }
   public void setGender(String gender) {
      this.gender = gender;
   }
   public String getTel() {
      return tel;
   }
   public void setTel(String tel) {
      this.tel = tel;
   }
   public Date getBirth() {
      return birth;
   }
   public void setBirth(Date birth) {
      this.birth = birth;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getPostNum() {
      return postNum;
   }
   public void setPostNum(String postNum) {
      this.postNum = postNum;
   }
   public String getStreetAddr() {
      return streetAddr;
   }
   public void setStreetAddr(String streetAddr) {
      this.streetAddr = streetAddr;
   }
   public String getAddr() {
      return addr;
   }
   public void setAddr(String addr) {
      this.addr = addr;
   }
   public String getDetailAddr() {
      return detailAddr;
   }
   public void setDetailAddr(String detailAddr) {
      this.detailAddr = detailAddr;
   }
   public int getStatus() {
      return status;
   }
   public void setStatus(int status) {
      this.status = status;
   }
   public int getAdmin() {
      return admin;
   }
   public void setAdmin(int admin) {
      this.admin = admin;
   }
   public Date getCreateDate() {
      return createDate;
   }
   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }
   
   SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
   public String getCreateDateStr() {
      return simpleDate.format(createDate);
   }
   public void setCreateDateStr(String createDate) {
      try {
         this.createDate = simpleDate.parse(createDate);
      } catch (ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public String getBirthStr() {
      return simpleDate.format(birth);
   }
   public void setBirthStr(String birth) {
      try {
         this.birth = simpleDate.parse(birth);
      } catch (ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
}