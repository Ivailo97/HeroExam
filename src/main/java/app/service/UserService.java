package app.service;

import app.domain.model.service.UserServiceModel;

public interface UserService {

    boolean register(UserServiceModel user);

    boolean login(UserServiceModel user);
}
