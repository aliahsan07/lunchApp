package com.venturedive.rotikhilao.service.user;

import com.venturedive.rotikhilao.response.UserSummary;
import com.venturedive.rotikhilao.security.UserPrincipal;

public interface IUserService {

    UserSummary viewProfile(UserPrincipal currentUser);
}
