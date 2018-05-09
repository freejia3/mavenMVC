package model;

public class ActionData {
	boolean redirect = false;
	String path = null;

	//리다이렉트 할지말지결정 -- true면 redirect. false면 디스패처forward
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}


}
