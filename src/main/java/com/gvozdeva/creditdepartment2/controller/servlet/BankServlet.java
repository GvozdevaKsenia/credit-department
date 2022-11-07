package com.gvozdeva.creditdepartment2.controller.servlet;

import com.gvozdeva.creditdepartment2.controller.dto.BankDto;
import com.gvozdeva.creditdepartment2.exception.DaoException;
import com.gvozdeva.creditdepartment2.util.JspHelper;
import com.gvozdeva.creditdepartment2.model.service.BankService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/banks")
public class BankServlet extends HttpServlet {

    private final BankService bankService = BankService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("banks", bankService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("banks"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var bank = BankDto.builder()
                .name(req.getParameter("bankName"))
                .address(req.getParameter("address"))
                .telephone(req.getParameter("telephone"))
                .email(req.getParameter("email"))
                .build();
        try {
            bankService.create(bank);
            resp.sendRedirect(req.getContextPath() + "/banks");
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
