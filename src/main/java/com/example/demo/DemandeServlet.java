
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

@WebServlet(urlPatterns = {"/updateStatus","/displayAllDemandes","/searchByStatus","/searchByDate"})

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
                case "/displayAllDemandes":
                    displayAllDemandes(request, response);
                    break;
                case "/searchByDate":
                    searchByDate(request, response);
                    break;
                case "/searchByStatus":
                    searchByStatus(request, response);
                    break;
                default:
                    System.out.println("choice not found");
                    break;
            }
        } catch (ServletException ex) {
            throw new ServletException(ex);
        }
    }
    private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
        String number = request.getParameter("number");
        StatusDemande statusDemande = StatusDemande.valueOf(status);
        request.setAttribute("employes", demandeService.UpdateStatus(statusDemande,number));
        displayAllDemandes(request,response);
    }
    private void displayAllDemandes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("demandes", demandeService.AllDemandes());
        System.out.println(demandeService.AllDemandes());
        request.getRequestDispatcher("/JSPs/DemandeAdministration/ListDemandes.jsp").forward(request, response);
    }
    private void searchByDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        request.setAttribute("demandes", demandeService.searchDemandesByDate(date));
         request.getRequestDispatcher("/JSPs/DemandeAdministration/ListDemandes.jsp").forward(request, response);

    }
    private void searchByStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StatusDemande statusDemande = StatusDemande.valueOf(request.getParameter("status"));
        request.setAttribute("demandes", demandeService.searchDemandesByLabel(statusDemande));
        request.getRequestDispatcher("/JSPs/DemandeAdministration/ListDemandes.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

