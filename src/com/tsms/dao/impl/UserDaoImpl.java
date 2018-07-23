package com.tsms.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.tsms.dao.UserDao;
import com.tsms.entity.User;
import com.tsms.util.DBUtil;

/**
 *
 * @Author: zhoujunpeng
 * @Date: 2018年7月10日 下午2:36:19
 */
public class UserDaoImpl implements UserDao {

	private DBUtil db = DBUtil.getInstance();
	
	@Override
	public User login(String userName) {
		List<Map<String, String>> list = db.query("select * from tb_user where username=?", userName);
		User user = null;
		if (list.size()>0) {
			user = new User();
			try {
				BeanUtils.populate(user, list.get(0));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return user;
	}

}
