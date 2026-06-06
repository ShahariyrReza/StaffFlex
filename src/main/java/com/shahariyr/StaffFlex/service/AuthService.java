package com.shahariyr.StaffFlex.service;

import com.shahariyr.StaffFlex.dto.LoginDto;
import com.shahariyr.StaffFlex.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
