package com.digi.service;

import com.digi.email.EmailSender;
import com.digi.enums.Status;
import com.digi.exceptions.UserAlreadyExistsException;
import com.digi.exceptions.UserNotFoundException;
import com.digi.exceptions.ValidationException;
import com.digi.helper.UserHelper;
import com.digi.model.User;
import com.digi.repository.impl.UserRepositoryJDBC;
import com.digi.util.TokenGenerator;

public class UserService {
    public static void saveUser(String name, String surname, String year, String email, String password) {
        UserRepositoryJDBC userRepositoryJDBC = new UserRepositoryJDBC();
        String vCode = TokenGenerator.generateVerificationCode();
        try {
            int newYear = Integer.parseInt(year);
            User user = new User(0, name, surname, newYear, email, password, Status.INACTIVE, vCode, null);
            UserHelper.userValidation(user);
            UserHelper.duplicateCheck(email);
            user.setPassword(TokenGenerator.passwordEncode(password));
            userRepositoryJDBC.saveUser(user);
            EmailSender.sendEmail(email, "Verification code", "Your verification code: " + vCode);
        }
        catch (Exception e) {
            if(e instanceof NumberFormatException) {
                throw new ValidationException("Number format is incorrect");
            }
            if(e instanceof ValidationException) {
                throw e;
            }
            if(e instanceof UserAlreadyExistsException) {
                throw e;
            }
        }

    }

    public static void verification(String email, String verifyCode) {
        UserRepositoryJDBC userRepositoryJDBC = new UserRepositoryJDBC();
        User user = userRepositoryJDBC.getUserByEmail(email);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }

        if(verifyCode != null && !user.getVerifyCode().equals(verifyCode)) {
            throw new ValidationException("Wrong verification code");
        }
        else if(verifyCode == null) {
            throw new ValidationException("You are verified");
        }

        userRepositoryJDBC.verification(email);
    }

    public static void userAvailability(String email) {
        UserRepositoryJDBC userRepositoryJDBC = new UserRepositoryJDBC();
        User user = userRepositoryJDBC.getUserByEmail(email);
        if(user == null) {
            throw new UserNotFoundException("User with specified email does not exist");
        }
        String token = TokenGenerator.resetTokenGenerator();
        userRepositoryJDBC.saveToken(email, token);
        EmailSender.sendEmail(email, "Token for password restoration", "Your token is: " + token);
    }

    public static void resetPassword(String email, String token, String password, String repeatPassword) {
        UserRepositoryJDBC userRepositoryJDBC = new UserRepositoryJDBC();
        User user = userRepositoryJDBC.getUserByEmail(email);
        if(user == null) {
            throw new UserNotFoundException("User does not exist");
        }
        if(!token.equals(user.getResetToken())) {
            throw new ValidationException("Incorrect token");
        }
        if(!UserHelper.isValidPassword(password)) {
            throw new ValidationException("Password must have at least 8 characters, 1 uppercase and 1 digit");
        }
        if(!password.equals(repeatPassword)) {
            throw new ValidationException("password is not equal to repeat password");
        }

        String encodedPassword = TokenGenerator.passwordEncode(password);
        userRepositoryJDBC.resetPassword(email, encodedPassword);

    }

}
