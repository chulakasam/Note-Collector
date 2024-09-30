package lk.ijse.notecollector.service;

import lk.ijse.notecollector.dto.NoteDTO;
import lk.ijse.notecollector.dto.UserDTO;
import lk.ijse.notecollector.dto.UserStatus;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserStatus getUser(String userId);
    boolean updateUser(String userId,UserDTO userDTO);
    void deleteUser(String userId);
}
