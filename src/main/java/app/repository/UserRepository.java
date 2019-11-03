package app.repository;

import app.domain.entity.User;

public interface UserRepository extends GenericRepository<User,String> {

    User findByUsernameAndPassword(String username, String password);

    User findByEmail(String email);

    User findByUsername(String username);
}
