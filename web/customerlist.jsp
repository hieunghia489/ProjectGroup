<%-- 
    Document   : customerlist
    Created on : Jun 14, 2022, 11:38:48 AM
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dao.CustomerDAO"%>
<%@page import="dto.CustomerDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Thông tin khách hàng</title>
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
                    <a href="BillController" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                        <i class="fa fa-file w3-xxlarge"></i>
                        <p>Hóa đơn</p>
                    </a>

                    <a href="ListCustomerController" class="w3-bar-item w3-button w3-padding-large w3-black">
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
                                    <div style="text-align: right;">
                                        <button>
                                            <a href="LogoutController" style="padding: 10px 5px;">Đăng xuất</a>                                       
                                        </button>
                                    </div>
                                    <div id ="header_title_table">
                                        <h4 class="card-title">Thông tin khách hàng</h4>
                                    </div>
                                    <div style="text-align: right; margin-right: 30px;">
                                        <button>
                                            <a href="OldCustomer" style="text-decoration: none;">Danh sách khách hàng cũ</a>
                                        </button>
                                    </div>
                                    <div class="table-responsive">
                                        <%
                                            ArrayList<CustomerDTO> cusList = (ArrayList<CustomerDTO>) request.getAttribute("CUSTOMERLIST");
                                            if (cusList != null) {
                                        %>
                                        <table class="table table-striped table-bordered zero-configuration text-center">
                                            <thead>
                                                <tr>
                                                    <th>Họ và tên</th>
                                                    <th>CMND</th>
                                                    <th>Địa chỉ</th>
                                                    <th>Điện thoại</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    for (CustomerDTO cus : cusList) {
                                                %>
                                                <tr>
                                                    <td><%= cus.getFullname()%></td>
                                                    <td><%= cus.getCustomerID()%></td>
                                                    <td><%= cus.getAddress() %></td>
                                                    <td>0<%= cus.getPhoneNumber()%></td>
                                                    <td>
                                                 <button><a href="InforCusToUpdate?action=update&customerID=<%= cus.getCustomerID()%>&fullname=<%= cus.getFullname()%>&address=<%= cus.getAddress()%>&phoneNumber=<%= cus.getPhoneNumber() %>">Cập nhật</a></button>                                                    
                                                    </td>
                                                    <td>
                                                        <button><a href="ViewItemOfCustomer?action=details&customerID=<%= cus.getCustomerID()%> ">Tài sản thế chấp</a></button>


                                                    </td>
                                                </tr>
                                                <% } %>
                                            </tbody>
                                        </table>
                                        <% } else request.getRequestDispatcher("bill.jsp"); %>%>
                                    </div>
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