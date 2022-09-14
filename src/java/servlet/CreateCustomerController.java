/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dto.CustomerError;
import dao.CustomerDAO;
import dto.CustomerDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class CreateCustomerController extends HttpServlet {

    private static final String SUCCESS = "createitem.jsp";
    private static final String ERROR = "createcustomer.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        CustomerError cusError = new CustomerError();
        String customerID = request.getParameter("customerID");
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        
        try {

            boolean checkValidation = true;
            if (customerID.length() != 12) {
                cusError.setCustomerIDError("Chứng Minh Nhân Dân phải có 12 số!");
                checkValidation = false;
            }
            if (fullName.length() > 20 || fullName.length() < 5) {
                cusError.setCustomerNameError("Tên khách hàng phải nằm khoảng từ 5 đến 20 kí tự!");
                checkValidation = false;
            }
            if (address.length() < 10 || address.length() > 100) {
                cusError.setCustomerAddressError("Địa chỉ phải nằm khoảng từ 10 đến 100 kí tự!");
                checkValidation = false;
            }
            if (phoneNumber.length() != 10) {
                cusError.setCustomerSdtError("Số điện thoại phải có 10 số!");
                checkValidation = false;
            }
            if (checkValidation) {
                CustomerDAO dao = new CustomerDAO();
                CustomerDTO cus = new CustomerDTO(customerID, fullName, new Integer(phoneNumber), address);
                boolean creates = dao.createCustomer(cus);
                HttpSession session = request.getSession();
                if (creates) {
                    session.setAttribute("CUSTOMER", cus);
                }
                url = SUCCESS;
            } else {
                request.setAttribute("CUS_ERROR", cusError);
            }
        } catch (NumberFormatException e) {
            cusError.setMessageError("Mã khách hàng và số điện thoại phải nhập số");
        } catch (SQLException e) {
            String msg = e.getMessage();
            log("CreateController SQL " + msg);
            if (msg.contains("duplicate")) {
                cusError.setMessageError(customerID + " đã tồn tại!");
                request.setAttribute("CUS_ERROR", cusError);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
