<%-- 
    Document   : details
    Created on : Jun 8, 2022, 10:39:09 PM
    Author     : Admin
--%>


<%@page import="dto.CallHistoryDTO"%>
<%@page import="dao.CallHistoryDAO"%>
<%@page import="dao.StaffDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="dao.BillDAO"%>
<%@page import="dto.ItemDTO"%>
<%@page import="dao.ItemDAO"%>
<%@page import="dao.CustomerDAO"%>
<%@page import="dto.BillDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Thông tin chi tiết hóa đơn</title>
        <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
        <link href="./plugins/tables/css/datatable/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/style.css" rel="stylesheet"> 
    </head>
    <body>
        <div id="preloader">
            <div class="loader">
                <svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
                </svg>
            </div>
        </div>
        <div id="main-wrapper">
            <ul class="metismenu" id="menu">
                <nav class="w3-sidebar w3-bar-block w3-small w3-hide-small w3-center">
                    <img src="images/itemPic/LOGO.png" style="width:100%">

                    <a href="BillController" class="w3-bar-item w3-button w3-padding-large w3-black">
                        <i class="fa fa-file w3-xxlarge"></i>
                        <p>Hóa đơn</p>
                    </a>

                    <a href="ListCustomerController" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                        <i class="fa fa-user w3-xxlarge"></i>
                        <p>Thông tin khách hàng</p>
                    </a>

                    <a href="ItemController" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                        <i class="fa fa-cart-plus w3-xxlarge"></i>
                        <p>Thông tin món đồ</p>
                    </a>
                </nav>
            </ul>
            <div class="content-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">

                                    <div class="table-responsive">
                                        <%
                                            ArrayList<BillDTO> detail = (ArrayList<BillDTO>) request.getAttribute("listdetail");
                                            String fullName = (String) request.getAttribute("GETNAME");
                                            String itemName = (String) request.getAttribute("GETNAMEITEM");
                                            String noti = (String) request.getAttribute("NOTIFICATION");
                                            int phone = (int) request.getAttribute("PHONE");
                                            ItemDTO item = (ItemDTO) request.getAttribute("ITEM");
                                            Calendar cal = Calendar.getInstance();
                                            int bilID = 0;
                                            int itemID = 0;
                                            Date beginDate = null;
                                            int numberDay = 0;
                                            int money = 0;
                                            float interest = 0;
                                            CallHistoryDAO daoCall = new CallHistoryDAO();
                                            CallHistoryDTO call = daoCall.getCallHistory(item.getItemID());
                                            int m1 = 0, m2 = 0;
                                            String dateError = (String) request.getSession().getAttribute("DATE_WRONG");
                                            if (detail != null) {
                                        %>
                                        <div style="display: flex; justify-content: center;">
                                            <div style="border: solid 1px;width: 700px;">
                                                <h1 style="padding: 10px 20px;margin: 0px;">Họ tên&nbsp &nbsp &nbsp &nbsp &nbsp : <%= fullName%></h1>
                                                <h1 style="padding: 10px 20px;margin: 0px;">Số điện thoại : 0<%= phone%></h1>
                                                <h1 style="padding: 10px 20px;margin: 0px;">Tên đồ vật &nbsp : <%= itemName%></h1>  
                                                <img src="images/itemPic/<%= item.getItemPic()%>" width="220" height="150" alt="itempic" style="margin-left: 200px"/><br/>
                                                <% if (call != null) { %>
                                                <h3>Lịch sử cuộc gọi nhắc nhở : </h3>
                                                <% if (call.getDate1() != null) {%>
                                                Lần 1 : <%=call.getDate1()%>
                                                <% }
                                                    if (call.getDate2() != null) {%>
                                              <br/>  Lần 2 : <%=call.getDate2()%>
                                                <% }
                                                    }
                                                    if (dateError != null) {
                                                %>
                                               <br/> <%= dateError%>
                                                <% } %>
                                            </div> 
                                        </div>
                                        <br>
                                        <div style="display: flex; justify-content: center;">
                                            <table class="table table-striped table-bordered zero-configuration text-center" >
                                                <thead>
                                                    <tr>

                                                        <th>Ngày cầm</th>
                                                        <th>Ngày trả lãi</th>
                                                        <th>Tiền gốc</th>
                                                        <th>Lãi suất</th>
                                                        <th>Số tiền đóng</th>
                                                        <th>Còn lại</th>
                                                        <th>Nhân viên quản lí</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        for (BillDTO b : detail) {
                                                    %>
                                                    <tr>
                                                        <td style="text-align: center;padding: 10px 5px;"><%= b.getBillBeginDate()%></td>
                                                        <td style="text-align: center;padding: 10px 5px;"><%= b.getNumberDays()%></td>
                                                        <td style="text-align: center;padding: 10px 5px;"><%= b.getPawnMoney()%>đ</td>
                                                        <td style="text-align: center;padding: 10px 5px;"><%= b.getInterestRate()%>%</td>
                                                        <td style="text-align: center;padding: 10px 5px;"><%= b.getReturnMoney()%>đ</td>
                                                        <td style="text-align: center;padding: 10px 5px;">
                                                            <%
                                                                if (b.getBillBeginDate().equals(item.getItemSendingDate())) {
                                                                    m1 = (b.getPawnMoney() - b.getReturnMoney());
                                                                    out.print(m1 + "đ");
                                                                } else {
                                                                    m2 = (b.getPawnMoney() + (int) ((b.getPawnMoney() * b.getInterestRate() / 100)) - b.getReturnMoney());
                                                                    out.print(m2 + "đ");
                                                                }
                                                            %>                        
                                                        </td>
                                                        <td style="text-align: center;padding: 10px 5px;">
                                                            <%
                                                                StaffDAO dao = new StaffDAO();
                                                                out.print(dao.getStaffByStaffID(b.getStaffID()).getFullName());
                                                            %>
                                                        </td>
                                                        <% bilID = b.getBillID(); %>
                                                        <% beginDate = b.getBillBeginDate(); %>
                                                        <% numberDay = b.getNumberDays(); %>
                                                        <%
                                                            if (b.getBillBeginDate().equals(item.getItemSendingDate())) {
                                                                money = m1;
                                                            } else {
                                                                money = m2;
                                                            }
                                                        %>
                                                        <% interest = b.getInterestRate(); %>
                                                        <% itemID = b.getItemID();%>
                                                    </tr>                                               
                                                    <%               }
                                                    %>
                                                </tbody>
                                            </table>
                                        </div>
                                        <%
                                            } else request.getRequestDispatcher("bill.jsp");
                                           if (money != 0) {
                                        %>
                                        <button style="padding: 5px 15px;margin-left: 540px; ">
                                            <a style="text-decoration: none; color: black;" href="AddNewBill?action=add&id=<%= bilID%>&date=<%= beginDate%>&numberDay=<%= numberDay%>&money=<%= money%>&interest=<%= interest%>&itemID=<%= itemID%>">Thêm hóa đơn</a>
                                        </button>
                                        <button style="padding: 5px 15px;margin-left: 50px; ">
                                            <a style="text-decoration: none; color: black;" href="CallController?itemID=<%= itemID%>">Xác nhận đã gọi</a>
                                        </button>
                                        </br>
                                        <div style="text-align: center; margin-top: 10px; font-size: 20px;">
                                            <font color="red"><% if (noti != null) {
                                                    out.print(noti);
                                                }%></font>
                                        </div>    
                                        <%
                                        } else {
                                        %>
                                        <div style="text-align: center;sfont-size: 20px;">
                                            <font color="red" style="font-size: 20px;"><% out.print("Khách hàng đã hoàn tất thanh toán hóa đơn"); %></font>
                                        </div>
                                        <%
                                            }
                                        %>
                                        </br>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script src="plugins/common/common.min.js"></script>
            <script src="js/custom.min.js"></script>
            <script src="js/settings.js"></script>
            <script src="js/gleek.js"></script>
            <script src="js/styleSwitcher.js"></script>

            <script src="./plugins/tables/js/jquery.dataTables.min.js"></script>
            <script src="./plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
            <script src="./plugins/tables/js/datatable-init/datatable-basic.min.js"></script>

    </body>
</html>
