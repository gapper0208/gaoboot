package com.gao.dao;
import java.util.List;
import com.gao.entity.Dept;
public interface IDeptDao {
	void save(Dept dept);
	void delete(Integer did);
	void update(Dept dept);
	List<Dept> find();
	Dept find(Integer did);
}
