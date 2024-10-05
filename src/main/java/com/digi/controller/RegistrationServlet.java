package com.digi.controller;

import com.digi.exceptions.UserAlreadyExistsException;
import com.digi.exceptions.ValidationException;
import com.digi.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String year = request.getParameter("year");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        String errorMessage = null;

        try {
            UserService.saveUser(name, surname, year, email, password);
        } catch (Exception e) {
            if (e instanceof ValidationException) {
                errorMessage = e.getMessage();
            }
            if (e instanceof UserAlreadyExistsException) {
                errorMessage = e.getMessage();
            }
        }


        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("registration-page.jsp").forward(request, response);
        } else {
            session.setAttribute("email", email);
            response.sendRedirect("/verification-page.jsp");
        }


    }
}
