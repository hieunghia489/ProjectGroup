/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.KeyDAO;
import dao.PawnShopDAO;
import dao.StaffDAO;
import dto.PawnShopErrorDTO;
import dto.StaffDTO;
import dto.StaffErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String url = "register.jsp";
        StaffErrorDTO staffError = new StaffErrorDTO();
        PawnShopErrorDTO shopError = new PawnShopErrorDTO();
        try {
            String action = request.getParameter("action");
            if (action.equals("Register")) {
int phoneNumber=0;
                boolean error = false;
                String staffID = request.getParameter("cmnd");
                if (staffID.length() != 12) {
                    staffError.setStaffIDError("Căn cước phải có 12 số");
                    error = true;
                }
                String staffName = request.getParameter("fullName");
                if (staffName.length() < 5) {
                    staffError.setFullNameError("Mục tên phải có ít nhất 5 ký tự");
                    error = true;
                }
                String password = request.getParameter("password");
                if (password.length() < 5) {
                    staffError.setPasswordError("Mật khẩu phải có tối thiểu 6 ký tự");
                    error = true;
                } else {
                    String confirmPassword = request.getParameter("confirmPassword");
                    if (!confirmPassword.equals(password)) {
                        staffError.setConfirmPasswordError("Mật khẩu bạn nhập không trùng hớp với nhau");
                        error = true;
                    }
                }

                String storeName = request.getParameter("nameShop");
                if (storeName.length() < 5) {
                    shopError.setStoreNameError("Tên cửa hàng tối thiểu phải có 5 kí tự");
                    error = true;
                }
                String address = request.getParameter("address");
                if (address.length() < 10) {
                    shopError.setStoreAddressError("Địa chỉ cửa hàng phải có trên 10 kí tự");
                    error = true;
                }
                String phone = request.getParameter("mobile");
                
                String key = request.getParameter("key");
                if (key.length() != 8) {
                    shopError.setComfirmKeyError("Hãy kiểm tra lại mã mở khóa của bạn");
                    error = true;
                } else {
                    KeyDAO daoKey = new KeyDAO();
                    if (daoKey.checkKey(key)) {
                        shopError.setComfirmKeyError("Hãy kiểm tra lại mã mở khóa của bạn");
                        error = true;
                    }
                }
                if (phone.length() != 10) {
                    shopError.setPhoneNumberError("Số điện phải có 10 chữ số");
                    error = true;
                }else{
                    phoneNumber=Integer.parseInt(phone);
                }
                if(!error){
                    StaffDAO daoStaff=new StaffDAO();
                    PawnShopDAO daoShop=new PawnShopDAO();
                    if(daoStaff.createStaff(staffID, password, staffName, 0))
                    if(daoShop.createPawnShop(storeName, address, phoneNumber, staffID, key)){
                        int storeID=daoShop.getShopByKey(key).getStoreID();
                        daoStaff.updateStaff(staffID, password, staffName, storeID);
                        KeyDAO daokey=new KeyDAO();
                        daokey.updateKey(key, true);
                        url="login.jsp";
                    }
                }else{
                    request.setAttribute("ErrorStaff", staffError);
                    request.setAttribute("ErrorShop", shopError);
                }
            } else {
                url = "login.jsp";
            }

        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate")) {
                url = "register.jsp";
                staffError.setStaffIDError("Căn cước này đã bị sử dụng");
                request.setAttribute("ErrorStaff", staffError);
            }
        } catch (NumberFormatException e) {
            shopError.setPhoneNumberError("Số điện thoại phải có 10 chữ số");
            url = "register.jsp";
            request.setAttribute("ErrorShop", shopError);
        } catch (ClassNotFoundException ex) {
            log("Error at RegisterController "+ex.toString());
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
