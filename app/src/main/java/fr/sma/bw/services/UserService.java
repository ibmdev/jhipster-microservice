package fr.sma.bw.services;

import fr.sma.svc.bw.service.model.ListUserResponse;
import fr.sma.bw.entities.User;
import fr.sma.bw.mappers.ObjectMapperUtils;
import fr.sma.bw.repositories.mybatis.UserDao;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserService {
    private final UserDao userDao;
    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }
    public ListUserResponse retrieveUsers() {
        final List<User> reponseDTO  = this.userDao.getListUsers();
        final List<fr.sma.svc.bw.service.model.User> listUsers  =
        ObjectMapperUtils.mapAll(reponseDTO, fr.sma.svc.bw.service.model.User.class);
        final ListUserResponse out = new ListUserResponse();
        out.addAll(listUsers);
        return out;
    }
}
