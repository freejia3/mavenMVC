package ttt;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

public class DeleteReg implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		//vo에 id, pw를 담는다
		//msg, url설정
		//sch!=null이면 수행
		//-파일이 있다면, up에서 파일 삭제
		//-DB에서 레코드 삭제
		//DB close()
		//alert.jsp로 이동


		int page = Integer.parseInt(request.getParameter("page"));

		//vo에 id, pw를 담는다
		BoardVO vo = new BoardVO();
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setPw(request.getParameter("pw"));

		BoardDAO dao = new BoardDAO();
		//sch(): id, pw가지고 DB에서 파일명있나 확인 후 파일명을 res에 저장한다
		BoardVO res = dao.sch(vo);

		String msg = "암호 인증 실패";
		//삭제 직전 봤던 페이지로 이동
		String url = "DeleteForm?id="+vo.getId()+"&page="+page;

		if(res!=null)
		{
			//DB에 파일이 이미 있다면, 해당경로의 해당 파일명 입력하여 파일을 삭제
			if(!res.getUpfile().equals("")) {

				String path = request.getRealPath("up")+"\\";
				path = "F:\\jia\\mvcWork\\mvcJsp\\WebContent\\up\\";
				//프로젝트 up 파일 안에서 삭제
				new File(path+res.getUpfile()).delete();
			}
			//DB에서 레코드 삭제
			dao.delete(vo.getId());
			msg = "삭제되었습니다.";
			url = "List?page="+page;
		}
		//sch와 delete 모두에서 close() 안해줬기 때문에 여기서 해준다
		dao.close();

		ActionData data = new ActionData();

		request.setAttribute("main", "alert.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return data;
	}

}
