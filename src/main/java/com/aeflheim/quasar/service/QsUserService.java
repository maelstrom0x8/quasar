package com.aeflheim.quasar.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aeflheim.quasar.GenerateCodeUtil;
import com.aeflheim.quasar.model.Otp;
import com.aeflheim.quasar.model.User;
import com.aeflheim.quasar.repository.OtpRepository;
import com.aeflheim.quasar.repository.UserRepository;

@Service
@Transactional
public class QsUserService {

    private Logger logger = LoggerFactory.getLogger(QsUserService.class.getName());
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(User user) {
        Optional<User> optUser = userRepository.findByUsername(user.getUsername());

        if (optUser.isPresent()) {
            User u0 = optUser.get();
            if (passwordEncoder.matches(user.getPassword(), u0.getPassword())) {
                renewOtp(u0);
            } else {
                throw new BadCredentialsException("Bad Credentials");
            }
        } else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    public boolean check(Otp otpToValidate) {

        Optional<Otp> userOtp = otpRepository.findByUsername(otpToValidate.getUsername());

        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            if (otpToValidate.getCode().equals(otp.getCode())) {
                return true;
            }
        }

        return false;
    }

    private void renewOtp(User user) {
        String code = GenerateCodeUtil.generateCode();
        // Send sms
        logger.info(String.format("Generating OTP code %s for user %s", code, user.getUsername()));
        Optional<Otp> userOtp = otpRepository.findByUsername(user.getUsername());
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            otp.setCode(code);
        } else {
            Otp otp = new Otp();
            otp.setUsername(user.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }
}
