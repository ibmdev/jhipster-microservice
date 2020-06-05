package fr.sma.bw.repositories.mybatis;

import fr.sma.bw.entities.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.mapping.callback.EntityCallback;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@FunctionalInterface
public interface UserDao extends EntityCallback<User> {

    @Select("SELECT * from User")
    List<User> getListUsers();
}
