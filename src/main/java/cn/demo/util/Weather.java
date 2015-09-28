package cn.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Weather {
 
	public String getCityWeather(String areaid,String area,String newtime) throws IOException{
		URL u=new URL("http://route.showapi.com/9-2?showapi_appid=10261&showapi_timestamp="+newtime+"&areaid="+areaid+"&area="+area+"&needMoreDay=0&needIndex=0&needHourData=0&showapi_sign=09ce6a76e9a749c0be4c4eba6b890671");
		InputStream in=u.openStream();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		try {
			byte buf[]=new byte[1024];
			int read = 0;
			while ((read = in.read(buf)) > 0) {
				out.write(buf, 0, read);
			}
		}  finally {
			if (in != null) {
				in.close();
			}
		}
		byte b[]=out.toByteArray( );
		String result = new String(b,"utf-8");
		return result;
	}
}
