package bbs.hong.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbclose.util.CloseUtil;

public class HongDAO {
	DataSource ds;

	public HongDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc:PBoardDB");
			// DBMS 에러를 찾는데 도움
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<HongCommentVO> clist(String c_hong_num) {
		ArrayList<HongCommentVO> hcvl = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from comment_hong where c_hong_num = ? order by c_hong_num desc, c_num asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(c_hong_num));
			rs = pstmt.executeQuery();

			hcvl = new ArrayList<HongCommentVO>();
			while (rs.next()) {
				int c_num = rs.getInt("c_num");
				String c_hong_name = rs.getString("c_hong_name");
				String c_hong_content = rs.getString("c_hong_content");
				int c_hong_num1 = rs.getInt("c_hong_num");
				Timestamp c_hong_date = rs.getTimestamp("c_hong_date");

				HongCommentVO hcv = new HongCommentVO(c_num, c_hong_name, c_hong_content, c_hong_num1, c_hong_date);

				hcvl.add(hcv);
			}

		} catch (Exception e) {
			System.out.println("comment list 에러");
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);
		}
		return hcvl;
	}

	public ArrayList<HongVO> list(int start, int end) {
		ArrayList<HongVO> hvl = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = " select hong_num, hong_name, hong_subject, hong_content, hong_realpath, hong_filename, hong_count, hong_hit, hong_date, r";
			sql += " FROM(SELECT hong_num, hong_name, hong_subject, hong_content, hong_realpath, hong_filename, hong_count, hong_hit, hong_date, rownum r";
			sql += " FROM(select hong_num, hong_name, hong_subject, hong_content, hong_realpath, hong_filename, hong_count, hong_hit, hong_date";
			sql += " FROM bbs_hong order by hong_num desc)) WHERE R>=? AND R<=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			hvl = new ArrayList<HongVO>();
			while (rs.next()) {
				int hong_num = rs.getInt("hong_num");
				String hong_name = rs.getString("hong_name");
				String hong_subject = rs.getString("hong_subject");
				String hong_content = rs.getString("hong_content");
				String hong_realpath = rs.getString("hong_realpath");
				String hong_filename = rs.getString("hong_filename");
				int hong_count = rs.getInt("hong_count");
				int hong_hit = rs.getInt("hong_hit");
				Timestamp hong_date = rs.getTimestamp("hong_date");

				HongVO hv = new HongVO(hong_num, hong_name, hong_subject, hong_content, hong_realpath, hong_filename,
						hong_count, hong_hit, hong_date);

				hvl.add(hv);
			}

		} catch (Exception e) {
			System.out.println("list 에러");
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);
		}
		return hvl;
	}

	public void write(String hong_subject, String hong_name, String hong_content) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int num;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(hong_num) from bbs_hong");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			} else {
				num = 1;
			}

			String sql = "insert into bbs_hong( hong_num, hong_name, hong_subject,"
					+ " hong_content, hong_realpath, hong_filename, hong_count, hong_hit, hong_date)"
					+ " values (?,?,?,?,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, hong_name);
			pstmt.setString(3, hong_subject);
			pstmt.setString(4, hong_content);
			pstmt.setString(5, null);
			pstmt.setString(6, null);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("write insert 에러");
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);

		}
	}

	public void cwrite(String c_hong_name, String c_hong_content, String c_hong_num) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int num;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(c_num) from comment_hong where c_hong_num = ?");
			pstmt.setInt(1, Integer.parseInt(c_hong_num));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			} else {
				num = 1;
			}
			String sql = "insert into comment_hong ( c_num, c_hong_name, c_hong_content,"
					+ " c_hong_num , c_hong_date) values (?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, c_hong_name);
			pstmt.setString(3, c_hong_content);
			pstmt.setInt(4, Integer.parseInt(c_hong_num));
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("comment write 에러");
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);

		}

	}

	public void modify(String hong_subject, String hong_content, int hong_num) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "update bbs_hong set hong_subject=?, hong_content=? where hong_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hong_subject);
			pstmt.setString(2, hong_content);
			pstmt.setInt(3, hong_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("modify 에러");
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);

		}

	}

	public void delete(int hong_num) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "delete from bbs_hong where hong_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hong_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("delete 에러");
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);
		}
	}

	public void cdelete(int c_num) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "delete from comment_hong where c_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("comment delete 에러");
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);
		}

	}

	public HongVO view(String hong_num) {
		count(hong_num);
		HongVO hv = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from bbs_hong where hong_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(hong_num));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int hong_num1 = rs.getInt("hong_num");
				String hong_name = rs.getString("hong_name");
				String hong_subject = rs.getString("hong_subject");
				String hong_content = rs.getString("hong_content");
				String hong_realpath = rs.getString("hong_realpath");
				String hong_filename = rs.getString("hong_filename");
				int hong_count = rs.getInt("hong_count");
				int hong_hit = rs.getInt("hong_hit");
				Timestamp hong_date = rs.getTimestamp("hong_date");

				hv = new HongVO(hong_num1, hong_name, hong_subject, hong_content, hong_realpath, hong_filename,
						hong_count, hong_hit, hong_date);
			}
		} catch (Exception e) {
			System.out.println("view 에러");
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);
		}

		return hv;
	}

	public HongVO modify_view(String hong_num) {
		HongVO hv = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from bbs_hong where hong_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(hong_num));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int hong_num1 = rs.getInt("hong_num");
				String hong_name = rs.getString("hong_name");
				String hong_subject = rs.getString("hong_subject");
				String hong_content = rs.getString("hong_content");
				String hong_realpath = rs.getString("hong_realpath");
				String hong_filename = rs.getString("hong_filename");
				int hong_count = rs.getInt("hong_count");
				int hong_hit = rs.getInt("hong_hit");
				Timestamp hong_date = rs.getTimestamp("hong_date");

				hv = new HongVO(hong_num1, hong_name, hong_subject, hong_content, hong_realpath, hong_filename,
						hong_count, hong_hit, hong_date);
			}
		} catch (Exception e) {
			System.out.println("modify_view 에러");
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);
		}

		return hv;
	}

	public int hit(String hong_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			sql = "UPDATE bbs_hong SET hong_hit = hong_hit + 1 WHERE hong_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(hong_num));
			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("hit 에러");
		}

		return result;

	}

	private int count(String hong_num) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			conn = ds.getConnection();
			String sql = "UPDATE bbs_hong SET hong_count = hong_count + 1 WHERE hong_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(hong_num));
			pstmt.executeUpdate();
			sql = "select hong_count from bbs_hong where hong_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(hong_num));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("count 에러");
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);
		}
		return count;
	}

	public int all_list() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pcount = 0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM bbs_hong"); //
			rs = pstmt.executeQuery();

			if (rs.next())
				pcount = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);
		}

		return pcount;
	}

}
