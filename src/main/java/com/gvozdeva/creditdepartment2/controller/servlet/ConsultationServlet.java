package com.gvozdeva.creditdepartment2.controller.servlet;

import com.gvozdeva.creditdepartment2.controller.dto.OrderConsultationDto;
import com.gvozdeva.creditdepartment2.model.service.ConsultationService;
import com.gvozdeva.creditdepartment2.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/main", name = "DefaultServlet")
public class ConsultationServlet extends HttpServlet {

    private final ConsultationService consultationService = ConsultationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("index"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var consultationDto = OrderConsultationDto.builder()
                .fio(req.getParameter("fio"))
                .topic(req.getParameter("topic"))
                .email(req.getParameter("email"))
                .telephone(req.getParameter("telephone"))
                .message(req.getParameter("message"))
                .build();
        consultationService.save(consultationDto);
        resp.sendRedirect(req.getContextPath() + "/success");
    }
}
