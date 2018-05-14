package test.java;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;

import model.BoardDAO;
import model.BoardVO;

class AAA{

	int a, b;

	//hashCode: '내용'이 같은지
	//equals: '객체'가 같은지

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AAA other = (AAA) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		return true;
	}



	public AAA(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}


}

public class BoardDAOTest {

	@Test
	public void testDetail() {
		//fail("Not yet implemented");

		BoardVO vo = new BoardVO();

		vo.setId(165);
		vo.setGid(165);
		vo.setSeq(0);
		vo.setLev(0);
		vo.setCnt(0);
		try {
			vo.setReg_date(
	          new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
	          parse("2018-05-14 10:39:49.0"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vo.setPname("월요일");
		vo.setPw(null);
		vo.setTitle("월요일");
		vo.setContent("입력하라능");
		vo.setUpfile(null);

		BoardDAO dao = new BoardDAO();

		BoardVO res = dao.detail(165);
		dao.close();

		System.out.println(res);
		System.out.println(vo);

		Assert.assertEquals(res, vo);

	}

}
