package com.venturedive.rotikhilao.service.google;

import java.io.IOException;
import java.util.Map;

public interface IGoogleService {

    Boolean checkMembership(Map<String, String> authInfo,  String accessToken) throws IOException;
}
