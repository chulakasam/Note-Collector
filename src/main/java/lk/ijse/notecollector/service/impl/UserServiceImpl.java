package lk.ijse.notecollector.service.impl;

import lk.ijse.notecollector.Dao.UserDao;
import lk.ijse.notecollector.dto.UserDTO;
import lk.ijse.notecollector.entity.impl.UserEntity;
import lk.ijse.notecollector.service.UserService;
import lk.ijse.notecollector.util.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service//component annotation eka meta anotate wela tiyenwa..
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapping mapping;
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
         UserEntity SaveUser= userDao.save(mapping.toUserEntity(userDTO));
         return mapping.toUserDTO(SaveUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> all = userDao.findAll();
        return mapping.asUserDTOList(all);
    }

    @Override
    public UserDTO getUser(String userId) {
        UserEntity userEntity = userDao.getReferenceById(userId);
        return mapping.toUserDTO(userEntity);
    }

    @Override
    public boolean updateUser(String userId, UserDTO userDTO) {
        return false;
    }

    @Override
    public void deleteUser(String userId) {
        userDao.deleteById(userId);
    }
}

