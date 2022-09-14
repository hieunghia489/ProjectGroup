/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.KeyDAO;
import dto.AdminDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "CreateKeyController", urlPatterns = {"/CreateKeyController"})
public class CreateKeyController extends HttpServlet {

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
            request.setCharacterEncoding("utf-8");
        String action=request.getParameter("action");
        KeyDAO dao=new KeyDAO();
        HttpSession session=request.getSession();
        try{
            if(action.equals("Tạo mã")){
                String key="";
                do { key=dao.randomAlphaNumeric(8);
                }while(!dao.checkKey(action));
                session.setAttribute("KEY", key);
            }else if(action.equals("Sử dụng")){
                String k =(String)session.getAttribute("KEY");
                if(k!=null){
                   AdminDTO admin=(AdminDTO) session.getAttribute("ADMIN");
                   if(admin!=null){
                       dao.addKey(k, admin.getAdminID());
                   }
                }
            }
        }catch(SQLException e){
            log("Error at CreateKeyControlelr "+e.toString());
        }finally{
            response.sendRedirect("KeyController");
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
