/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.CustomerDAO;
import dto.CustomerDTO;
import dto.CustomerError;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class UpdateCustomer extends HttpServlet {

    private final String UPDATE_SUCCESS = "ListCustomerController";

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
        response.setContentType("text/html;charset=UTF-8");
        CustomerError cusError = new CustomerError();
        boolean error = false;
        String action = request.getParameter("action");
        String url="customer_update.jsp";
        try {
            if (action.equals("Cập Nhật")) {
                String customerID = request.getParameter("customerID");
                String fullName = request.getParameter("fullName");
                String phone = request.getParameter("phoneNumber");
                String address = request.getParameter("address");
                int phoneNumber = 0;
                if (phone.length() != 10) {
                    cusError.setCustomerSdtError("Số điện thoại phải có 10 chữ số");
                    error = true;
                } else {
                    phoneNumber = Integer.parseInt(phone);
                }
                if(address.length()<5){
                    cusError.setCustomerAddressError("Địa chỉ phải tối thiểu 10 kí tự");
                    error = true;
                }
                if(!error){
                CustomerDTO dto = new CustomerDTO(customerID, fullName, phoneNumber, address);
                CustomerDAO dao = new CustomerDAO();
                boolean updateCus = dao.updateCustomer(dto);
                if (updateCus) {
                    url = UPDATE_SUCCESS;
                }
                }else{
                request.setAttribute("ErrorCus", cusError);
                }
            }
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            if (e.toString().contains("NumberFormat")) {
                cusError.setCustomerSdtError("Số điện thoại không thể chứa kí tự chữ");
                request.setAttribute("ErrorCus", cusError);
            }
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
