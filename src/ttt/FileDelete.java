package ttt;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

public class FileDelete implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		//vo에 데이터 저장
		//sch로 vo에 파일명 받아오기
		//sch!=null이면, up에서 삭제 및 fileDelete()
		//close();
		//modifyForm.jsp로 이동

		BoardVO vo = new BoardVO();

		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		vo.setPw(request.getParameter("pw"));
		vo.setPname(request.getParameter("pname"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		//파일 삭제 실패시 기존의 파일을 그냥 가지고있음
		vo.setUpfile(request.getParameter("upfile"));

		String msg = "암호 인증 실패";

		BoardDAO dao = new BoardDAO();

		//sch로 암호 인증여부 확인
		if(dao.sch(vo) != null) {
			msg = "파일 삭제 성공";
			//파일을 지울꺼니까 파일내용을 안받는다
			vo.setUpfile("");

			//up에 있는 파일 삭제
			String path= request.getRealPath("up");
			path = "F:\\jia\\mvcWork\\mvcJsp\\WebContent\\up";
			new File(path+"\\"+request.getParameter("upfile")).delete();

			//DB에 있는 파일명을 null로 만듬
			dao.fileDelete(vo.getId());
		}
		//DB닫음
		dao.close();

		//파일 삭제 후 '수정'페이지로 간다. 수정페이지에서 보여줄 데이터를 vo로 가지고간다
		request.setAttribute("msg", msg);
		request.setAttribute("main", "modifyForm.jsp");
		request.setAttribute("data", vo);

		return new ActionData();
	}

}
