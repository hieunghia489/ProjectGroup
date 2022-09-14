<%-- 
    Document   : key
    Created on : Jul 20, 2022, 9:39:01 PM
    Author     : user
--%>

<%@page import="dao.PawnShopDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.KeyDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Danh sách mỡ mở khóa</title>
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

                    <a href="GetAllStore" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
                        <i class="fa fa-file w3-xxlarge"></i>
                        <p>Thông tin các của hàng</p>
                    </a>

                    <a href="KeyController" class="w3-bar-item w3-button w3-padding-large w3-black">
                        <i class="fa fa-user w3-xxlarge"></i>
                        <p>Quản lý mã mở khóa</p>
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
                                        <h4 class="card-title">Mã mở khóa</h4>
                                        <% ArrayList<KeyDTO> listKey = (ArrayList<KeyDTO>) session.getAttribute("LIST_KEY");
                                            if (listKey != null) {
                                        %>
                                        <table class="table table-striped table-bordered zero-configuration">
                                            <thead>
                                                <tr>
                                                    <th>Mã</th>
                                                    <th>Trạng thái</th>
                                                    <th>Admin</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% for (KeyDTO key : listKey) {%> 
                                                <tr>
                                                    <td style="text-align: center;padding: 10px 5px;"><%=key.getComfirmKey()%></td> 
                                                    <td style="text-align: center;padding: 10px 5px;"><%
                                                        if (!key.isStatusKey()) {%>
                                                        Chưa sử dụng
                                                        <%
                                                        } else {
                                                        %>
                                                        Đã sử dụng bởi<%
                                                            PawnShopDAO daoShop = new PawnShopDAO();
                                                        %>
                                                        <%= daoShop.getShopByKey(key.getComfirmKey()).getStoreName()%>
                                                        <% }
                                                        %></td> 
                                                    <td style="text-align: center;padding: 10px 5px;"><%=key.getAdminID()%></td> 
                                                </tr>
                                                <% }
                                                %> 
                                            </tbody>
                                        </table>
                                        <% }%>
                                        <div style="text-align: center"> <h2>Tạo mã mở khóa tài khoản</h2>
                                            <form action="CreateKeyController">
                                                <input type="text" name="confirmKey" value="${sessionScope.KEY}" readonly="readonly" style="text-align: center"/>
                                                <input type="submit" value="Tạo mã" name="action" />
                                                <input type="submit" value="Sử dụng" name="action" />
                                            </form>
                                        </div>
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
