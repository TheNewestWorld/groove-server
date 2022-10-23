//package org.bogus.groove.config.oauth;
//
//import org.bogus.groove.domain.user.UserType;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//
//public class GoogleUserInfo implements OAuth2UserInfo {
//    private final OAuth2AuthenticationToken authentication;
//
//    public GoogleUserInfo(OAuth2AuthenticationToken authentication) {
//        this.authentication = authentication;
//    }
//
//    @Override
//    public UserType getUserType() {
//        return UserType.GOOGLE;
//    }
//
//    @Override
//    public String getEmail() {
//        return authentication.getPrincipal().getAttribute("email");
//    }
//
//    @Override
//    public String getName() {
//        return authentication.getPrincipal().getAttribute("name");
//    }
//
//    @Override
//    public String getProfileImage() {
//        return authentication.getPrincipal().getAttribute("picture");
//    }
//
//    @Override
//    public String getUserId() {
//        return authentication.getPrincipal().getName(); // ID?
//    }
//}
