package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

/**
 * Servlet implementation class F_Controller
 */
@WebServlet("/board/*")
@MultipartConfig(
	location="C:\\tomcat\\temp",
	maxFileSize=1024*5000,
	maxRequestSize=1024*1024*100,
	fileSizeThreshold=1024*1024*10
)
public class F_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//올라가라규!!!!!
    /**
     * @see HttpServlet#HttpServlet()
     */
    public F_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getRequestURL());
		//http://localhost:8080/mvcJsp/board/List
		//System.out.println(request.getContextPath());
		///mvcJsp

		//침입자 탐지!
		if(request.getRemoteAddr().equals("192.168.0.12")) {
			System.out.println("찬이 왔다감.");
		}else if(request.getRemoteAddr().equals("192.168.0.14")||
				request.getRemoteAddr().equals("192.168.0.39") ||
				request.getRemoteAddr().equals("192.168.0.11")) {
			request.getRequestDispatcher("../view/getout.jsp").
			forward(request, response);
		}else {
			System.out.println("잡았다요놈!"+request.getRemoteAddr());
		}

		request.setCharacterEncoding("utf-8");

		//service syso출력값 -->List
		String service = request.getRequestURI().substring(
				(request.getContextPath()+"/board/").length());
		System.out.println("service: "+service);
		try {
			//object니까 Action으로 형변환 필요
			//service에 List가 저장되어 있으니 Action중에서도 List생성해서 를 실행함.
			//즉, 모든 애들이 이런식으로 각자의 class를 생성하고.
			//Action 인터페이스를 구현하니까 모두 같은 이름인 execute메소드를 돌린다.
			Action action = (Action)Class.forName("ttt."+service).newInstance();

			//List에서 재정의한 execute의 결과값이 data에 들어간다
			ActionData data = action.execute(request, response);

/*			System.out.println("data.getPath():"+data.getPath()); */
			if(data!=null) {
				if(data.isRedirect())
				{
					response.sendRedirect(data.getPath());
				}else {
					//data.getPath() --> List에서 setPath("list.jsp")의 결과가 들어감
					request.getRequestDispatcher("../view/template.jsp").
					forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
