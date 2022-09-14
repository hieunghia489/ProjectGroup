/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.BillDAO;
import dao.PawnShopDAO;
import dao.StaffDAO;
import dto.BillDTO;
import dto.StaffDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
public class BillController extends HttpServlet {
    private final String HOME_PAGE = "bill.jsp";

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
            throws ServletException, IOException, ParseException {
        String url = HOME_PAGE;
        Map<Integer,Integer> item_bill =   new HashMap<>();
        try {
            StaffDTO staff = (StaffDTO) request.getSession().getAttribute("STAFF");
            if (staff != null) {
                BillDAO daoBill = new BillDAO();
                PawnShopDAO daoShop = new PawnShopDAO();
                StaffDAO daoStaff = new StaffDAO();
                ArrayList<BillDTO> bil = null;
                ArrayList<StaffDTO> listStaff = daoStaff.getAllStaffInStore(staff.getStoreID());
                if (listStaff != null) {
                    for (StaffDTO staffDTO : listStaff) {
                        if (bil == null) {
                            bil = new ArrayList<>();
                        }
                        ArrayList<BillDTO> smallBill = daoBill.viewBillByStaff(staffDTO.getStaffID());
                        if(smallBill!=null){
                        for (BillDTO billDTO : smallBill) {
                            bil.add(billDTO);
                        }
                        }
                    }
                   
                    for (BillDTO dto : bil) {
                        item_bill.put(dto.getItemID(),dto.getBillID());
                    }
                }
                HttpSession session = request.getSession();
                session.setAttribute("bill", bil);
                session.setAttribute("ITEM_BILL", item_bill);
                session.setAttribute("STORE", daoShop.viewPawnShop(staff.getStoreID()));
                session.setAttribute("storeID", staff.getStoreID()); 
                ArrayList<BillDTO> lastBill= new ArrayList<>();
                for(Integer i : item_bill.values()){
                   lastBill.add(daoBill.getBillByBillID(i));
                }
                ArrayList<BillDTO> billClose=daoBill.billClose(lastBill);
                if(!billClose.isEmpty()){
                    session.setAttribute("BILL_CLOSE", billClose);
                }
                
                ArrayList<BillDTO> billOver=daoBill.billOverTime(lastBill);
                 if(!billOver.isEmpty()){
                    session.setAttribute("BILL_OVER", billOver);
                }
                url = HOME_PAGE;
            }
        } catch (ClassNotFoundException | SQLException |ParseException e) {
            log("Error at BillController " + e.getMessage());
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
        } catch (ParseException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
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
