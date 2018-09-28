package com.venturedive.rotikhilao.service.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.venturedive.rotikhilao.model.GoogleCredentials;
import com.venturedive.rotikhilao.repository.GoogleCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class GoogleService implements IGoogleService {

    @Autowired
    private GoogleCredentialsRepository googleCredentialsRepository;

    @Override
    public Boolean checkMembership(Map<String, String> authInfo, String accessToken) throws IOException {

        String idFromToken = null;
        try {
            idFromToken = authInfo.get("sub");
        } catch (Exception e){
            System.out.println("Key not found");
        }

        if (googleCredentialsRepository.findBySub(idFromToken).isPresent()){
            System.out.println("Welcome back mister");
        }else{
            GoogleCredentials googleCredentials = new GoogleCredentials();
            googleCredentials.setEmail(authInfo.get("email"));
            //googleCredentials.setEmailVerified(authInfo.get("email_verified"));
//            if (authInfo.get("email_verified").equals(true)){
//                googleCredentials.setEmailVerified(true);
//            }else{
//                googleCredentials.setEmailVerified(false);
//            }
            //googleCredentials.setExp(Long.valueOf(authInfo.get("exp")));

            Object object = authInfo.get("exp");
            Integer exp = (Integer) object;
            googleCredentials.setExp(exp);

            googleCredentials.setSub(authInfo.get("sub"));

            googleCredentialsRepository.save(googleCredentials);
        }

        // for fetching User information
        GoogleCredential credential  = new GoogleCredential().setAccessToken(accessToken);
        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential).setApplicationName(
                "Oauth2").build();
        Userinfoplus userinfo = oauth2.userinfo().get().execute();
        userinfo.toPrettyString();
        // --------------------------------


        return true;

    }
}
