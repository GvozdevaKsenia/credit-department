package com.gvozdeva.creditdepartment2.controller.servlet;

import com.gvozdeva.creditdepartment2.util.JspHelper;
import com.gvozdeva.creditdepartment2.model.service.ConsultantService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/consultants")
public class ConsultantServlet extends HttpServlet {

    private final ConsultantService consultantService = ConsultantService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var bankId = Integer.valueOf(req.getParameter("bankId"));
        req.setAttribute("consultants", consultantService.findAllByBankId(bankId));

        req.getRequestDispatcher(JspHelper.getPath("consultants"))
                .forward(req, resp);
    }
}
