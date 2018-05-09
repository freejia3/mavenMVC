package ttt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

public class ModifyReg implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		//sch(vo)
		//이미 파일이 있다면, request.getParameter("upfile")
		//새로 올렸다면 InsertReg().fileUpload(request)
		//modify(vo)
		//close()
		//alert.jsp


		String page = request.getParameter("page");

		BoardVO vo = new BoardVO();
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setPw(request.getParameter("pw"));
		vo.setPname(request.getParameter("pname"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));

		BoardDAO dao = new BoardDAO();
		//sch에서 id,pw일치여부 확인
		BoardVO res = dao.sch(vo);

		String msg="암호 인증 실패";
		String url = "ModifyForm?id="+vo.getId()+"&page="+page;

		//id,pw일치여부 확인 후 파일 업로드 종류도 구분해서 수행
		if(res!=null) {
			//수정시 이미 업로드된 파일이 있는 경우와 수정시 새로 올리는 경우를 구분해준다
			if(request.getParameter("upfile")!=null) { //이미 올려놨던 파일이 있는 경우
				vo.setUpfile(request.getParameter("upfile"));
			}else { //파일이 없어서 새로 올렸으면 업로드하도록
				vo.setUpfile(new InsertReg().fileUpload(request));
			}

			dao.modify(vo);
			msg="변경되었습니다.";
			url="Detail?id="+vo.getId();
		}
		//sch와 modify 모두에서 close() 안해줬기 때문에 여기서 해준다
		dao.close();

		request.setAttribute("main", "alert.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return new ActionData();
	}

}
