package org.esun.database.custom;

import org.esun.database.entity.User;

import java.util.List;

public interface RegisterRepositoryCustom {
    List<User> findRegisterBySelective(User user);
}
