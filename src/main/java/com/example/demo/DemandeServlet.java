
package com.example.demo;
import Dao.DaoImplementation.ClientImp;
import Dao.DaoImplementation.DemandeImp;
import Dao.DaoImplementation.EmployeImp;
import Entities.Client;
import Entities.Employe;
import Enum.*;
import Service.ClientService;
import Service.DemandeService;
import Service.EmployeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static Dao.DaoImplementation.EmployeImp.genererCodeUnique;

@WebServlet(urlPatterns = {"/updateStatus"})

public class DemandeServlet extends HttpServlet {
    ClientService clientService;
    EmployeService employeService;
    DemandeService demandeService;
    @Override
    public void init() throws ServletException {
        clientService= new ClientService(new ClientImp());
        employeService= new EmployeService(new EmployeImp());
        demandeService = new DemandeService(new DemandeImp());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/updateStatus":
                    updateStatus(request, response);
                    break;

                default:
                    System.out.println("choice not found");
                    break;
            }
        } catch (ServletException ex) {
            throw new ServletException(ex);
        }
    }
    protected void updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
        String number = request.getParameter("number");
        StatusDemande statusDemande;
        if (status != null && !status.isEmpty()) {
            switch (status.toLowerCase()) {
                case "Active":
                    statusDemande = StatusDemande.Active;
                    break;
                case "Late":
                    statusDemande = StatusDemande.Late;
                    break;
                default:
                    statusDemande = StatusDemande.Active;
                    break;
            }
        } else {

            statusDemande = StatusDemande.Active;
        }
       request.setAttribute("employes", demandeService.UpdateStatus(statusDemande,number));
        System.out.println("status updated");
       // request.getRequestDispatcher("/JSPs/ClientAdministration/AddClient.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

