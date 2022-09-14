/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.BillDAO;
import dao.ItemDAO;
import dto.BillDTO;
import dto.BillError;
import dto.ItemDTO;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class CreateBillController extends HttpServlet {

    private final String CHECK_WRONG = "createbill.jsp";
    private final String CHECK_CORRECT = "BillController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws java.text.ParseException
     * @throws java.io.UnsupportedEncodingException
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ParseException, UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = CHECK_WRONG;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        BillError billError = new BillError();
        HttpSession session = request.getSession();
        try {
            boolean checkValidation = true;

            ItemDTO item = (ItemDTO) session.getAttribute("ITEM");
            int itemID = item.getItemID();
            String pawnMoneyStr = request.getParameter("pawnMoney");
            String payMoneyDate = request.getParameter("numberDays");
            int pawnMoney = 0;
            int numberDays = 0;
            String interest = request.getParameter("interestRate");
            float interestRate = 0;

            java.sql.Date billBeginDate = item.getItemGettingDate();
            String staffID = request.getParameter("staffID");

            String payMoney = request.getParameter("returnMoney");
            int returnMoney = Integer.parseInt(payMoney);
            if (payMoney.equals("")) {
                billError.setBillReturnMoneyError("Số tiền trả phải được nhập!");
                checkValidation = false;
            } 
            if (pawnMoneyStr.equals("")) {
                billError.setBillPawnMoneyError("Số tiền cầm phải được nhập!");
                checkValidation = false;
            } else {
                 pawnMoney = Integer.parseInt(pawnMoneyStr);
                if (pawnMoney < 5000) {
                    billError.setBillPawnMoneyError("Số tiền cầm phải từ 5.000 trở lên!");
                    checkValidation = false;
                }
            }
            if (payMoneyDate.equals("")) {
                billError.setBillNumberDaysError("Số ngày cầm phải được nhập!");
                checkValidation = false;
            } else {
                 numberDays = Integer.parseInt(payMoneyDate);
                if (numberDays == 0) {
                    billError.setBillNumberDaysError("Số ngày cầm phải được nhập!");
                    checkValidation = false;
                }
            }
            if(interest.equals("")){
                billError.setBillInterestRateError("Lãi suất chỉ nằm ở khoảng từ 0 đến 4.5%");
                checkValidation = false;
            }else{ 
                 interestRate = Float.parseFloat(interest);
            if (interestRate > 4.5 || interestRate < 0) {
                billError.setBillInterestRateError(" Lãi suất chỉ nằm ở khoảng từ 0 đến 4.5%");
                checkValidation = false;
            }}
            if (checkValidation) {
                BillDAO bdao = new BillDAO();
                int check = bdao.insertNewBill(new BillDTO(itemID, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney));
                if (check > 0) {
                    ItemDAO daoItem = new ItemDAO();
                    Calendar c = Calendar.getInstance();
                    String dateString = dateFormat.format(billBeginDate);
                    java.util.Date utilDate = dateFormat.parse(dateString);
                    c.setTime(utilDate);
                    c.add(Calendar.DATE, numberDays);
                    utilDate = c.getTime();
                    String date2 = dateFormat.format(utilDate);
                    java.sql.Date sqlDate = java.sql.Date.valueOf(date2);
                    if (daoItem.updateItemGettingDate(itemID, sqlDate)) {
                        url = CHECK_CORRECT;
                    }
                }
            } else {
                session.setAttribute("BILL_ERROR", billError);
            }
        } catch (SQLException e) {
            log("Error at CreateBillController " + e.toString());
        } catch (NumberFormatException e) {
            billError.setWrongFormat("Tất cả các ô phải nhập số");
            session.setAttribute("BILL_ERROR", billError);
        } finally {
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (ServletException | IOException ex) {
                log("Error at CreateBillController " + ex.toString());
            }
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
        } catch (ParseException ex) {
            Logger.getLogger(CreateBillController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(CreateBillController.class.getName()).log(Level.SEVERE, null, ex);
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
