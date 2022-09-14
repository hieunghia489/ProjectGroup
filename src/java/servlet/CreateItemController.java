/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ItemDAO;
import dto.ItemDTO;
import dto.ItemError;
import dto.StaffDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class CreateItemController extends HttpServlet {

    private static final String UPLOAD_DIR = "images";
    private static final String UPLOAD_FOLDER = "itemPic";
    private static final String ERROR = "createitem.jsp";
    private static final String SUCCESS = "createbill.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
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
        String url = ERROR;
        ItemError itemError = new ItemError();
        request.setCharacterEncoding("utf-8");
        try {
            String action = request.getParameter("action");
            if ("Next".equals(action)) {
                boolean checkValidation = true;
                String itemName = request.getParameter("itemName").trim();
                String itemPic = uploadFile(request);
                String customerID = request.getParameter("customerID").trim();
                String sendingDate = request.getParameter("itemSendingDate").trim();
                HttpSession session = request.getSession();
                Date itemSendingDate = null;
                StaffDTO staff = (StaffDTO) session.getAttribute("STAFF");
                int storeID = staff.getStoreID();
                if (sendingDate.equals("")) {
                    itemError.setItemSendingDateError("Ngày gửi không được để trống!");
                    checkValidation = false;
                } else {
                    itemSendingDate = Date.valueOf(sendingDate);
                }
                if (itemName.length() < 5 || itemName.length() > 20) {
                    itemError.setItemNameError("Tên vật phẩm phải nằm khoảng [5,20] từ!");
                    checkValidation = false;
                }
                if (customerID.length() != 12) {
                    itemError.setItemCusIDError("Chứng minh thư phải có 12 số!");
                    checkValidation = false;
                }
                if (storeID == 0) {
                    itemError.setItemStoreIDError("Mã cửa hàng không được để trống!");
                    checkValidation = false;
                }
                if (itemPic.equals("")) {
                    itemError.setItemPicError("Hình ảnh của bạn đang trống");
                    checkValidation = false;
                }

                if (checkValidation) {
                    ItemDAO dao = new ItemDAO();
                    int createitem = dao.createItem(new ItemDTO(itemName, itemPic, customerID, storeID, itemSendingDate, itemSendingDate, true, true));
                    ItemDAO daoItem = new ItemDAO();
                    int itemID = daoItem.getLastItemID();
                    ItemDTO item = new ItemDTO(itemID, itemName, itemPic, customerID, storeID, itemSendingDate, itemSendingDate, true, true);
                    if (createitem > 0) {
                        session.setAttribute("ITEM", item);
                    }
                    url = SUCCESS;
                } else {
                    request.setAttribute("ITEM_ERROR", itemError);
                }
            }
        } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
            log("Error at CreateItemController " + e.getMessage() );
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("itemPic");

            //fileName: picture-001.jpg
            fileName = (String) getFileName(filePart);

            //applicationPath: C:\Users\Lonely\Documents\NetBeansProjects\Shop_Bonfire\build\web
            String applicationPath = request.getServletContext().getRealPath("");

            //File.separator: \ 
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator + UPLOAD_FOLDER + File.separator;

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (IOException e) {
                log("Error at CreateItemController " + e.getMessage());
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception e) {
            log("Error at CreateItemController " + e.getMessage());
            fileName = "";
        }
        return fileName;
    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
