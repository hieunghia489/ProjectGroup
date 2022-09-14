<%-- 
    Document   : updateStore
    Created on : Jul 24, 2022, 6:01:48 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Information Page</title>
        <meta name="viewport" content="width=device-width,initial-scale=1">
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
        <c:set var="storeID" value="${requestScope.storeID}"/>
        <c:set var="daoStore" value="${PawnShopDAO()}"/>
        <c:set var="store" value="${daoStore.viewPawnShop(storeID)}"/>
        <c:set var="daoStaff" value="${StaffDAO()}" />
        <c:set var="listStaff" value="${daoStaff.getAllStaffInStore(storeID)}"/>
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
                        <p>Thông tin các cửa hàng</p>
                    </a>

                    <a href="KeyController" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
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
                                    <div id ="header_title_table">
                                        <h4 class="card-title">Thông tin cửa hàng</h4>

                                        <table class="table table-striped table-bordered zero-configuration">
                                            <thead>
                                                <tr>
                                                    <th>Tiệm</th>
                                                    <th>Địa chỉ</th>
                                                    <th>Nhân viên</th>
                                                    <th>Sốđiện thoại</th>
                                                    <th>Mã mở khóa</th>
                                                    <th>Trạng thái</th>
                                                </tr>
                                            </thead>
                                        </table>
                                        <form action="UpdateStoreController">
                                            <div class="container" style="margin-bottom: 10px;">
                                                <div class="row">
                                                    <div class="col-sm-5" style=" padding-left: 300px;">
                                                        Tiệm
                                                    </div>
                                                    <div class="col-sm-7">
                                                        <input type="text" name="oldDate" value="<%= date%>" style="text-align: center;"readonly=""/> 
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="container" style="margin-bottom: 10px;">
                                                <div class="row">
                                                    <div class="col-sm-5" style=" padding-left: 300px;">
                                                        Địa chỉ
                                                    </div>
                                                    <div class="col-sm-7">
                                                        <input type="text" name="oldDate" value="<%= date%>" style="text-align: center;"readonly=""/> 
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="container" style="margin-bottom: 10px;">
                                                <div class="row">
                                                    <div class="col-sm-5" style=" padding-left: 300px;">
                                                        Tiệm
                                                    </div>
                                                    <div class="col-sm-7">
                                                        <input type="text" name="oldDate" value="<%= date%>" style="text-align: center;"readonly=""/> 
                                                    </div>
                                                </div>
                                            </div>

                                        </form>
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
