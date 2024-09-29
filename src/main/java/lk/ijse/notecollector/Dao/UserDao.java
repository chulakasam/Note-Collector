package lk.ijse.notecollector.Dao;

import lk.ijse.notecollector.dto.UserDTO;
import lk.ijse.notecollector.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository//dao layer ekata adala bawa penwanna.
public interface UserDao extends JpaRepository<UserEntity,String> {
    //UserEntity saveUser(UserDTO userDTO);

}
