package ttt;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Action;
import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

public class InsertReg implements Action{

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		//Collection<Part> request.getParts()
		//part.getName()
		//id = insert(vo)
		//setPath("Detail?id="+id)

		//fileUpload
		//-request.getPart("upfile")
		//-pp.getContentType()
		//-pp.getHeader("Content-Disposition")

		//fileSave
		//-pos, exp, fileDo
		//ff.exist()
		//pp.write()
		//pp.delete()


		ActionData data = new ActionData();

		BoardVO vo = new BoardVO();

		try {
			Collection<Part> parts = request.getParts();
			for(Part part : parts) {
				//upfile이 아니면
				if(!part.getName().equals("upfile")) {
					switch(part.getName()) {
						case "title" :
							vo.setTitle(request.getParameter("title"));
							break;
						case "pname" :
							vo.setPname(request.getParameter("pname"));
							break;
						case "pw" :
							vo.setPw(request.getParameter("pw"));
							break;
						case "content" :
							vo.setContent(request.getParameter("content"));
							break;
					}
				}else {
					vo.setUpfile(fileUpload(request));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//data insert하고, 최신 id를 리턴받음
		int id = new BoardDAO().insert(vo);

		//방금 올린 글의 id를 가지고 Detail 페이지로 redirect
		data.setRedirect(true);
		data.setPath("Detail?id="+id);

		System.out.println("insert success");

		return data;
	}

	//파일명 추출
	public String fileUpload(HttpServletRequest request) {
		try {
			Part pp = request.getPart("upfile");

			if(pp.getContentType()!=null) {

				String fileName="";

				for(String hh: pp.getHeader("Content-Disposition").split(";")) {
					if(hh.trim().startsWith("filename")) {
						fileName=
					hh.substring(hh.indexOf("=")+1).trim().replaceAll("\"", "");
					}
				}

				if(!fileName.equals("")) {
					return fileSave(pp, fileName, request);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		//파일 안올리는 경우
		return "";
	}

	public String fileSave(Part pp, String fileName, HttpServletRequest request ) {
		int pos = fileName.lastIndexOf(".");
		//파일도메인
		String fileDo = fileName.substring(0, pos);
		System.out.println("fileDo:"+fileDo);
		//파일 확장자
		String exp = fileName.substring(pos);
		System.out.println("exp:"+exp);

		String path = request.getRealPath("up"+"\\");
		path = "F:\\jia\\mvcWork\\mvcJsp\\WebContent\\up\\";

		int cnt = 0;
		File ff = new File(path+fileName);

		//이미 존재하는 파일명에 cnt붙여주기
		while(ff.exists()) {
			fileName = fileDo+"_"+(cnt++)+exp;
			ff = new File(path+fileName);
		}
		//파일 쓰기
		try {
			pp.write(path+fileName);
			pp.delete();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileName;
	}

}
