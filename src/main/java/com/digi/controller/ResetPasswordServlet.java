package com.digi.controller;

import com.digi.exceptions.UserNotFoundException;
import com.digi.exceptions.ValidationException;
import com.digi.service.UserService;
import jakarta.mail.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat-password");
        String email = (String) session.getAttribute("email");

        String errorMessage = null;

        try {
            UserService.resetPassword(email, token, password, repeatPassword);
        }
        catch (Exception e) {
            if(e instanceof ValidationException) {
                errorMessage = e.getMessage();
            }
            if(e instanceof UserNotFoundException) {
                errorMessage = e.getMessage();
            }
        }

        if(errorMessage == null) {
            session.removeAttribute("email");
            response.sendRedirect("/home-page.html");
        }else {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/password-reset.jsp").forward(request, response);
        }

    }
}
