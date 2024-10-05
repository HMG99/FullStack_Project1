package com.digi.controller;

import com.digi.exceptions.UserNotFoundException;
import com.digi.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TokenSendServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        HttpSession session = request.getSession();

        String errorMessage = null;

        try {
            UserService.userAvailability(email);
        }
        catch (Exception e) {
            if(e instanceof UserNotFoundException) {
                errorMessage = e.getMessage();
            }
        }

        if(errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/send-by-email.jsp").forward(request, response);
        }else {
            session.setAttribute("email", email);
            response.sendRedirect("/password-reset.jsp");
        }

    }
}
