<%-- 
    Document   : details_item
    Created on : Aug 2, 2022, 1:27:44 AM
    Author     : Admin
--%>

<%@page import="dto.ItemDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Thông tin chi tiết sản phẩm</title>
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
                                            ArrayList<ItemDTO> detail = (ArrayList<ItemDTO>) request.getAttribute("ITEM");                     
                                            if (detail != null) {
                                        %>
                               
                                        <br>
                                        <div style="display: flex; justify-content: center;">
                                            <table class="table table-striped table-bordered zero-configuration text-center" >
                                                <thead>
                                                    <tr>

                                                        <th>Tên Món Hàng</th>
                                                        <th>Ảnh món hàng</th>
                                                        <th>Mã Cửa Hàng</th>
                                                        <th>Ngày Cầm đồ</th>
                                                        <th>Ngày Hết Hạn Cầm Đồ</th>  
                                                        <th>Tình trạng</th>  
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        for (ItemDTO i : detail) {
                                                    %>
                                                    <tr>
                                                        <td style="text-align: center;padding: 10px 5px;"><%= i.getItemName() %></td>
                                                        <td style="text-align: center;padding: 10px 5px;"><img src="images/itemPic/<%= i.getItemPic()%>" width="220" height="150"/></td>
                                                        <td style="text-align: center;padding: 10px 5px;"><%= i.getStoreID()%></td>
                                                        <td style="text-align: center;padding: 10px 5px;"><%= i.getItemGettingDate()%></td>
                                                        <td style="text-align: center;padding: 10px 5px;"><%= i.getItemSendingDate()%></td>
                                                        <td style="text-align: center;padding: 10px 5px;"><% if(i.isStatusID()== false) {out.print("Đã trả");}
                                                        else {
                                                            if(i.isIsKeep()==false){
                                                                out.print("Tịch Thu");
                                                            }else{
                                                                out.print("Đang hoàn trả");
                                                            }
                                                        }%></td>
                                                        
                                                        
                                                        
                                                    </tr>                                               
                                                    <%               }
                                                    %>
                                                </tbody>
                                            </table>
                                        </div>
                                        <%            }

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

