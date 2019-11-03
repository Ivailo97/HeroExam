package app.service;

import app.domain.entity.User;
import app.domain.model.service.UserServiceModel;
import app.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean register(UserServiceModel user) {


        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            return false;
        }

        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));


        User entity = modelMapper.map(user, User.class);

        userRepository.save(entity);

        return true;
    }

    @Override
    public boolean login(UserServiceModel user) {

        if (userRepository.findByUsernameAndPassword(user.getUsername(), DigestUtils.sha256Hex(user.getPassword())) == null) {
            return false;
        }

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("id", userRepository.findByUsername(user.getUsername()).getId());

        return true;
    }
}
