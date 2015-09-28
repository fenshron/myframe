package cn.demo.service;

public abstract interface BaseService<T,ID> {

	public abstract int insert(T t);
	
	public abstract int selectByObj(T t);
	
	public abstract int selectById(ID pk);
	
	public abstract int deleteById(ID pk);
	
	public abstract int deleteByObj(T t);
	
	public abstract int updateByObj(T t);
}
