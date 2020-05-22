package core.repository;

import core.entity.user.User;

public interface UserRepository {
    public User findByAccount(String account);
}
