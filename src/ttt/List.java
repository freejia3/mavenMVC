package ttt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.BoardDAO;

public class List implements Action{
	//List는  Action에 있는 execute메소드를 재정의해서 쓴다
	//클래스에 인터페이스 상속 시 인터페이스 내에 정의된 모든 추상 메소드를 구현하여야만 정상적인 컴파일이 가능
	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		//page
		//limit, pageLimit
		//start, end
		//startPage, endPage
		//totalCount()
		//totalPage++
		//endPage = totalPage;


		ActionData res = new ActionData();

		int page = 1;
		//limit = 한 페이지에 보여주는 글 수
		//pageLimit = 하단에 한 번에 보여주는 페이지번호 수
		int limit = 3, pageLimit=4;

		//만약 페이지 이동이 있었을 경우, parameter로 받아온 page를 반영한다 
		if(request.getParameter("page")!=null &&
				!request.getParameter("page").equals("")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		//한 페이지에 보이는 글의 시작번호, 끝 번호
		int start = (page-1)*limit+1;
		int end = page*limit;

		//하단에 보이는 페이지 번호들 중에 첫번째 페이지, 마지막 페이지 번호를 정해준다
		int startPage = (page-1)/pageLimit*pageLimit+1;
		int endPage = startPage+pageLimit-1;

		//total:글의 총 개수 가져옴
		BoardDAO dao = new BoardDAO();
		int total = dao.totalCount();

		//totalPage: 최대 페이지 번호
		int totalPage = total/limit;

		if(total%limit != 0) {
			totalPage++;
		}
		//endPage의 최대 페이지 번호로 지정
		if(endPage>totalPage) {
			endPage = totalPage;
		}

		request.setAttribute("page", page);
		request.setAttribute("start", start);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("data", dao.list(start, end));  //DB를 다녀온당
		request.setAttribute("main", "list.jsp");

		return res;
	}

}
