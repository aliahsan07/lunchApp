package com.venturedive.rotikhilao.service.user;

import com.venturedive.rotikhilao.response.UserSummary;
import com.venturedive.rotikhilao.security.UserPrincipal;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Override
    public UserSummary viewProfile(UserPrincipal currentUser) {

        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getFirstName(), currentUser.getLastName(),
                                                    currentUser.getUsername());

        return userSummary;
    }
}
