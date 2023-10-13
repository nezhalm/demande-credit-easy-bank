package com.example.demo;
import Dao.DaoImplementation.EmployeImp;
import Entities.Client;
import Entities.Employe;
import Service.ClientService;
import Service.EmployeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static java.lang.System.out;

@WebServlet(name = "ClientServlet", urlPatterns = {"/all-clients", "/new-client", "/update-a-client", "/dashboard-client"})
public class ClientServlet extends HttpServlet {
    private final EmployeService employeService;
    private final EmployeImp employeImp;

    public ClientServlet() {
        this.employeImp = new EmployeImp();
        this.employeService = new EmployeService(employeImp);
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            String uri = request.getRequestURI();
            response.setContentType("text/html");

            if ("/all-clients".equals(uri)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientAdministration/GetAllClients.jsp");
                dispatcher.forward(request, response);
            } else if ("/new-client".equals(uri)) {
                List<Employe> employes = employeService.AllEmployes();
                request.setAttribute("employes", employes);
                request.getRequestDispatcher("/WEB-INF/JSPs/ClientAdministration/AddClient.jsp").forward(request, response);
            } else if ("/update-a-client".equals(uri)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientAdministration/UpdateClient.jsp");

                dispatcher.forward(request, response);
            }   else if ("/dashboard-client".equals(uri)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientAdministration/Dashboard.jsp");
            dispatcher.forward(request, response);
        }else {
                out.print("Page Not Found!");
            }
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }

    }
    //   protected void doPost(HttpServletRequest request, HttpServletResponse response)
              //  throws ServletException, IOException {
//            String fullName = request.getParameter("fullName");
//            String username = request.getParameter("username");
//            String email = request.getParameter("email");
//            String phoneNumber = request.getParameter("phoneNumber");
//            LocalDate dateNaissance = LocalDate.parse(request.getParameter("dateNaissance"));
//           Optional<Client> client = clientService.insertClient(fullName, username, email, phoneNumber, dateNaissance);

            // Par exemple, afficher les valeurs dans la console ou les enregistrer dans une base de données
//            System.out.println("Nom: " + fullName);
//            System.out.println("Prénom: " + username);
//            System.out.println("Email: " + email);
//            System.out.println("Téléphone: " + phoneNumber);
//            System.out.println("Date de naissance: " + dateNaissance);

            // Vous pouvez également rediriger l'utilisateur vers une autre page après avoir traité les données
            // response.sendRedirect("votre_page_de_redirection.jsp");
        }

//}