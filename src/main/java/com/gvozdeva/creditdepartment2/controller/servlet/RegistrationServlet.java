package com.gvozdeva.creditdepartment2.controller.servlet;

import com.gvozdeva.creditdepartment2.controller.dto.CreateClientDto;
import com.gvozdeva.creditdepartment2.exception.ValidationException;
import com.gvozdeva.creditdepartment2.model.service.ClientService;
import com.gvozdeva.creditdepartment2.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var clientDto = CreateClientDto.builder()
                .firstName(req.getParameter("firstName"))
                .surname(req.getParameter("surname"))
                .birthDate(req.getParameter("birthday"))
                .telephone(req.getParameter("telephone"))
                .passportNo(req.getParameter("passport"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        try {
            clientService.create(clientDto);
            resp.sendRedirect(req.getContextPath() + "/login");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
