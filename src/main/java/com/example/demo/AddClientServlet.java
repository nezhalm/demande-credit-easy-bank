package com.example.demo;

import Dao.DaoImplementation.ClientImp;
import Dao.DaoImplementation.EmployeImp;
import Entities.Client;
import Entities.Employe;
import Service.ClientService;
import Service.EmployeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/add")
public class AddClientServlet extends HttpServlet {
    private final ClientService clientService;
    private final ClientImp clientImp;
    private final EmployeService employeService;
    private final EmployeImp employeImp;

    public AddClientServlet() {
        this.clientImp = new ClientImp();
        this.clientService = new ClientService(clientImp);
        this.employeImp = new EmployeImp();
        this.employeService = new EmployeService(employeImp);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employe> employes = employeService.AllEmployes();
        System.out.println(employes.get(0).getMatricule());
        request.setAttribute("employes", employes);
        request.getRequestDispatcher("/WEB-INF/JSPs/ClientAdministration/AddClient.jsp").forward(request, response);


    }


}
