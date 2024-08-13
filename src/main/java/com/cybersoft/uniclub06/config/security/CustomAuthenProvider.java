package com.cybersoft.uniclub06.config.security;

import com.cybersoft.uniclub06.dto.RoleDTO;
import com.cybersoft.uniclub06.request.AuthenRequest;
import com.cybersoft.uniclub06.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired
    private AuthenService authenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        //Credentials là password
        String password = authentication.getCredentials().toString();
        AuthenRequest authenRequest = new AuthenRequest(username, password);

        List<RoleDTO> roleDTOList = authenService.checkLogin(authenRequest);
        if (!roleDTOList.isEmpty()) {
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();// bữa sau chỉnh stream API
            for (RoleDTO roleDTO : roleDTOList) {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleDTO.getName());
                grantedAuthorityList.add(simpleGrantedAuthority);
            }
            return new UsernamePasswordAuthenticationToken("", "", grantedAuthorityList);
        } else {
            return null;
        }


    }
    //UsernamePasswordAuthenticationToken.class là truền từ thằng cho Authentication của hàm trên
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
