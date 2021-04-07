package com.hacker.news.service;

import com.hacker.news.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

  private UserService userService;

  @Autowired
  public SecurityServiceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public String getUsername() {
    String username;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      username = ((UserDetails)principal).getUsername();
    } else {
      username = principal.toString();
    }
    return username;
  }

  @Override
  public User getUser() {
    return userService.getUserByUserName(getUsername());
  }

  @Override
  public String getAuthorities() {
    String authorities;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      authorities = ((UserDetails)principal).getAuthorities().toString();
    } else {
      authorities = principal.toString();
    }
    return authorities;
  }

  @Override
  public boolean isLoggedIn() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return !(auth instanceof AnonymousAuthenticationToken);
  }

}
