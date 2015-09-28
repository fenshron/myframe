package cn.demo.pageModel;

import java.util.Map;
 
public class ResourcesMenu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String pid;
	private String text;
	private String url;
	private Map<String, Object> attributes;

	/** default constructor */
	public ResourcesMenu() {
	}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

}