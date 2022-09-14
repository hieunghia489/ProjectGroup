<%-- 
    Document   : updatebill
    Created on : Jun 16, 2022, 11:39:19 AM
    Author     : Admin
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="dao.CustomerDAO"%>
<%@page import="dao.ItemDAO"%>
<%@page import="dao.BillDAO"%>
<%@page import="dto.BillDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Cập nhật hóa đơn</title>
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
                                    <%
                                        Date date = (Date) session.getAttribute("date");
                                        Date dateExpected = (Date) session.getAttribute("dateExpected");
                                        int money = (int) session.getAttribute("money");
                                        int numberDay = (int) session.getAttribute("numberDay");
                                        int itemId = (int) session.getAttribute("itemID");
                                        float interestRate = (float) session.getAttribute("interestRate");
                                        int total = (int) (money * interestRate / 100);
                                        String errorDate = (String) session.getAttribute("ERRORDATE");
                                        String errorMoney = (String) session.getAttribute("ERRORMONEY");

                                    %>
                                    <form action="CompleteAddBill">
                                        <div class="container" style="margin-bottom: 10px;">
                                            <div class="row">                                             
                                                <div class="col-sm-5" style=" padding-left: 300px;">
                                                    Ngày đóng gần nhất
                                                </div>
                                                <div class="col-sm-7">
                                                    <input type="text" name="oldDate" value="<%= date%>" style="text-align: center;"readonly=""/> 
                                                </div>
                                            </div>
                                        </div>
                                        <div class="container" style="margin-bottom: 10px;">
                                            <div class="row">
                                                <div class="col-sm-5" style=" padding-left: 300px;">
                                                    Ngày dự kiến đóng
                                                </div>
                                                <div class="col-sm-7">
                                                    <input type="text" name="expectedDate" value="<%= dateExpected%>" style="text-align: center;"readonly=""/> 
                                                </div>
                                            </div>
                                        </div>
                                        <div class="container" style="margin-bottom: 10px;">
                                            <div class="row">
                                                <div class="col-sm-5" style=" padding-left: 300px;">
                                                    Ngày đóng tiền
                                                </div>
                                                <div class="col-sm-7">
                                                    <input type="date" name="itemSendingDate"  style="width: 150px;height: 30px;border-radius: 10px;padding: 20px"/>
                                                </div>
                                            </div>

                                            <div style="text-align: center;">
                                                <font color="red">
                                                    <%
                                                        if (errorDate != null) {
                                                            out.print(errorDate);
                                                        }
                                                    %>
                                                </font>
                                            </div>
                                        </div>
                                        <div class="container" style="margin-bottom: 10px;">
                                            <div class="row">
                                                <div class="col-sm-5" style=" padding-left: 300px;">
                                                    Số ngày cầm
                                                </div>
                                                <div class="col-sm-7">
                                                    <input type="text" name="txtPayDate" value="<%= numberDay%>" readonly="" style="text-align: center;"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="container" style="margin-bottom: 10px;">
                                            <div class="row">
                                                <div class="col-sm-5"  style=" padding-left: 300px;">
                                                    Tiền tiền khách nợ
                                                </div>
                                                <div class="col-sm-7">
                                                    <input type="text" name="txtPawnMoney" value="<%= money%>" readonly="" style="text-align: center;"/>     
                                                </div>
                                            </div>
                                        </div>
                                        <div class="container" style="margin-bottom: 10px;">
                                            <div class="row">
                                                <div class="col-sm-5"  style=" padding-left: 300px;">
                                                    Tiền Lãi
                                                </div>
                                                <div class="col-sm-7">
                                                    <input type="text" name="txtInterest" value="<%= interestRate%>"readonly="" style="text-align: center;"/>     
                                                </div>
                                            </div>
                                        </div>
                                        <div class="container" style="margin-bottom: 10px;">
                                            <div class="row">
                                                <div class="col-sm-5"  style=" padding-left: 300px;">
                                                    Số Tiền Cần Đóng
                                                </div>
                                                <div class="col-sm-7">
                                                    <input type="text" name="txtDebt" value="<%= money + total%>" readonly="" style="text-align: center;"/>     
                                                </div>
                                            </div>
                                        </div>
                                        <div class="container" style="margin-bottom: 10px;">
                                            <div class="row">
                                                <div class="col-sm-5"  style=" padding-left: 300px;">
                                                    Số Tiền Đóng
                                                </div>
                                                <div class="col-sm-7">
                                                    <input type="text" name="txtPayMoney" value="" placeholder="<%= money + total%>" style="text-align: center;"/>
                                                </div>
                                            </div>
                                           
                                            <div style="text-align: center;">
                                                <font color="red">
                                                    <%
                                                        if (errorMoney != null) {
                                                            out.print(errorMoney);
                                                        }
                                                    %>
                                                </font>
                                            </div>
                                        </div>
                                        <div style="margin-top: 20px; margin-left: 550px;">
                                            <input type="submit" value="Thêm" style="padding: 5px 10px;">
                                        </div>                                               
                                    </form>

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
