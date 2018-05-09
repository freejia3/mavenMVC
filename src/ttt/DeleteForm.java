package ttt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

public class DeleteForm implements Action{

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		//deleteForm.jsp로 이동

		ActionData data = new ActionData();

		request.setAttribute("main", "deleteForm.jsp");
		return data;
	}

}
