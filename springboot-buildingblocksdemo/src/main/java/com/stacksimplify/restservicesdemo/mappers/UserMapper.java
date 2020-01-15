package com.stacksimplify.restservicesdemo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.stacksimplify.restservicesdemo.dto.UserMsDTO;
import com.stacksimplify.restservicesdemo.entity.User;

@Mapper(componentModel="Spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	//User to UserMsDTO
	@Mappings({
		@Mapping(source="id", target="id"),
		@Mapping(source="email", target="email")
	})
	UserMsDTO userToUserMsDTO(User user);
	
	//List<User> to List<UserMsDTO)
	
	List<UserMsDTO> usersToUserMsDTOs(List<User> users);
	
}
