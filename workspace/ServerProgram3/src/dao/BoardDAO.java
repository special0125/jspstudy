package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;
import dto.ReplyDTO;

public class BoardDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private static DataSource dataSource;
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (con != null) { con.close(); }
			if (ps != null) { ps.close(); }
			if (rs != null) { rs.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, TITLE, AUTHOR, POSTDATE, HIT FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getLong(1));
				dto.setTitle(rs.getString(2));
				dto.setAuthor(rs.getString(3));
				dto.setPostdate(rs.getDate(4));
				dto.setHit(rs.getInt(5));
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	public int getTotalBoardCount() {
		int count = 0;
		try {
			con = dataSource.getConnection();
			sql = "SELECT COUNT(*) FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return count;
	}
	
	public int insertBoard(BoardDTO boardDTO) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, '127.0.0.1', SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, boardDTO.getAuthor());
			ps.setString(2, boardDTO.getTitle());
			ps.setString(3, boardDTO.getContent());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, null);
		}
		return result;
	}
	
	public BoardDTO selectBoardByNo(long no) {
		BoardDTO boardDTO = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, AUTHOR, POSTDATE, IP, HIT, TITLE, CONTENT FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if (rs.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setNo(rs.getLong(1));
				boardDTO.setAuthor(rs.getString(2));
				boardDTO.setPostdate(rs.getDate(3));
				boardDTO.setIp(rs.getString(4));
				boardDTO.setHit(rs.getInt(5));
				boardDTO.setTitle(rs.getString(6));
				boardDTO.setContent(rs.getString(7));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return boardDTO;
	}
	
	public void updateHit(long no) {
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET HIT = HIT + 1 WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	public ReplyDTO insertReply(ReplyDTO replyDTO) {
		ReplyDTO replyDTO2 = null;
		try {
			con = dataSource.getConnection();
			sql = "INSERT NO, AUTHOR CONTENT, IP, BOARD_NO, POSTDATE INTO REPLY VALUES(REPLY_SEQ.NEXTVAL, ?, ?, 127.0.0.1, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			replyDTO.setNo(rs.getLong(1));
			replyDTO.setAuthor(rs.getString(2));
			replyDTO.setContent(rs.getString(3));
			replyDTO.setIp(rs.getString(4));
			replyDTO.setBoardNo(rs.getLong(5));
			replyDTO.setPostdate(rs.getDate(6));
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return replyDTO2;
	}
	
	public int deleteBoard(long no) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, null);
		}
		return result;
	}
	
	public BoardDTO bestHit() {
		BoardDTO boardDTO = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT TITLE, CONTENT, HIT FROM BOARD WHERE HIT = (SELECT MAX(HIT) FROM BOARD)";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setTitle(rs.getString(1));
				boardDTO.setContent(rs.getString(2));
				boardDTO.setHit(rs.getInt(3));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return boardDTO;
		
	}
	
}
