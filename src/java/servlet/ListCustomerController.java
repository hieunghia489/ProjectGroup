/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.CustomerDAO;
import dao.ItemDAO;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.PawnShopDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ListCustomerController extends HttpServlet {

    private final String WRONG_PAGE = "bill.jsp";
    private final String CUSTOMER_PAGE = "customerlist.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = WRONG_PAGE;
        try {
            if (request.getSession().getAttribute("STAFF") != null) {
                PawnShopDTO pdto = (PawnShopDTO) request.getSession().getAttribute("STORE");
                ItemDAO idao = new ItemDAO();
                ArrayList<ItemDTO> listItem = idao.getAllItemByIsKeep(pdto.getStoreID());
                CustomerDAO dao = new CustomerDAO();
                ArrayList<CustomerDTO> customer = dao.getAllCus();
                ArrayList<CustomerDTO> cusList = new ArrayList<>();
                cusList = dao.getCusInShop(customer, listItem);
                for (CustomerDTO customerDTO : customer) {
                    System.out.println(customerDTO.getAddress());
                }
                request.setAttribute("CUSTOMERLIST", cusList);
                url = CUSTOMER_PAGE;
            }
        } catch (SQLException e) {
            log("Error at ListCustomerController" + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
