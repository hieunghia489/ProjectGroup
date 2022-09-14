/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.PawnShopDAO;
import dao.StaffDAO;
import dto.PawnShopErrorDTO;
import dto.StaffDTO;
import dto.StaffErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "CreateStaffController", urlPatterns = {"/CreateStaffController"})
public class CreateStaffController extends HttpServlet {

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
        StaffErrorDTO staffError=new StaffErrorDTO();
             PawnShopErrorDTO shopError=new PawnShopErrorDTO();
             String url="login.jsp";
             PawnShopDAO daoShop=new PawnShopDAO();
        try{
            String action=request.getParameter("action");
            if(action.equals("Register")){
            boolean error=false;
            String managerID=request.getParameter("cmnd");
            StaffDAO daoStaff=new StaffDAO();
            if(managerID.length()!=12){
                shopError.setManagerIDError("Số căn cước phải có 12 chữ số");
                error=true;
            }else{
                if(daoStaff.viewStaff(managerID)==null){
                    shopError.setManagerIDError("Số căn cước này không tồn tại trong hệ thống");
                    error=true;
                }
            }
            String key=request.getParameter("key");
       if(key.length()!=8){
           shopError.setComfirmKeyError("Mã mở khóa đã nhập sai cú pháp");
           error =true;
       }else{
           
           if(daoShop.getShopByKey(key)==null){
               shopError.setComfirmKeyError("Mã mở khóa không tồn tại");
               error=true;
           }else{
               if(!daoShop.getShopByKey(key).getManagerID().equals(managerID)){
                   shopError.setComfirmKeyError("Cửa hàng hoặc quản lí đã không trùng khớp");
                   error=true;
               }
           }
       }
            int phoneNumber=0;
                String staffID = request.getParameter("cmndStaff");
                if (staffID.length() != 12) {
                    staffError.setStaffIDError("Căn cước phải có 12 số");
                    error = true;
                }else{
                    
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
                 if(!error){
                daoStaff.createStaff(staffID, password,staffName, daoShop.getShopByKey(key).getStoreID());
                url="login.jsp";
                 }else{
                    request.setAttribute("ErrorStaff", staffError);
                    request.setAttribute("ErrorShop", shopError);
                    url="createStaff.jsp";
                }
            }
        }catch (SQLException e) {
            if (e.getMessage().contains("duplicate")) {
                url = "createStaff.jsp";
                staffError.setStaffIDError("Căn cước này đã bị sử dụng");
                request.setAttribute("ErrorStaff", staffError);
            }
        } catch (NumberFormatException e) {
            shopError.setPhoneNumberError("Số điện thoại phải có 10 chữ số");
            url = "createStaff.jsp";
            request.setAttribute("ErrorShop", shopError);
        } catch (ClassNotFoundException ex) {
            log("Error at RegisterController "+ex.toString());
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
