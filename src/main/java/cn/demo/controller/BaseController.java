package cn.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.demo.util.AlterModel;
import cn.demo.util.GridModel;

public class BaseController{

	// datagrid的属性
		private int page;
		private int rows;
		private String sort;
		private String order;
			
		protected GridModel gridModel =new GridModel();

		protected Map<String,String> param =new HashMap<String,String>();
		
		public String writeJson(Object object) {
			 return JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			gridModel.setPage(page);
			this.page = page;
		}

		public int getRows() {
			return rows;
		}

		public void setRows(int rows) {
			gridModel.setRows(rows);
			this.rows = rows;
		}

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			gridModel.setSort(sort);
			this.sort = sort;
		}

		public String getOrder() {
			return order;
		}

		public void setOrder(String order) {		
			gridModel.setOrder(order);
			this.order = order;
		}

		public Map<String, String> getParam() {
			return param;
		}

		public void setParam(Map<String, String> param) {
			this.param = param;
		}

		
		public void myresponse(HttpServletResponse response,Boolean flag, String result ){
			AlterModel  alterModel = new AlterModel();
			alterModel.setFlag(flag);
			alterModel.setMsg(result);
			try {
				response.getWriter().write(writeJson(alterModel));
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
