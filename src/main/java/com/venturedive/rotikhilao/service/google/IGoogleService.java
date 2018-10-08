package com.venturedive.rotikhilao.service.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import java.util.Map;

public interface IGoogleService {

    String saveNewUser(GoogleIdToken.Payload payload);

    Boolean checkUserExistence(GoogleIdToken.Payload payload);

    String saveNewUser(Map<String,Object> map);

    String checkUserExistence(Map<String,Object> map);
}
