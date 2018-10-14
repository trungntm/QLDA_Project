package vn.hcmute.projectmanagement.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.hcmute.projectmanagement.api.v1.dto.PersonDto;
import vn.hcmute.projectmanagement.api.v1.dto.UserDto;
import vn.hcmute.projectmanagement.entity.Person;
import vn.hcmute.projectmanagement.entity.User;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDto personToPersonDto(Person person);
}
