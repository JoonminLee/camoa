package com.kosta.pboardModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbclose.util.CloseUtil;
import com.kosta.pboardModel.P_bbsVO;

public class P_bbsDAO {
	private static P_bbsDAO instance = new P_bbsDAO();	
	
	public static P_bbsDAO getInstance() {	return instance;}
	public P_bbsDAO() {	} 
	
	public Connection getConnection() throws Exception {
		
		InitialContext initCtx = new InitialContext(); 
		DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc:PBoardDB");

		return ds.getConnection();
	} // getConnection() end
//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////insert//////////////////////////////////////
	public void insert(P_bbsVO vo)  {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = vo.getCf_num();
		ResultSet rs = null;
		String sql = "";
		try {
		conn = getConnection();
		pstmt = conn.prepareStatement("SELECT MAX(Cf_NUM) FROM P_BBS");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			num = rs.getInt(1)+1;
		}else {
			num = 1; 
		}
		
		sql = "INSERT INTO P_BBS(CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL, ";
		sql += "CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE) ";
		sql += "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,0,0,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.setString(2, vo.getCf_id());
		pstmt.setString(3, vo.getCf_name());
		pstmt.setString(4, vo.getCf_address());
		pstmt.setString(5, vo.getCf_phone());
		pstmt.setInt(6, vo.getCf_ame());
		pstmt.setInt(7, vo.getCf_latte());
		pstmt.setInt(8, vo.getCf_caramel());
		pstmt.setInt(9, vo.getCf_mocha());
		pstmt.setInt(10, vo.getCf_vanila());
		pstmt.setString(11, vo.getCf_workhour());
		pstmt.setString(12, vo.getCf_park());
		pstmt.setString(13, vo.getCf_intro());
		pstmt.setTimestamp(14, vo.getCf_reg_date());

		pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////allcount//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
	public int getListAllCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM P_BBS"); //
			rs = pstmt.executeQuery();
			
			if( rs.next() ) count = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		
		return count;
	} // end getListAllCount()
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////아메리카노 리스트//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
	public ArrayList<P_bbsVO> amelist(int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<P_bbsVO> list = null;  
		
		try {
			conn = getConnection();
	
			String sql = " SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, R";
			sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, ROWNUM R"; 
			sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE";
			sql += " FROM P_BBS order by cf_ame asc)) WHERE R>=? AND R<=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  start);
			pstmt.setInt(2,  end);
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				list = new ArrayList<P_bbsVO>();
				
				do {
					P_bbsVO vo = new P_bbsVO();
					
					vo.setCf_num(rs.getInt("cf_num"));
					vo.setCf_id(rs.getString("cf_id"));
					vo.setCf_name(rs.getString("cf_name"));
					vo.setCf_address(rs.getString("cf_address"));
					vo.setCf_phone(rs.getString("cf_phone"));
					vo.setCf_ame(rs.getInt("cf_ame"));
					vo.setCf_latte(rs.getInt("cf_latte"));
					vo.setCf_caramel(rs.getInt("cf_caramel"));
					vo.setCf_mocha(rs.getInt("cf_mocha"));
					vo.setCf_vanila(rs.getInt("cf_vanila"));
					vo.setCf_workhour(rs.getString("cf_workhour"));
					vo.setCf_park(rs.getString("cf_park"));
					vo.setCf_intro(rs.getString("cf_intro"));
					vo.setCf_recom(rs.getInt("cf_recom"));
					vo.setCf_readcount(rs.getInt("cf_readcount"));
					vo.setCf_reg_date(rs.getTimestamp("cf_reg_date"));
					
					list.add(vo);
				}while(rs.next());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
		CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		return list;
	}
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////라떼리스트//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
	public ArrayList<P_bbsVO> lattelist(int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<P_bbsVO> list = null;  
		
		try {
			conn = getConnection();

			
			String sql = " SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, R";
			sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, ROWNUM R"; 
			sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE";
			sql += " FROM P_BBS order by cf_latte asc)) WHERE R>=? AND R<=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  start);
			pstmt.setInt(2,  end);
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				list = new ArrayList<P_bbsVO>();
				
				do {
					P_bbsVO vo = new P_bbsVO();
					
					vo.setCf_num(rs.getInt("cf_num"));
					vo.setCf_id(rs.getString("cf_id"));
					vo.setCf_name(rs.getString("cf_name"));
					vo.setCf_address(rs.getString("cf_address"));
					vo.setCf_phone(rs.getString("cf_phone"));
					vo.setCf_ame(rs.getInt("cf_ame"));
					vo.setCf_latte(rs.getInt("cf_latte"));
					vo.setCf_caramel(rs.getInt("cf_caramel"));
					vo.setCf_mocha(rs.getInt("cf_mocha"));
					vo.setCf_vanila(rs.getInt("cf_vanila"));
					vo.setCf_workhour(rs.getString("cf_workhour"));
					vo.setCf_park(rs.getString("cf_park"));
					vo.setCf_intro(rs.getString("cf_intro"));
					vo.setCf_recom(rs.getInt("cf_recom"));
					vo.setCf_readcount(rs.getInt("cf_readcount"));
					vo.setCf_reg_date(rs.getTimestamp("cf_reg_date"));
					
					list.add(vo);
				}while(rs.next());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
		CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		return list;
	}
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////라떼리스트//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
public ArrayList<P_bbsVO> caramellist(int start, int end) throws Exception{
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
ArrayList<P_bbsVO> list = null;  

try {
conn = getConnection();


String sql = " SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, R";
sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, ROWNUM R"; 
sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE";
sql += " FROM P_BBS order by cf_caramel asc)) WHERE R>=? AND R<=?";

pstmt = conn.prepareStatement(sql);
pstmt.setInt(1,  start);
pstmt.setInt(2,  end);
rs = pstmt.executeQuery();

if( rs.next() ) {
list = new ArrayList<P_bbsVO>();

do {
P_bbsVO vo = new P_bbsVO();

vo.setCf_num(rs.getInt("cf_num"));
vo.setCf_id(rs.getString("cf_id"));
vo.setCf_name(rs.getString("cf_name"));
vo.setCf_address(rs.getString("cf_address"));
vo.setCf_phone(rs.getString("cf_phone"));
vo.setCf_ame(rs.getInt("cf_ame"));
vo.setCf_latte(rs.getInt("cf_latte"));
vo.setCf_caramel(rs.getInt("cf_caramel"));
vo.setCf_mocha(rs.getInt("cf_mocha"));
vo.setCf_vanila(rs.getInt("cf_vanila"));
vo.setCf_workhour(rs.getString("cf_workhour"));
vo.setCf_park(rs.getString("cf_park"));
vo.setCf_intro(rs.getString("cf_intro"));
vo.setCf_recom(rs.getInt("cf_recom"));
vo.setCf_readcount(rs.getInt("cf_readcount"));
vo.setCf_reg_date(rs.getTimestamp("cf_reg_date"));

list.add(vo);
}while(rs.next());

}
} catch (Exception e) {
e.printStackTrace();
}finally {
}
CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
return list;
}
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////라떼리스트//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
public ArrayList<P_bbsVO> mochalist(int start, int end) throws Exception{
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
ArrayList<P_bbsVO> list = null;  

try {
conn = getConnection();


String sql = " SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, R";
sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, ROWNUM R"; 
sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE";
sql += " FROM P_BBS order by cf_mocha asc)) WHERE R>=? AND R<=?";

pstmt = conn.prepareStatement(sql);
pstmt.setInt(1,  start);
pstmt.setInt(2,  end);
rs = pstmt.executeQuery();

if( rs.next() ) {
list = new ArrayList<P_bbsVO>();

do {
P_bbsVO vo = new P_bbsVO();

vo.setCf_num(rs.getInt("cf_num"));
vo.setCf_id(rs.getString("cf_id"));
vo.setCf_name(rs.getString("cf_name"));
vo.setCf_address(rs.getString("cf_address"));
vo.setCf_phone(rs.getString("cf_phone"));
vo.setCf_ame(rs.getInt("cf_ame"));
vo.setCf_latte(rs.getInt("cf_latte"));
vo.setCf_caramel(rs.getInt("cf_caramel"));
vo.setCf_mocha(rs.getInt("cf_mocha"));
vo.setCf_vanila(rs.getInt("cf_vanila"));
vo.setCf_workhour(rs.getString("cf_workhour"));
vo.setCf_park(rs.getString("cf_park"));
vo.setCf_intro(rs.getString("cf_intro"));
vo.setCf_recom(rs.getInt("cf_recom"));
vo.setCf_readcount(rs.getInt("cf_readcount"));
vo.setCf_reg_date(rs.getTimestamp("cf_reg_date"));

list.add(vo);
}while(rs.next());

}
} catch (Exception e) {
e.printStackTrace();
}finally {
}
CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
return list;
}
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////라떼리스트//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
public ArrayList<P_bbsVO> vanilalist(int start, int end) throws Exception{
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
ArrayList<P_bbsVO> list = null;  

try {
conn = getConnection();


String sql = " SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, R";
sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, ROWNUM R"; 
sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE";
sql += " FROM P_BBS order by cf_vanila asc)) WHERE R>=? AND R<=?";

pstmt = conn.prepareStatement(sql);
pstmt.setInt(1,  start);
pstmt.setInt(2,  end);
rs = pstmt.executeQuery();

if( rs.next() ) {
list = new ArrayList<P_bbsVO>();

do {
P_bbsVO vo = new P_bbsVO();

vo.setCf_num(rs.getInt("cf_num"));
vo.setCf_id(rs.getString("cf_id"));
vo.setCf_name(rs.getString("cf_name"));
vo.setCf_address(rs.getString("cf_address"));
vo.setCf_phone(rs.getString("cf_phone"));
vo.setCf_ame(rs.getInt("cf_ame"));
vo.setCf_latte(rs.getInt("cf_latte"));
vo.setCf_caramel(rs.getInt("cf_caramel"));
vo.setCf_mocha(rs.getInt("cf_mocha"));
vo.setCf_vanila(rs.getInt("cf_vanila"));
vo.setCf_workhour(rs.getString("cf_workhour"));
vo.setCf_park(rs.getString("cf_park"));
vo.setCf_intro(rs.getString("cf_intro"));
vo.setCf_recom(rs.getInt("cf_recom"));
vo.setCf_readcount(rs.getInt("cf_readcount"));
vo.setCf_reg_date(rs.getTimestamp("cf_reg_date"));

list.add(vo);
}while(rs.next());

}
} catch (Exception e) {
e.printStackTrace();
}finally {
}
CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
return list;
}
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////라떼리스트//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
public ArrayList<P_bbsVO> recomlist(int start, int end) throws Exception{
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
ArrayList<P_bbsVO> list = null;  

try {
conn = getConnection();


String sql = " SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, R";
sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, ROWNUM R"; 
sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE";
sql += " FROM P_BBS order by cf_recom desc)) WHERE R>=? AND R<=?";

pstmt = conn.prepareStatement(sql);
pstmt.setInt(1,  start);
pstmt.setInt(2,  end);
rs = pstmt.executeQuery();

if( rs.next() ) {
list = new ArrayList<P_bbsVO>();

do {
P_bbsVO vo = new P_bbsVO();

vo.setCf_num(rs.getInt("cf_num"));
vo.setCf_id(rs.getString("cf_id"));
vo.setCf_name(rs.getString("cf_name"));
vo.setCf_address(rs.getString("cf_address"));
vo.setCf_phone(rs.getString("cf_phone"));
vo.setCf_ame(rs.getInt("cf_ame"));
vo.setCf_latte(rs.getInt("cf_latte"));
vo.setCf_caramel(rs.getInt("cf_caramel"));
vo.setCf_mocha(rs.getInt("cf_mocha"));
vo.setCf_vanila(rs.getInt("cf_vanila"));
vo.setCf_workhour(rs.getString("cf_workhour"));
vo.setCf_park(rs.getString("cf_park"));
vo.setCf_intro(rs.getString("cf_intro"));
vo.setCf_recom(rs.getInt("cf_recom"));
vo.setCf_readcount(rs.getInt("cf_readcount"));
vo.setCf_reg_date(rs.getTimestamp("cf_reg_date"));

list.add(vo);
}while(rs.next());

}
} catch (Exception e) {
e.printStackTrace();
}finally {
}
CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
return list;
}
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////라떼리스트//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
public ArrayList<P_bbsVO> readcountlist(int start, int end) throws Exception{
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
ArrayList<P_bbsVO> list = null;  

try {
conn = getConnection();


String sql = " SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, R";
sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, ROWNUM R"; 
sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE";
sql += " FROM P_BBS order by cf_readcount desc)) WHERE R>=? AND R<=?";

pstmt = conn.prepareStatement(sql);
pstmt.setInt(1,  start);
pstmt.setInt(2,  end);
rs = pstmt.executeQuery();

if( rs.next() ) {
list = new ArrayList<P_bbsVO>();

do {
P_bbsVO vo = new P_bbsVO();

vo.setCf_num(rs.getInt("cf_num"));
vo.setCf_id(rs.getString("cf_id"));
vo.setCf_name(rs.getString("cf_name"));
vo.setCf_address(rs.getString("cf_address"));
vo.setCf_phone(rs.getString("cf_phone"));
vo.setCf_ame(rs.getInt("cf_ame"));
vo.setCf_latte(rs.getInt("cf_latte"));
vo.setCf_caramel(rs.getInt("cf_caramel"));
vo.setCf_mocha(rs.getInt("cf_mocha"));
vo.setCf_vanila(rs.getInt("cf_vanila"));
vo.setCf_workhour(rs.getString("cf_workhour"));
vo.setCf_park(rs.getString("cf_park"));
vo.setCf_intro(rs.getString("cf_intro"));
vo.setCf_recom(rs.getInt("cf_recom"));
vo.setCf_readcount(rs.getInt("cf_readcount"));
vo.setCf_reg_date(rs.getTimestamp("cf_reg_date"));

list.add(vo);
}while(rs.next());

}
} catch (Exception e) {
e.printStackTrace();
}finally {
}
CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
return list;
}
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////allcount//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////	
	public ArrayList<P_bbsVO> getSelectAll(int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<P_bbsVO> list = null;  
		
		try {
			conn = getConnection();
			
			String sql = " SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, R";
			sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE, ROWNUM R"; 
			sql += " FROM(SELECT CF_NUM,CF_ID,CF_NAME,CF_ADDRESS,CF_PHONE,CF_AME,CF_LATTE,CF_CARAMEL,CF_MOCHA, CF_VANILA,CF_WORKHOUR,CF_PARK,CF_INTRO,CF_RECOM,CF_READCOUNT,CF_REG_DATE";
			sql += " FROM P_BBS order by cf_num desc)) WHERE R>=? AND R<=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  start);
			pstmt.setInt(2,  end);
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				list = new ArrayList<P_bbsVO>();
				
				do {
					P_bbsVO vo = new P_bbsVO();
					
					vo.setCf_num(rs.getInt("cf_num"));
					vo.setCf_id(rs.getString("cf_id"));
					vo.setCf_name(rs.getString("cf_name"));
					vo.setCf_address(rs.getString("cf_address"));
					vo.setCf_phone(rs.getString("cf_phone"));
					vo.setCf_ame(rs.getInt("cf_ame"));
					vo.setCf_latte(rs.getInt("cf_latte"));
					vo.setCf_caramel(rs.getInt("cf_caramel"));
					vo.setCf_mocha(rs.getInt("cf_mocha"));
					vo.setCf_vanila(rs.getInt("cf_vanila"));
					vo.setCf_workhour(rs.getString("cf_workhour"));
					vo.setCf_park(rs.getString("cf_park"));
					vo.setCf_intro(rs.getString("cf_intro"));
					vo.setCf_recom(rs.getInt("cf_recom"));
					vo.setCf_readcount(rs.getInt("cf_readcount"));
					vo.setCf_reg_date(rs.getTimestamp("cf_reg_date"));
					
					list.add(vo);
				}while(rs.next());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
		CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		return list;
	}
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////議고?���닔//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////	
public int updateReadCount(int num) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	P_bbsVO vo = null;
	int readcount = 0;
	String sql = "";
	try {
		conn = getConnection();
		sql = "UPDATE p_bbs SET READCOUNT = READCOUNT + 1 WHERE cf_NUM = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.executeUpdate();
		sql = "select cf_readcount from p_bbs where cf_num = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		if(rs.next() ) {
			readcount = rs.getInt(1);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		
	}
	return readcount;
}
	
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////getDataDetail//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
	
public P_bbsVO getDataDetail(int cf_num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		P_bbsVO vo = null;
		String sql = ""; 
		try {
			conn = getConnection();
			sql = "UPDATE P_BBS SET CF_READCOUNT = CF_READCOUNT + 1 WHERE CF_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cf_num);
			pstmt.executeQuery();
			
			pstmt = conn.prepareStatement("SELECT * FROM P_BBS WHERE CF_NUM = ?");
			pstmt.setInt(1, cf_num);
			rs = pstmt.executeQuery();
			
			
			if( rs.next() ) {
				vo = new P_bbsVO();
				
				vo.setCf_num(rs.getInt("cf_num"));
				vo.setCf_id(rs.getString("cf_id"));
				vo.setCf_name(rs.getString("cf_name"));
				vo.setCf_address(rs.getString("cf_address"));
				vo.setCf_phone(rs.getString("cf_phone"));
				vo.setCf_ame(rs.getInt("cf_ame"));
				vo.setCf_latte(rs.getInt("cf_latte"));
				vo.setCf_caramel(rs.getInt("cf_caramel"));
				vo.setCf_mocha(rs.getInt("cf_mocha"));
				vo.setCf_vanila(rs.getInt("cf_vanila"));
				vo.setCf_workhour(rs.getString("cf_workhour"));
				vo.setCf_park(rs.getString("cf_park"));
				vo.setCf_intro(rs.getString("cf_intro"));
				vo.setCf_recom(rs.getInt("cf_recom"));
				vo.setCf_readcount(rs.getInt("cf_readcount"));
				vo.setCf_reg_date(rs.getTimestamp("cf_reg_date"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
		}
		return vo;
}
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////Update////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////

public P_bbsVO update(int num) {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	P_bbsVO vo =  null;
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement("SELECT * FROM P_BBS WHERE cf_NUM = ?");
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			vo = new P_bbsVO();

			vo.setCf_name(rs.getString("cf_name"));
			vo.setCf_address(rs.getString("cf_address"));
			vo.setCf_phone(rs.getString("cf_phone"));
			vo.setCf_ame(rs.getInt("cf_ame"));
			vo.setCf_latte(rs.getInt("cf_latte"));
			vo.setCf_caramel(rs.getInt("cf_caramel"));
			vo.setCf_mocha(rs.getInt("cf_mocha"));
			vo.setCf_vanila(rs.getInt("cf_vanila"));
			vo.setCf_workhour(rs.getString("cf_workhour"));
			vo.setCf_park(rs.getString("cf_park"));
			vo.setCf_intro(rs.getString("cf_intro"));

		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn);
	}
	return vo;
}
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////Update 泥섎?��////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////

public int update(P_bbsVO vo) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs =null;
	
	String sql = "";
	int result = -1;
	
	try {
		conn = getConnection();

				sql = "UPDATE P_BBS SET CF_NAME=?, CF_ADDRESS=?, CF_PHONE=?, CF_AME=?, CF_LATTE=?, CF_CARAMEL=?, ";
				sql += "CF_MOCHA=?, CF_VANILA=?, CF_WORKHOUR=?, CF_PARK=?, CF_INTRO=?";
				sql += " WHERE CF_NUM = ?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getCf_name());
				pstmt.setString(2, vo.getCf_address());
				pstmt.setString(3, vo.getCf_phone());
				pstmt.setInt(4, vo.getCf_ame());
				pstmt.setInt(5, vo.getCf_latte());
				pstmt.setInt(6, vo.getCf_caramel());
				pstmt.setInt(7, vo.getCf_mocha());
				pstmt.setInt(8, vo.getCf_vanila());
				pstmt.setString(9, vo.getCf_workhour());
				pstmt.setString(10, vo.getCf_park());
				pstmt.setString(11, vo.getCf_intro());
				pstmt.setInt(12, vo.getCf_num());
				
				pstmt.executeUpdate();
				result = 1;

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		CloseUtil.close(rs); CloseUtil.close(pstmt);	CloseUtil.close(conn);
	} // end try
	
	return result;
}// end update(vo)
//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////Delete 泥섎?��////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
public int delete(int cf_num) throws Exception{
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	int result = 0;
	String sql = "";
	try {
		conn =getConnection();

		sql = "DELETE FROM P_BBS WHERE CF_NUM = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, cf_num);
		pstmt.executeUpdate();
		result = 1;
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		CloseUtil.close(pstmt);	CloseUtil.close(conn);
	}
	return result;
}
public int check(int num, String cf_id) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String id =null;
	String sql = "";
	int result = 0;
	try {
		conn=getConnection();
		sql="select cf_id from p_bbs where num=?";
		pstmt.getConnection().prepareStatement(sql);
		pstmt.setInt(1, num);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			id = rs.getString("cf_id");
			if(id.equals(cf_id)) {
				result = 1;
			}else
				result = 0;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return result;
}
public int recom(int cf_num) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	int result = 0;
	try {
		conn = getConnection();
		sql = "UPDATE P_BBS SET CF_recom = CF_recom + 1 WHERE CF_NUM = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, cf_num);
		pstmt.executeQuery();
		if(rs.next()) {
				result = 1;
			}else {
				result = 0;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return result;
	
}

}