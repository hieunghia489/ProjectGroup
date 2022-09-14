/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.BillDAO;
import dao.CallHistoryDAO;
import dao.CustomerDAO;
import dao.ItemDAO;
import dto.BillDTO;
import dto.CallHistoryDTO;
import dto.ItemDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class DetailBillOfItem extends HttpServlet {
    private final String WRONG_PAGE = "wrong.html";
    private final String DETAILBILL_PAGE = "detailsbill.jsp";
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
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        int idd = Integer.parseInt(id);
        String url = WRONG_PAGE;
        try{
            CustomerDAO cdao = new CustomerDAO();
            ItemDAO idao = new ItemDAO();
            String fullName = (String) cdao.viewCus(idao.viewItem(idd).getCustomerID()).getFullname();
            int phone=cdao.viewCus(idao.viewItem(idd).getCustomerID()).getPhoneNumber();
            String itemName = (String) idao.viewItem(idd).getItemName();
            
            ItemDTO idto = idao.getItemByItemID(idd);
            if(action.equals("detail")){
                BillDAO bdao = new BillDAO();
                HttpSession session = request.getSession();
                ArrayList<BillDTO> bdto = bdao.getBillByIdItem(idd);
                request.setAttribute("GETNAME", fullName);
                request.setAttribute("GETNAMEITEM", itemName);
                request.setAttribute("ITEM", idao.viewItem(idd));
                request.setAttribute("PHONE", phone);
                session.setAttribute("itemID", idd);
                request.setAttribute("listdetail", bdto);
                request.setAttribute("ITEM", idto);
                url = DETAILBILL_PAGE;
                    
                
            }
            
        }catch(Exception e){
            log("Error at DetailBillOfItem " + e.getMessage());
        }
        request.getRequestDispatcher(url).forward(request, response);     
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
