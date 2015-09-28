package cn.demo.dao;

public abstract interface BaseDAO<T,PK> {
	
	public abstract int insert(T t);
	
	public abstract int selectByObj(T t);
	
	public abstract int selectById(PK pk);
	
	public abstract int deleteById(PK pk);
	
	public abstract int deleteByObj(T t);
	
	public abstract int updateByObj(T t);

}
