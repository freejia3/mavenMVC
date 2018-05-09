package ttt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

public class ReplyForm implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		//GID : group ID. 원글의 번호
		//ID: 현재글의 번호
		//seq : 그룹 안에서의 순서(상하)
		//lev : 옆으로 몇 칸을 밀지를 계산 (좌우)
		ActionData data = new ActionData();

		//일단, 현재글을 가져와야 한다
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.detail(Integer.parseInt(request.getParameter("id")));
		dao.close();

		//제목 및 내용 바꿔줌
		vo.setTitle("[답변]"+vo.getTitle());
		vo.setContent("[답변]"+vo.getContent());

		request.setAttribute("data", vo);
		//insertForm이나 modifyForm 다시 써도 상관은 없다.
		request.setAttribute("main", "replyForm.jsp");

		return data;
	}

}
