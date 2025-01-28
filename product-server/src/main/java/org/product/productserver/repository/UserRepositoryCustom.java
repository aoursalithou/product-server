package org.product.productserver.repository;

import org.product.productserver.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {

    Page<UserDto> getUsersList(Pageable pageable, UserDto dto);

    List<UserDto> fetchFirstNames();

    List<UserDto> fetchLastNames();

    List<UserDto> fetchUsernames();

    List<UserDto> fetchEmails();

    boolean updateUser(UserDto userDto);

    UserDto getUserById(long id);

    boolean deleteUser(Long id);
}
