<%-- 
    Document   : register
    Created on : May 26, 2022, 8:35:59 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Colorlib Templates">
        <meta name="author" content="Colorlib">
        <meta name="keywords" content="Colorlib Templates">

        <!-- Title Page-->
        <title>Đăng kí</title>

        <!-- Icons font CSS-->
        <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <!-- Font special for pages-->
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Vendor CSS-->
        <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="css/main_1.css" rel="stylesheet" media="all">
    </head>

    <body>
        <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
            <div class="wrapper wrapper--w680">
                <div class="card card-4">
                    <div class="card-body">
                        <c:set var="staffError" value="${requestScope.ErrorStaff}"/>
                        <c:set var="shopError" value="${requestScope.ErrorShop}"/>
                        <h2 class="title">Đăng kí tài khoản</h2>
                        <form action="RegisterController" method="POST">
                            <div>
                                <div class="" style="text-align: center" ><h2 >Thông Tin Chủ Tiệm</h2></div>
                                <div class="row row-space">
                                    <div class="col-2">
                                        <div class="input-group">
                                            <label class="label">Họ Tên</label>
                                            <input class="input--style-4" type="text" name="fullName"value="${param.fullName}">
                                            <font style="color: red">${staffError.fullNameError}</font>
                                        </div>
                                    </div>
                                    <div class="col-2">
                                        <div class="input-group">
                                            <label class="label">SĐT</label>
                                            <input class="input--style-4" type="text" name="mobile" value="${param.mobile}">
                                             <font style="color: red">${shopError.phoneNumberError}</font>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div class="input-group">
                                        <label class="label">CMND</label>
                                        <input class="input--style-4" type="text" name="cmnd" value="${param.cmnd}">
                                         <font style="color: red">${staffError.staffIDError}</font>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="" style="text-align: center" ><h2 >Thông Tin Tiệm Cầm Đồ</h2></div>
                                <div>
                                    <div class="input-group">
                                        <label class="label">Tên Tiệm</label>
                                        <input class="input--style-4" type="text" name="nameShop" value="${param.nameShop}">
                                         <font style="color: red">${shopError.storeNameError}</font>
                                    </div>
                                </div>
                                <div>
                                    <div class="input-group">
                                        <label class="label">Địa chỉ</label>
                                        <input class="input--style-4" type="text" name="address" value="${param.address}">
                                         <font style="color: red">${shopError.storeAddressError}</font>
                                    </div>
                                </div>
                            </div>

                            <div>
                                <div class="" style="text-align: center" ><h2 >Tài Khoản</h2></div>
                                <div class="row  row-space">
                                    <div class="col-2">
                                        <div class="input-group">
                                            <label class="label">Mật Khẩu</label>
                                            <input class="input--style-4" type="text" name="password" value="${param.password}">
                                             <font style="color: red">${staffError.passwordError}</font>
                                        </div>
                                    </div>
                                    <div class="col-2">
                                        <div class="input-group">
                                            <label class="label">Xác Nhận Mật Khẩu</label>
                                            <input class="input--style-4" type="password" name="confirmPassword" value="${param.confirmPassword}">
                                             <font style="color: red">${staffError.confirmPasswordError}</font>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="">
                                <label class="label">Mã Mở Tài Khoản</label>
                                <div class="rs-select2 js-select-simple select--no-search">
                                    <input class="input--style-4" type="password" name="key" value="${param.key}">
                                     <font style="color: red">${shopError.comfirmKeyError}</font>
                                    <div class="select-dropdown"></div>
                                </div>
                            </div>
                         
                            <div class="row row-space">
                                <div class="p-t-15"  style="text-align: center">
                                    <button class="btn btn--radius-2 btn--blue" name="action" value="Return" type="submit">Quay lại trang chủ</button>
                                </div>
                                <div class="p-t-15"  style="text-align: center">
                                    <button class="btn btn--radius-2 btn--blue" name="action" value="Register" type="submit">Đăng kí</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Jquery JS-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <!-- Vendor JS-->
        <script src="vendor/select2/select2.min.js"></script>
        <script src="vendor/datepicker/moment.min.js"></script>
        <script src="vendor/datepicker/daterangepicker.js"></script>

        <!-- Main JS-->
        <script src="js/global.js"></script>

    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->
