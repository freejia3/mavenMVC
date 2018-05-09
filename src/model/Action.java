package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Action {
	//action이라는 인터페이스를 구현해서. 이후의 url에 따라 작업이 나뉜다.
	//execute라는 메소드인데 리턴이 ActionData 클래스 !
	//List가 들어오니까. List에서 재정의한 execute가 수행된다
	public ActionData execute(HttpServletRequest request, HttpServletResponse response);
}
