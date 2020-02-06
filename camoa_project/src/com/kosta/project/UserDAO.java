package com.kosta.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import dbclose.util.CloseUtil;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public UserDAO() {
	}
	public String saup_num(String userId) {
		
		String sa_num = null;
		String sql = "SELECT SAUP_NUM FROM CUS WHERE CUS_ID = ?";
		try {
			conn = Connect.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				sa_num = rs.getString(1); 
				System.out.println(sa_num);
			}
			return sa_num;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sa_num;
	}
	public String getIn(String s, String uid) throws SQLException, NamingException {	
		String sql=null;
		String back=null;
		switch (s) {
		case "Email" :
			sql="select cus_email from cus where cus_id = ?";
			break;
		case "Name" :
			sql="select cus_name from cus where cus_id = ?";
			break;
		case "Phone" :
			sql="select cus_phone from cus where cus_id = ?";
			break;
		case "SaupNum" :
			sql="select Saup_num from cus where cus_id = ?";
			break;
		case "CafeName" :
			sql="select Saup_cafe from cus where cus_id = ?";
			break;
		}
		conn=Connect.getConnection();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, uid);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			back=rs.getString(1);
		}
		CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		return back;
	}
	public Boolean test(String uid) {//사업자번호로 사업자 인지 판별
		String sql="select saup_num from cus where cus_id = ?";
		try {
			conn=Connect.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("saup_num")!=null) return true;
				else return false;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		return false;
		
	}
	public int login(String userID, String userPwd) {
		String sql="select cus_pwd1 from cus where cus_id=?";
		try {
			conn=Connect.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPwd))
					return 1; 	//일치 
				else return 0;	//비밀번호 불일치
			}
			return -1;//아이디없음
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		return -2; //데이터베이스 오류
	}
	public int join(String userId, String userPwd1,  String userPwd2,
			String userName, String email, String userNum) {//개인회원가입
	
		String sql = "insert into cus(cus_id,cus_pwd1,cus_pwd2,cus_email,cus_name,cus_phone) values( ?, ?, ?, ?,?,?)";
		try {
			conn=Connect.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd1);
			pstmt.setString(3, userPwd2);
			pstmt.setString(4, userName);
			pstmt.setString(5, email);
			pstmt.setString(6, userNum);
			return pstmt.executeUpdate();  //-1이 아닌경우 성공적 회원가입 이루어짐
		} catch (Exception e) {
			e.printStackTrace();
		}
		CloseUtil.close(pstmt);  CloseUtil.close(conn);
		return -1; 	//데이터베이스 오류
	}
	public int join(String userId, String userPwd1,  String userPwd2,
			String userName, String email, String userNum, String saupNum, String cafeName) {//개인회원가입
	
		String sql = "insert into cus values(?,?, ?, ?, ?, ?,?,?)";
		try {
			conn=Connect.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd1);
			pstmt.setString(3, userPwd2);
			pstmt.setString(4, userName);
			pstmt.setString(5, email);
			pstmt.setString(6, userNum);
			pstmt.setString(7, saupNum);
			pstmt.setString(8, cafeName);
			return pstmt.executeUpdate();  //-1이 아닌경우 성공적 회원가입 이루어짐
		} catch (Exception e) {
			e.printStackTrace();
		}
		CloseUtil.close(pstmt);  CloseUtil.close(conn);
		return -1; 	//데이터베이스 오류
	}
	/*public int join(UserBean user) throws NamingException, SQLException {//개인회원가입
		
		String sql = "insert into cus(cus_id,cus_pwd1,cus_pwd2,cus_email,cus_name,cus_phone) values( ?, ?, ?, ?,?,?)";
		try {
			conn=Connect.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getCus_id());
			pstmt.setString(2, user.getCus_pwd1());
			pstmt.setString(3, user.getCus_pwd2());
			pstmt.setString(4, user.getCus_email());
			pstmt.setString(5, user.getCus_name());
			pstmt.setString(6, user.getCus_phone());
			return pstmt.executeUpdate();  //-1이 아닌경우 성공적 회원가입 이루어짐
		} catch (Exception e) {
			e.printStackTrace();
		}
		CloseUtil.close(pstmt);  CloseUtil.close(conn);
		return -1; 	//데이터베이스 오류
	}*/
	public int Sjoin(UserBean user) throws NamingException, SQLException {//사업자회원가입
		String sql = "insert into cus values(?,?, ?, ?, ?, ?,?,?)";
		try {
			conn=Connect.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getCus_id());
			pstmt.setString(2, user.getCus_pwd1());
			pstmt.setString(3, user.getCus_pwd2());
			pstmt.setString(4, user.getCus_email());
			pstmt.setString(5, user.getCus_name());
			pstmt.setString(6, user.getCus_phone());
			pstmt.setString(7, user.getSaup_num());
			pstmt.setString(8, user.getSaup_cafe());
			return pstmt.executeUpdate();  //-1이 아닌경우 성공적 회원가입 이루어짐
		} catch (Exception e) {
			e.printStackTrace();
		}
		CloseUtil.close(pstmt);  CloseUtil.close(conn);
		return -1; 	//데이터베이스 오류
	}
	public int update(UserBean user, String uid){
		
		String sql="update cus set cus_pwd1=?, cus_pwd2=?, cus_email=?, cus_name=?, cus_phone=?, saup_num=?, saup_cafe=? where cus_id=?";
		try {
			conn=Connect.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getCus_pwd1());
			pstmt.setString(2, user.getCus_pwd2());
			pstmt.setString(3, user.getCus_email());
			pstmt.setString(4, user.getCus_name());
			pstmt.setString(5, user.getCus_phone());
			pstmt.setString(6, user.getSaup_num());
			pstmt.setString(7, user.getSaup_cafe());
			pstmt.setString(8, uid);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		return -1;
	}

	public int registerCheck(String userId) {
		String sql="select * from cus where cus_id=?";
		try {
			conn=Connect.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs= pstmt.executeQuery();
			
				if(rs.next()|| userId.equals(""))
					return 0; 	
				else return 1;	
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		return -1; //데이터베이스 오류
	}
}
