package ttt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

public class ReplyReg implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {

		//폼에서 받은 데이터들을 vo에 담음
		BoardVO vo = new BoardVO();
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setTitle(request.getParameter("title"));
		vo.setPname(request.getParameter("pname"));
		vo.setPw(request.getParameter("pw"));
		vo.setContent(request.getParameter("content"));

		//vo를 reply()로 넘겨서 답변 달고 나서의 새 글의 글번호(id)반환받음
		int id = new BoardDAO().reply(vo);

		ActionData data = new ActionData();

		data.setRedirect(true);
		data.setPath("Detail?id="+id+"&page="+request.getParameter("page"));
		return data;
	}

}
