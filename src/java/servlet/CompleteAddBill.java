/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.BillDAO;
import dao.CallHistoryDAO;
import dao.ItemDAO;
import dto.StaffDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
public class CompleteAddBill extends HttpServlet {

    private final String RETURN_PAGE = "BillController";
    private final String CHECK_PAGE = "updatebill.jsp";

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
        HttpSession session = request.getSession(false);
        String url = CHECK_PAGE;
        try {
            int itemID = (int) session.getAttribute("itemID");

            int pawnMoney = (int) session.getAttribute("money");

            int numberDay = (int) session.getAttribute("numberDay");

            float interestRate = (float) session.getAttribute("interestRate");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = request.getParameter("itemSendingDate").trim();
            if (date == "") {
                session.setAttribute("ERRORDATE", "Bạn phải nhập ngày đóng tiền");
                return;
            }
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            StaffDTO staff = (StaffDTO) session.getAttribute("STAFF");
            String staffID = staff.getStaffID();
            String payMoney = request.getParameter("txtPayMoney");
            int returnMoney = 0;
            if (payMoney != "") {
                returnMoney = Integer.parseInt(payMoney);
            }
            String strDebt = request.getParameter("txtDebt");
            int debt = Integer.parseInt(strDebt);
            boolean error = false;
            int payDate = Integer.parseInt(request.getParameter("txtPayDate"));
            java.sql.Date expectedDate = (java.sql.Date) session.getAttribute("dateExpected");
            java.sql.Date oldDate = (java.sql.Date) session.getAttribute("date");

            if (sqlDate.compareTo(expectedDate) == 1 || sqlDate.compareTo(oldDate) == -1) {
                error = true;
                session.setAttribute("ERRORDATE", "Ngày đóng tiền phải nằm trong khoảng ngày nhập!!!");
            }
            if (returnMoney > debt) {
                error = true;
                session.setAttribute("ERRORMONEY", "Số tiền đóng phải ít hơn số tiền nợ!!");
            }else if(returnMoney<0){
                error=true;
                session.setAttribute("ERRORMONEY", "Số tiền không thể < 0");
            }

            ItemDAO idao = new ItemDAO();
            CallHistoryDAO daoCall = new CallHistoryDAO();
            if ((debt - returnMoney) > 0) {
                if (!error) {
                    session.setAttribute("ERRORDATE", "");
                    session.setAttribute("ERRORMONEY", "");
                    BillDAO bdao = new BillDAO();
                    Calendar c = Calendar.getInstance();
                    java.util.Date beginDate = dateFormat.parse(date);
                    c.setTime(beginDate);
                    c.add(Calendar.DATE, payDate);
                    beginDate = c.getTime();
                    java.sql.Date sqlPayDate = java.sql.Date.valueOf(dateFormat.format(beginDate));
                    boolean check = bdao.insertBill(itemID, pawnMoney, numberDay, interestRate, sqlPayDate, staffID, returnMoney);
                    if (check) {
                        daoCall.deleteCall(itemID);
                        url = RETURN_PAGE;
                    }
                } else {
                    url = CHECK_PAGE;
                }
            } else if ((debt - returnMoney) == 0) {
                boolean statusID = false;
                session.setAttribute("CheckRemainMonney", "Khách hàng đã hoàn tất thanh toán");
                boolean check = idao.updateItemStatus(itemID, statusID);
                if (!error) {
                    session.setAttribute("ERRORDATE", "");
                    session.setAttribute("ERRORMONEY", "");
                    session.setAttribute("UPDATE", "");
                    BillDAO bdao = new BillDAO();
                    java.util.Date beginDate = dateFormat.parse(date);
                    java.sql.Date sqlPayDate = java.sql.Date.valueOf(dateFormat.format(beginDate));
                    boolean check1 = bdao.insertBill(itemID, pawnMoney, 0, interestRate, sqlDate, staffID, returnMoney);
                    if (check) {
                        boolean checkUpdate = idao.updateItemGettingDate(itemID, sqlPayDate);
                        int billID = bdao.getLastBillID();
                        boolean checkUpdate2 = bdao.updateNumberDay(billID, 0);
                        
                        if (checkUpdate && checkUpdate2) {
                            url = RETURN_PAGE;
                        }
                    }
                } else {
                    url = CHECK_PAGE;
                }
            }
        } catch (NumberFormatException | SQLException | ParseException | ClassNotFoundException e) {
            log("Error at CreateBill " + e.toString());
        } finally {
            response.sendRedirect(url);
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
