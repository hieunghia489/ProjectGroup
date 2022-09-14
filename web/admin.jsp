<%-- 
    Document   : admin
    Created on : Jul 18, 2022, 9:51:42 AM
    Author     : user
--%>

<%@page import="dto.StaffDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.StaffDAO"%>
<%@page import="dto.PawnShopDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Thông tin cửa hàng</title>
        <!-- Favicon icon -->
        <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
        <!-- Custom Stylesheet -->
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
                    <!-- Avatar image in top left corner -->
                    <img src="images/itemPic/LOGO.png" style="width:100%">

                    <a href="GetAllStore" class="w3-bar-item w3-button w3-padding-large w3-black">
                        <i class="fa fa-file w3-xxlarge"></i>
                        <p>Thông tin các cửa hàng</p>
                    </a>

                    <a href="KeyController" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                        <i class="fa fa-user w3-xxlarge"></i>
                        <p>Quản lý mã mở khóa</p>
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
                                        <h4 class="card-title">Trang kiểm soát</h4>
                                        <% ArrayList<PawnShopDTO> listShop = (ArrayList<PawnShopDTO>) session.getAttribute("LIST_SHOP");
                                            if (listShop != null) {
                                                StaffDAO daoStaff = new StaffDAO();
                                        %>
                                        <table class="table table-striped table-bordered zero-configuration">
                                            <thead>
                                                <tr>
                                                    <th>Tiệm</th>
                                                    <th>Địa chỉ</th>
                                                    <th>Nhân viên</th>
                                                    <th>Số điện thoại</th>
                                                    <th>Mã mở khóa</th>
                                                    <th>Trạng thái</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% for (PawnShopDTO shop : listShop) { %> 
                                                <tr>
                                                    <td style="text-align: center;padding: 10px 5px;"><%=shop.getStoreName()%></td> 
                                                    <td style="text-align: center;padding: 10px 5px;"><%=shop.getStoreAddress()%></td> 
                                                    <td style="text-align: center;padding: 10px 5px;">
                                                        <%
                                                            ArrayList<StaffDTO> listStaff = daoStaff.getAllStaffInStore(shop.getStoreID());
                                                            for (StaffDTO staff : listStaff) {
                                                        %> 
                                                      <br/> <%= staff.getStaffID()%> : <%=staff.getFullName()%>
                                                        <%
                                                             if (staff.getStaffID().equals(shop.getManagerID())) { %>
                                                        ( Quản lí)
                                                        <% }
                                                     } 
                                                        %>
                                                    </td> 
                                                    <td style="text-align: center;padding: 10px 5px;">0<%=shop.getPhoneNumber() %></td> 
                                                    <td style="text-align: center;padding: 10px 5px;"><%=shop.getComfirmKey() %></td> 
                                                    <% if(shop.isStatusID()) { %>
                                                    <td style="text-align: center;padding: 10px 5px;color: green">Đang hoạt động</td> 
                                                    <td><a href="StoreDetailController?storeID=<%= shop.getStoreID() %>" onclick="return confirm('Bạn chắc chắn muốn đổi trạng thái hoạt động của cửa hàng <%= shop.getStoreName() %>?');">Dừng hoạt động</a></td>
                                                    <% }else{ %>
                                                    <td style="text-align: center;padding: 10px 5px;color: red">Đã ngưng hoạt động</td> 
                                                    <% }%>
                                                </tr>
                                              <% }
                                              %>

                                            </tbody>
                                        </table>
                                        <% }%>
                                        <br/><h3> Thông báo : ${requestScope.RESULT}</h3>
                                    </div>
                                    <div class="table-responsive">



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
