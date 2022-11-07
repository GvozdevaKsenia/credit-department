package com.gvozdeva.creditdepartment2.controller.servlet;

import com.gvozdeva.creditdepartment2.controller.dto.BankDto;
import com.gvozdeva.creditdepartment2.exception.DaoException;
import com.gvozdeva.creditdepartment2.model.service.BankService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteBank")
public class BankDeleteServlet extends HttpServlet {

    private final BankService bankService = BankService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var bank = BankDto.builder()
                .name(req.getParameter("bankDelName"))
                .build();

        try {
            bankService.delete(bank);
            resp.sendRedirect(req.getContextPath() + "/banks");
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
