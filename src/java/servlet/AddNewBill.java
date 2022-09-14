/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.BillDAO;
import dao.ItemDAO;
import dto.BillDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AddNewBill extends HttpServlet {

    private final String WRONG_PAGE = "BillController";
    private final String UPDATE_PAGE = "updatebill.jsp";

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
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String date = request.getParameter("date");
        String number = request.getParameter("numberDay");
        String money = request.getParameter("money");
        int money1 = Integer.parseInt(money);
        int numberDay = Integer.parseInt(number);
        String interest = request.getParameter("interest");
        float interestRate = Float.parseFloat(interest);

        int idd = Integer.parseInt(id);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = dateFormat.parse(date);

            BillDAO dao = new BillDAO();
            BillDTO bdto = dao.getBillByBillID(idd);
            if (bdto != null) {
                java.sql.Date date2 = java.sql.Date.valueOf(date);
                session.setAttribute("date", date2);
                request.setAttribute("newbill", bdto);
                session.setAttribute("numberDay", numberDay);
                session.setAttribute("money", money1);
                session.setAttribute("interestRate", interestRate);
            }
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            c.add(Calendar.DATE, numberDay);
            date1 = c.getTime();
            String date2 = dateFormat.format(date1);
            java.sql.Date sqlDate = java.sql.Date.valueOf(date2);
            session.setAttribute("dateExpected", sqlDate);
            url = UPDATE_PAGE;

        } catch (SQLException | ParseException e) {
            log("Error at AddNewBill " + e.getMessage());
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
