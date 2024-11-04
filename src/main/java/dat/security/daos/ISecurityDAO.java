package dat.security.daos;

import dat.entities.User;
import dat.exceptions.ApiException;
import dat.security.exceptions.ValidationException;
import dat.dtos.UserDTO;

public interface ISecurityDAO {
    UserDTO getVerifiedUser(String username, String password) throws ValidationException;
    User createUser(String username, String password) throws ApiException;
    User addRole(UserDTO user, String newRole);
}
