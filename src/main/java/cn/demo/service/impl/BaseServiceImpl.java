package cn.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.demo.dao.PageDAO;
import cn.demo.dao.UserDAO;
import cn.demo.service.BaseService;

public class BaseServiceImpl implements BaseService<Object,Object> {

	@Autowired
	public PageDAO pageDAO;
	
	@Autowired
	public UserDAO userDAO;

	@Override
	public int insert(Object t) {
		return 0;
	}

	@Override
	public int selectByObj(Object t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectById(Object pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Object pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByObj(Object t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByObj(Object t) {
		// TODO Auto-generated method stub
		return 0;
	}
 

}
