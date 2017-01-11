/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import pl.com.radio.dao.UserDAO;
import pl.com.radio.entity.UserEntity;
import pl.com.radio.models.UserDTO;

/**
 *
 * @author bartek
 */
@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    public UserDTO createUser(UserDTO userDTO) {
        checkUserDTO(userDTO);
        UserEntity userEntity = new UserEntity();
        userDTO.reversePopulate(userEntity);
        userDAO.create(userEntity);
        return new UserDTO().populate(userEntity);
    }

    public List<UserDTO> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public UserDTO deleteUser(Long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void checkUserDTO(UserDTO userDTO) {
        //TODO: sprawdzanie poprawności wysłanych pol aktualnie łyka wszystko jak młody pelikan
    }

}
