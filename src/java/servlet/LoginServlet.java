/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.AdminDAO;
import dao.PawnShopDAO;
import dao.StaffDAO;
import dto.AdminDTO;
import dto.PawnShopDTO;
import dto.StaffDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        try {
            StaffDAO dao = new StaffDAO();
            AdminDAO daoAdmin=new AdminDAO();
            if (dao.checkLogin(userID, password)) {
                StaffDTO staff = dao.viewStaff(userID);
                if (staff != null) {
                    PawnShopDAO daoShop=new PawnShopDAO();
                    PawnShopDTO shop=daoShop.viewPawnShop(staff.getStoreID());
                    if(shop.isStatusID()){
                    session.setAttribute("STAFF", staff);
                    response.sendRedirect("BillController");
                }else{
                        session.setAttribute("errMessage", "Tiệm của tài khoản này đã dừng hoạt động");
                        response.sendRedirect("LoginServlet");
                    }
                }
            }else if(daoAdmin.checkLogin(userID, password)){
                AdminDTO admin=daoAdmin.getAdmin(userID);
                if(admin!=null){
                    session.setAttribute("ADMIN", admin);
                    response.sendRedirect("GetAllStore");
                }
            }else{
                 session.setAttribute("errMessage", "Tài khoản bạn nhập không đúng");
                response.sendRedirect("LoginServlet");
            }
        } catch (IOException | SQLException |ClassNotFoundException e) {
            log("Error at LoginServlet " + e.getMessage());
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
