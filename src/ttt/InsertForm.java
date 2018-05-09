package ttt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.BoardDAO;

public class InsertForm implements Action{
	//List는  Action에 있는 execute메소드를 재정의해서 쓴다
	//클래스에 인터페이스 상속 시 인터페이스 내에 정의된 모든 추상 메소드를 구현하여야만 정상적인 컴파일이 가능
	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		//insertForm.jsp로 이동

		ActionData res = new ActionData();

		request.setAttribute("main", "insertForm.jsp");

		return res;
	}

}
