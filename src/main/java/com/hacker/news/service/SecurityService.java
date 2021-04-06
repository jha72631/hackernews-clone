package com.hacker.news.service;

import com.hacker.news.model.User;

public interface SecurityService {

  String getUsername();

  String getAuthorities();

  User getUser();

  boolean isLoggedIn();

}
