package main.java;

import model.BoardDAO;

public class ForMain {
	// 2018. 05. 11 JUnit

	public static void main(String[] args) {

	}

	public void cal() {

		int [] arr = {22, 33, 44, 55, 66, 77};
		System.out.println(arr[1]);
	}

	public int cal2() {
		System.out.println("cal2()");

		return 100;
	}

	public int dbTC() {
		BoardDAO dao = new BoardDAO();
		int res = dao.totalCount();
		System.out.println("res:"+res);
		return res;
	}

}
