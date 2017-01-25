/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import pl.com.radio.dao.UserDAO;
import pl.com.radio.entity.UserEntity;
import pl.com.radio.exceptions.ServiceException;
import pl.com.radio.models.UserDTO;

/**
 *
 * @author bartek
 */
@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    public UserDTO createUser(UserDTO userDTO) throws ServiceException {
        checkUserDTO(userDTO);
        UserEntity userEntity = new UserEntity();
        userDTO.reversePopulate(userEntity);
        userDAO.create(userEntity);
        userEntity.setPassword(Utils.sha256(userDTO.getPassword().getBytes()));
        return new UserDTO().populate(userEntity);
    }

    public List<UserDTO> getUsers() {
        List<UserEntity> users = userDAO.findAllUsers();
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(user -> usersDTO.add(new UserDTO().populate(user)));
        users.forEach(user -> userDAO.evict(user.getId(), UserEntity.class));
        return usersDTO;
    }

    public UserDTO deleteUser(Long userId) throws ServiceException {
        UserEntity userEntity = userDAO.find(userId);
        if (userEntity == null) {
            throw new ServiceException(0, "userNotFound");
        }
        userDAO.remove(userEntity);
        return new UserDTO().populate(userEntity);
    }

    private void checkUserDTO(UserDTO userDTO) throws ServiceException {
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            throw new ServiceException(0, "userEmailNotFound");
        }
        if (userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()) {
            throw new ServiceException(0, "userFirstNameNotFound");
        }
        if (userDTO.getLastName() == null || userDTO.getLastName().isEmpty()) {
            throw new ServiceException(0, "userLastNameNotFound");
        }
        if (userDTO.getLogin() == null || userDTO.getLogin().isEmpty()) {
            throw new ServiceException(0, "userLoginNotFound");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new ServiceException(0, "userPasswordNotFound");
        }
    }

}
