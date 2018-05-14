package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BoardVO {
	Integer id, gid, seq, lev, cnt;
	Date reg_date;
	String pname, pw, title, content, upfile;

	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnt == null) ? 0 : cnt.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((gid == null) ? 0 : gid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lev == null) ? 0 : lev.hashCode());
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		result = prime * result + ((pw == null) ? 0 : pw.hashCode());
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((upfile == null) ? 0 : upfile.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardVO other = (BoardVO) obj;
		if (cnt == null) {
			if (other.cnt != null)
				return false;
		} else if (!cnt.equals(other.cnt))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (gid == null) {
			if (other.gid != null)
				return false;
		} else if (!gid.equals(other.gid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lev == null) {
			if (other.lev != null)
				return false;
		} else if (!lev.equals(other.lev))
			return false;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
			return false;
		if (pw == null) {
			if (other.pw != null)
				return false;
		} else if (!pw.equals(other.pw))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;

		if (upfile == null) {
			if (other.upfile != null)
				return false;
		} else if (!upfile.equals(other.upfile))
			return false;

		if (reg_date == null) {
			if (other.reg_date != null)
				return false;
		} else if (!sdf.format(reg_date).equals(sdf.format(other.reg_date)))
			return false;


		return true;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getLev() {
		return lev;
	}
	public void setLev(Integer lev) {
		this.lev = lev;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpfile() {
		//detail.jsp에서 파일 업로드 처리 참고
		if(upfile==null)
			upfile="";

		return upfile;
	}
	public void setUpfile(String upfile) {
		this.upfile = upfile;
	}

	//이미지 파일인지를 구분
	public boolean isImg() {
		if(upfile==null || upfile.equals("")) {
			return false;
		}

		ArrayList<String> imgs = new ArrayList<>();
		imgs.add("jpg");
		imgs.add("jpeg");
		imgs.add("gif");
		imgs.add("png");
		imgs.add("bmp");

		String exp = upfile.substring(upfile.lastIndexOf(".")+1).toLowerCase();

		return imgs.contains(exp);
	}
	@Override
	public String toString() {
		return "BoardVO [id=" + id + ", gid=" + gid + ", seq=" + seq + ", lev=" + lev + ", cnt=" + cnt + ", reg_date="
				+ reg_date + ", pname=" + pname + ", pw=" + pw + ", title=" + title + ", content=" + content
				+ ", upfile=" + upfile + "]";
	}




}
