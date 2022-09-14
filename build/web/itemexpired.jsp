<%-- 
    Document   : itemexpired
    Created on : Jul 18, 2022, 7:26:02 PM
    Author     : Admin
--%>

<%@page import="dao.CustomerDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Danh sách đồ đã bị tịch thu</title>
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

                    <a href="ListCustomerController" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                        <i class="fa fa-user w3-xxlarge"></i>
                        <p>Thông tin khách hàng</p>
                    </a>

                    <a href="ItemController" class="w3-bar-item w3-button w3-padding-large w3-black">
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
                                    <div id ="header_title_table">
                                        <h4 class="card-title">Thông tin món đồ</h4>
                                    </div>
                                    <div class="table-responsive">
                                        <%
                                            ArrayList<ItemDTO> itemList = (ArrayList<ItemDTO>) request.getAttribute("ITEMEXPIRED");
                                            if (itemList != null) {
                                        %>
                                        <table class="table table-striped table-bordered zero-configuration text-center">
                                            <thead>
                                                <tr>
                                                    <th>Tên món hàng</th>
                                                    <th>Ảnh món hàng</th>
                                                    <th>Tên người cầm</th>
                                                    <th>Ngày cầm đồ</th>
                                                    <th>Ngày hết hạn cầm đồ</th>
                                                    <th>Tình trạng cầm đồ</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    CustomerDAO dao = new CustomerDAO();
                                                    for (ItemDTO item : itemList) {
                                                %>
                                                <tr>
                                                    <td><%= item.getItemID()%></td>
                                                    <td><%= item.getItemName()%></td>
                                                    <td><img src="images/itemPic/<%= item.getItemPic()%>" width="220" height="150"/></td>
                                                    <td><%= dao.getCusByID(item.getCustomerID()).getFullname() %></td>
                                                    <td><%= item.getItemSendingDate()%></td>
                                                    <td><%= item.getItemGettingDate()%></td>
                                                    <td><% if(item.isIsKeep() == false){out.print("Đã tịch thu");}%></td>
                                                </tr>
                                            <% } %>
                                            </tbody>
                                        </table>
                                        <% }%>
                                        <div style="text-align: center;">
                                            <button>
                                                <a href="ItemController" style="text-decoration: none;">Quay lại</a>
                                            </button>
                                        </div>
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
