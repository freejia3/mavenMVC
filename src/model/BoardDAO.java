package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;


/*	public BoardDAO() {
		DataSource ds = null;
		try {
			Context init = new InitialContext();
			System.out.println("init:"+init);
			ds = (DataSource)init.lookup("java:comp/env/oooo");
			con = ds.getConnection();
			System.out.println(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/


	public BoardDAO() {
		try {
			Context init = new InitialContext();
			System.out.println(init);
			DataSource ds = (DataSource)init.lookup("java:comp/env/oooo");
			con = ds.getConnection();

		} catch (Exception e) {
			//e.printStackTrace();

			 try{//에러방지를 위한 처리구간
				 String driver="oracle.jdbc.driver.OracleDriver";
				 Class.forName(driver);
				 String url = "jdbc:oracle:thin:@localhost:1521:xe";
				 String id="hr";
				 String pw ="hr";

			//2. 커넥션 생성
			 con = DriverManager.getConnection(url,id,pw);
			 }catch(Exception e1) {}
		}
	}

	//글의 총 개수 가져온다
	public int totalCount() {

		try {
			sql = "select count(*) from mvcboard";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//close()안해요
		return 0;
	}

	//글을 모두 가져온다
	public ArrayList<BoardVO> list(int start, int end){
		ArrayList<BoardVO> res = new ArrayList<BoardVO>();

		try {
			//gid와 seq 역순으로 읽어오기
			//sql = "select * from mvcBoard order by gid desc, seq";
			sql = "select * from " +
					"(select rownum rnum, tt.* from" +
					"(select * from mvcBoard order by gid desc, seq) tt) " +
					"where rnum>= ? and rnum<= ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, end);
			rs = ptmt.executeQuery();

			//http://localhost:8080/mvcJsp/board/List?page=5 페이지 넘겨가면서 확인하기
			while(rs.next()) {
				BoardVO vo = new BoardVO();

				vo.setGid(rs.getInt("gid"));
				vo.setSeq(rs.getInt("seq"));
				vo.setLev(rs.getInt("lev"));
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setPname(rs.getString("pname"));
				vo.setReg_date(rs.getDate("reg_date"));
				res.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return res;
	}

	public BoardVO detail(int id){

		try {
			sql = "select * from mvcboard where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();

			if(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setId(id);
				vo.setGid(rs.getInt("gid"));
				vo.setLev(rs.getInt("lev"));
				vo.setSeq(rs.getInt("seq"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setTitle(rs.getString("title"));
				vo.setPname(rs.getString("pname"));
				vo.setContent(rs.getString("content"));
				vo.setUpfile(rs.getString("upfile"));
				return vo;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//close()하면안됨

		return null;
	}


	public int insert(BoardVO vo) {
		try {
			//gid, seq, lev : 답변 달때 꼭 필요한 컬럼.
			//cnt:조회수
			sql = "insert into mvcBoard "
					+ "(id, gid, seq, lev, cnt, reg_date, pname, pw, title, content, upfile)"
					+ " values (mvcBoard_Seq.nextval, mvcBoard_Seq.nextval, 0, 0, -1, sysdate, ?, ?, ?, ?, ?)";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, vo.getPname());
			ptmt.setString(2, vo.getPw());
			ptmt.setString(3, vo.getTitle());
			ptmt.setString(4, vo.getContent());
			ptmt.setString(5, vo.getUpfile());

			ptmt.executeUpdate();

			sql="select max(id) from mvcBoard";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();

			rs.next();

			return rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return 0;

	}

	//조회수올림
	public void addCount(int id) {

		try {
			sql = "update mvcboard set cnt = cnt+1 where id = ?";
			ptmt = con.prepareStatement(sql);

			ptmt.setInt(1, id);
			ptmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//close()안해요

	}


	//id, pw가 일치하는 레코드의 파일명을 search하고 파일명을 리턴
	public BoardVO sch(BoardVO vo) {
		try {
			sql = "select * from mvcboard where id = ? and pw = ? ";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, vo.getId());
			ptmt.setString(2, vo.getPw());

			rs = ptmt.executeQuery();

			//칼럼이 있으면 파일명 읽어와서 res에 저장하고 res리턴
			if(rs.next()) {
				BoardVO res = new BoardVO();
				res.setUpfile(rs.getString("upfile"));

				return res;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//close() 안해요 ModifyReg, DeleteReg 참고
		return null;
	}

	//삭제
	public void delete(int id) {
		try {
			sql = "delete from mvcboard where id = ? ";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//close() 안해요
	}

	//수정 modify
	public void modify(BoardVO vo) {
		try {
			sql = "update mvcboard set title=?, pname=?, content=?, upfile=? where id=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, vo.getTitle());
			ptmt.setString(2, vo.getPname());
			ptmt.setString(3, vo.getContent());
			ptmt.setString(4, vo.getUpfile());
			ptmt.setInt(5, vo.getId());
			ptmt.executeUpdate();
			System.out.println("DB modify 성공");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//close() 안해요
	}

	//DB에서 파일만 지운다.
	public void fileDelete(int id) {

		try {
			sql = "update mvcboard set upfile= null where id=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//close()안함
	}

	//답변 달기
	public int reply(BoardVO vo) {
		int res =0;
		//ori:original

		try {
			BoardVO ori = detail(vo.id);
			//현재 글 보다 밑에 글에 대한 seq 증가
			//seq>? 의미 : 지금의 답글 수준보다 더크다면
			sql = "update mvcboard set seq = seq+1 where gid=? and seq > ? ";
			ptmt = con.prepareStatement(sql);

			ptmt.setInt(1, ori.getGid());
			ptmt.setInt(2, ori.getSeq());
			ptmt.executeUpdate();


			//답변 insert
			sql = "insert into mvcboard (id, gid, seq, lev, reg_date, cnt, pname, pw, title, content) "
					+ "values (mvcBoard_Seq.nextval, ?, ?, ?, sysdate, -1, ? ,? ,? ,?)";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, ori.getGid());
			ptmt.setInt(2, ori.getSeq()+1);
			ptmt.setInt(3, ori.getLev()+1);
			ptmt.setString(4,vo.getPname());
			ptmt.setString(5,vo.getPw());
			ptmt.setString(6,vo.getTitle());
			ptmt.setString(7,vo.getContent());

			ptmt.executeUpdate();

			sql = "select max(id) from mvcboard ";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();

			rs.next();
			res = rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
	}

	public void close() {
		if(rs!=null)try {rs.close();} catch (SQLException e) {}
		if(ptmt!=null) try {ptmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
	}

}
