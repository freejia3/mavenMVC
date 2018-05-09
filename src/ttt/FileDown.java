package ttt;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

public class FileDown implements Action{

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		//fileName, path
		//URLEncoder, setHeader
		//sos, fis

		
		String fileName = request.getParameter("file");
		System.out.println("FileDown.java - fileName:"+fileName);
		String path = request.getRealPath("up");
		path = "F:\\jia\\mvcWork\\mvcJsp\\WebContent\\up";

		try {
			//java, jsp와 같은 back단은 한글은 아는데, html, javascript는 모르니까 인코딩 필요함.
			String en = URLEncoder.encode(fileName,"utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+en);

			//파일 스트림 열고 버퍼 만들어서 파일을 쓴다
			//Byte단위 입출력 클래스: FileInputStream

			//sos가 파일을 쓴다.
			ServletOutputStream sos = response.getOutputStream();
			//fis가 패스와 이름으로 파일을 읽어온다
			FileInputStream fis = new FileInputStream(path+"\\"+fileName);
			byte [] buf = new byte[1024];

			//fis에 파일내용이 있으면,
			while(fis.available()>0)
			{
				//버퍼에 넣었을때의 몇개의 길이인지 길이를 잰다
				int len = fis.read(buf);
				//buf에서 0부터 len만큼 쓴다
				sos.write(buf,0,len);
			}

			fis.close();
			sos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
