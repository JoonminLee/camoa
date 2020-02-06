package com.javalaec.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalaec.ex.dto.BDto;
import com.javalaec.ex.dto.CDto;

import dbclose.util.CloseUtil;

public class BDao {
	DataSource dataSource;

	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc:PBoardDB");
			// DBMS 에러를 찾는데 도움
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write(String bTitle, String bName, String bContent) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String query = "insert into mvc_board( bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?,?,?,0,mvc_board_seq.currval,0,0)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	}
	}
	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "select bId, bName, bTitle, bContent,TO_CHAR(bDate, 'YYYY-MM-DD HH24:MI:SS') AS bDateFmt, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
//			String query = "select * from mvc_board order by bGroup desc, bStep asc";
//			System.out.println(query.toString());
			
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

//			 bid, bName, bTitle, bContent,  bHit, bGroup, bStep, bIndent
			dtos = new ArrayList<BDto>();
			while (resultSet.next()) {
				int bId = resultSet.getInt("bid");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
//				Timestamp bDate = resultSet.getTimestamp("bDate"); //TO_CHAR(bDate, 'YYYY-MM-DD HH24:MI:SS') AS bDateFmt
				String bDateFmt = resultSet.getString("bDateFmt");	
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDateFmt, bHit, bGroup, bStep, bIndent);
				
				dtos.add(dto);
				//return dtos;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return dtos;

	}
	public int getListAllCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM P_BBS"); //
			resultSet = preparedStatement.executeQuery();
			
			if( resultSet.next() ) count = resultSet.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CloseUtil.close(resultSet); 
			CloseUtil.close(preparedStatement ); 
			CloseUtil.close(connection);
		}
		return count;
	}
	public BDto contentView(String strID) {
		upHit(strID);

		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = dataSource.getConnection();

			String query = "select bId, bName, bTitle, bContent, TO_CHAR(bDate, 'YYYY-MM-DD HH24:MI:SS') AS bDateFmt, bHit, bGroup, bStep, bIndent from mvc_board where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strID));
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				String bDateFmt = resultSet.getString("bDateFmt");
//				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");

				dto = new BDto(bId, bName, bTitle, bContent, bDateFmt, bHit, bGroup, bStep, bIndent);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	private void upHit(String bId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,  bId);
			resultSet = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {   
				e2.printStackTrace();
			}
		}
	}

	public void modify(String bId, String bTitle, String bContent) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
   			String query = "update mvc_board set bTitle= ?, bContent=? where bId=?";
   			preparedStatement = connection.prepareStatement(query);
   			preparedStatement.setString(1, bTitle);
   			preparedStatement.setString(2, bContent);
   			preparedStatement.setInt(3, Integer.parseInt(bId));   	
			preparedStatement.executeUpdate();
   					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {   
				e2.printStackTrace();
			}
		}

	}

	public void delete(String strID) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement  = null;
				
		try {
			connection = dataSource.getConnection();
			String query = "delete from mvc_board where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strID));
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {   
				e2.printStackTrace();
			}
		}
	}

	public ArrayList<CDto> commentList(String strID) {
		// TODO Auto-generated method stub
		ArrayList<CDto> cdtos = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "select cNum, cContent, cName, TO_CHAR(cDate, 'YYYY-MM-DD HH24:MI:SS') AS cDateFmt ,cStep, cLevel, cMom from c_board where cMom=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strID));
			resultSet = preparedStatement.executeQuery();
//			String query = "select * from mvc_board order by bGroup desc, bStep asc";
//			System.out.println(query.toString());
			
			
			cdtos = new ArrayList<CDto>();
			while (resultSet.next()) {
				//cNum, cContent, cName,cDate, cStep, cLevel, cMom	
				int cNum = resultSet.getInt("cNum");
				String cContent = resultSet.getString("cContent");
				String cName = resultSet.getString("cName");
				String cDateFmt = resultSet.getString("cDateFmt");
//				Timestamp cDate = resultSet.getTimestamp("cDate"); //cDateFmt
				int cStep = resultSet.getInt("cStep");
				int cLevel = resultSet.getInt("cLevel");
				int cMom = resultSet.getInt("cMom");
				
				CDto cdto = new CDto(cNum, cContent, cName, cDateFmt, cStep, cLevel, cMom);
				
				cdtos.add(cdto);
				//return ctos;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return cdtos;
	}

	public void write_comment(String cContent, String cName, String bId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String query = "insert into c_board(cNum, cContent, cName, cStep, cLevel, cMom) values (mvc_board_seq.nextval, ?,?,0,0,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cContent);
			preparedStatement.setString(2, cName);
			preparedStatement.setInt(3, Integer.parseInt(bId));
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	}
	}
}
