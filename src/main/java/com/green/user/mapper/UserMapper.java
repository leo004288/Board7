package com.green.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.green.user.dto.UserDto;

@Mapper
public interface UserMapper {

	void insertUser(UserDto userdto);

	List<UserDto> getUserList();

	void deleteUser(UserDto userdto);

	UserDto getUser(UserDto userdto);

	void updateUser(UserDto userdto, String oldpwd);

	UserDto getIdDupCheck2(UserDto userdto);

	void updateUser2(Map<String, Object> map);

	UserDto getLogin(UserDto userdto);



}
