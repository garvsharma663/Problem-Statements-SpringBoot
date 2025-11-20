package otpApp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otpApp.entity.User;
import otpApp.exception.UserNotFound;
import otpApp.repo.UserRepo;

import java.security.SecureRandom;

@RequiredArgsConstructor
@Service
public class OTPService {
    // There are many ways to generate OTP such as using numbers or AAlphanumerics.
    public final UserRepo userRepo;
    // 1.) Using only Numbers

    private static final SecureRandom random = new SecureRandom();
    // Using SecureRandom as its thread-safe and cannot be predicted easily
    // and making it final and static, so it is constant and can be accessed globally in the classes.

    // Method for generation
    public String generateNumberOTP(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // 2.) Alphanumeric (Uppercases only)
    private static final String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public String generateAlphanumeric(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(alphanumeric.charAt(random.nextInt(alphanumeric.length())));
        }
        return sb.toString();
    }

    // OTP for SignUp
    public String generateSignUpOTP(String email, String password) {

        if(userRepo.existsUserByEmail(email)){
            return "USER EXISTS";
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setVerified(false); // False as we must verify the OTP.
        String otp = generateNumberOTP(6);
        user.setOtp(otp);

        userRepo.save(user);
        return otp;
    }
    // OTP for ForgotPassword
    public String generateForgotPasswordOTP(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(UserNotFound::new);
        String otp = generateNumberOTP(6);
        user.setOtp(otp);
        userRepo.save(user);
        return otp;

    }
    // Method for Verification of OTP
    public boolean verifyOTP(String otp, String email) {
        // Check If user exists by email
        User user = userRepo.findByEmail(email)
                .orElseThrow(UserNotFound::new);
        if(user.getOtp()==null || !user.getOtp().equals(otp)){
            return false;
        }
        user.setVerified(true);
        user.setOtp(null); // OTP is temporary, so we assign it to null
        userRepo.save(user);
        return true;


    }





}
