/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.CallHistoryDAO;
import dto.CallHistoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
@WebServlet(name = "CallController")
public class CallController extends HttpServlet {

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
        String url="login.jsp";
        try{
        String id=request.getParameter("itemID");
        int itemID=Integer.parseInt(id);
            CallHistoryDAO daoCall=new CallHistoryDAO();
            CallHistoryDTO dto=daoCall.getCallHistory(itemID);
            Calendar c=Calendar.getInstance();
            java.util.Date utilDate=c.getTime();
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
             java.sql.Date d = java.sql.Date.valueOf(df.format(utilDate));
             url="DetailBillOfItem?id="+itemID+"&action=detail";
            if(dto==null){
                daoCall.createCall(itemID, d, 1);
            }else{
                if(dto.getDate1().compareTo(d)==1)
                {daoCall.createCall(itemID, d, 2);}
                else{
                 HttpSession session=request.getSession();
                 session.setAttribute("DATE_WRONG", "Hôm nay bạn đã liên lạc khách hàng này rồi");
                }
            }
        }catch(NumberFormatException | SQLException e){
            log("Error at CallController "+e.toString());
        }finally{
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
