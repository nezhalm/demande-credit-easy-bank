
package com.example.demo;
import Dao.DaoImplementation.ClientImp;
import Dao.DaoImplementation.DemandeImp;
import Dao.DaoImplementation.EmployeImp;
import Entities.Agence;
import Entities.Client;
import Entities.Demande;
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
import java.time.LocalDateTime;
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
                case "/add":
                    addDemande(request, response);
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
        // request.setAttribute("demandes", demandeService.AllDemandes());
        System.out.println("date");
       // request.getRequestDispatcher("/JSPs/DemandeAdministration/ListDemandes.jsp").forward(request, response);
    }

    private void searchByStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("label");
        request.setAttribute("demandes", demandeService.searchDemandesByLabel(status));
        System.out.println(demandeService.searchDemandesByLabel(status));
        //   request.getRequestDispatcher("/JSPs/DemandeAdministration/ListDemandes.jsp").forward(request, response);
    }

    private void addDemande(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Agence agence = new Agence();
        Client client = new Client();
        Employe employe = new Employe();
        
        String number = request.getParameter("number");
//        double monthlyPayment = Double.parseDouble(request.getParameter("monthly-payment"));
        int price = Integer.parseInt(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        String remark = request.getParameter("remake");
        LocalDateTime date = LocalDateTime.now();
        StatusDemande status = StatusDemande.Pending;
        employe.setMatricule(request.getParameter("employee-code"));
        client.setCode(request.getParameter("client-code"));
        agence.setCode(request.getParameter("agence-code"));

        Demande demande = new Demande(
                number,
                null,
                price,
                duration,
                remark,
                date,
                status,
                employe,
                agence,
                client
        );

        Optional<Demande> createdDemande = demandeService.ajouterDemande(demande);

        if (createdDemande.isPresent()) {
            String[] message = {"success", "The credit request has been created successfully."};
            request.setAttribute("message", message);
        } else {
            String[] message = {"error", "Error occurred during submitting the credit request."};
            request.setAttribute("message", message);
        }

        displayAllDemandes(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

