package cn.demo.pageModel;

public class ResourcesPage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String resource_id;
	private String resource_name;
	private String resource_type;
	private String priority;
	private String resource_string;
	private String resource_desc;
	private String enabled;
	private Integer issys;
	private String pid;
	public String getResource_id() {
		return resource_id;
	}
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	 
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getResource_string() {
		return resource_string;
	}
	public void setResource_string(String resource_string) {
		this.resource_string = resource_string;
	}
	public String getResource_desc() {
		return resource_desc;
	}
	public void setResource_desc(String resource_desc) {
		this.resource_desc = resource_desc;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public Integer getIssys() {
		return issys;
	}
	public void setIssys(Integer issys) {
		this.issys = issys;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	

}