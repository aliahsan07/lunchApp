package com.venturedive.rotikhilao.configuration;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.venturedive.rotikhilao.pojo.TokenResponse;
import com.venturedive.rotikhilao.service.google.GoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class GoogleTokenVerifier {

    private static final JacksonFactory jsonFactory = new JacksonFactory();

    @Autowired
    private GoogleService googleService;

    private StringBuffer GetData(String idToken) throws IOException {
        URL url = new URL("https://www.googleapis.com/oauth2/v3/tokeninfo");

        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        StringBuilder stringBuilder = new StringBuilder();
        String ID_TOKEN = "id_token";
        stringBuilder.append(ID_TOKEN);
        stringBuilder.append("=");
        stringBuilder.append(idToken);

        String urlParameters = stringBuilder.toString().replace("\"", "");
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(urlParameters);
        out.flush();
        out.close();

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        return content;
    }

    private GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jsonFactory)
            // Specify the CLIENT_ID of the app that accesses the backend:
            .setAudience(Collections.singletonList("273103486448-o9e5ur8qtagi8d34olftmrqcfniu3mvm.apps.googleusercontent.com"))
            // Or, if multiple clients access the backend:
            //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
            .build();

    public TokenResponse verifyIdToken(String idTokenString) throws Exception {

        GoogleIdToken idToken = verifier.verify(idTokenString);
        StringBuffer dataFromGoogleAPI = GetData(idTokenString);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = dataFromGoogleAPI.toString();

            Map<String, Object> map = new HashMap<>();

            map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});

            System.out.println(map);

            String userToken = googleService.checkUserExistence(map);

            return generateToken(userToken);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            throw new Exception("Error in generating token");
        }

        return generateToken("UNAUTHORIZED");

    }


    private TokenResponse generateToken(String content){

        if (content.equals("UNAUTHORIZED")){
            return new TokenResponse("false", "-");
        }

        return new TokenResponse("true", content);
    }


}
