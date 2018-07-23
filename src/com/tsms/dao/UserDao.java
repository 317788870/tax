package com.tsms.dao;

import com.tsms.entity.User;

/**
 *
 * @Author: zhoujunpeng
 * @Date: 2018年7月10日 下午2:34:37
 */
public interface UserDao {

	User login(String userName);
}
