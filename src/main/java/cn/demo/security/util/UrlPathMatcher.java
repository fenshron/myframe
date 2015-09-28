package cn.demo.security.util;

public class UrlPathMatcher {

	public boolean pathMatchesUrl(String url,String resURL){
		
		if(url.equals(resURL)){
			return true;
		}		
		return false;
	}
	
}
