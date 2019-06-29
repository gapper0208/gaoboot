package com.gao.dao;
import java.util.List;
import com.gao.entity.User;
public interface IUserDao {
	void save(User user);
	void delete(Integer id);
	void update(User user);
	List<User> find();
	User find(Integer id);
}
