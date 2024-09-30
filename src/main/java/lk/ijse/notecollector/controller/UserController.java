package lk.ijse.notecollector.controller;

import lk.ijse.notecollector.dto.UserDTO;
import lk.ijse.notecollector.exceotion.DataPersistException;
import lk.ijse.notecollector.service.UserService;
import lk.ijse.notecollector.service.impl.UserServiceImpl;
import lk.ijse.notecollector.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping( consumes= MediaType.MULTIPART_FORM_DATA_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUser(
           @RequestPart("firstname") String firstName,
           @RequestPart("lastname")String lastName,
           @RequestPart("email") String email,
           @RequestPart("password") String password,
           @RequestPart("profilePic") MultipartFile profilePic
    ){
        //user Id generate
        String userId = AppUtil.generateUserId();

        //profile pic convert to base64
        // String base64profilePicture = AppUtil.profilePicToBase64(profilePic);

        // profilePic ----> Base64
        String base64ProPic = "";
        try {
            byte [] bytesProPic = profilePic.getBytes();
            base64ProPic = AppUtil.profilePicToBase64(bytesProPic);

            //to do : build the object
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setUserId(userId);
            buildUserDTO.setFirstName(firstName);
            buildUserDTO.setLastName(lastName);
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(password);
            buildUserDTO.setProfilePic(base64ProPic);
            userService.saveUser(buildUserDTO);
            //return buildUserDTO;

            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{userId}",produces= MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getSelectedUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable("userId") String userId){
         userService.deleteUser(userId);
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping(value = "/{userId}" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void UpdateUser(
            @RequestPart("firstname") String firstName,
            @RequestPart("lastname") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") MultipartFile profilePic,
            @PathVariable("userId") String userId){

        String picToBase64="";
        try {
            byte[] bytesProPic = profilePic.getBytes();
            picToBase64=AppUtil.profilePicToBase64(bytesProPic);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var buildUserDTO=new UserDTO();
        buildUserDTO.setUserId(userId);
        buildUserDTO.setFirstName(firstName);
        buildUserDTO.setLastName(lastName);
        buildUserDTO.setEmail(email);
        buildUserDTO.setPassword(password);
        buildUserDTO.setProfilePic(picToBase64);

        userService.updateUser(userId,buildUserDTO);


    }
}
