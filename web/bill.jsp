<%-- 
    Document   : bill
    Created on : Jun 6, 2022, 10:37:54 AM
    Author     : Admin
--%>

<%@page import="java.lang.Integer"%>
<%@page import="java.util.Map"%>
<%@page import="dao.BillDAO"%>
<%@page import="dao.ItemDAO"%>
<%@page import="dao.CustomerDAO"%>
<%@page import="dto.CustomerDTO"%>
<%@page import="dto.ItemDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.BillDTO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Thông tin hóa đơn</title>
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
                    <!-- Avatar image in top left corner -->
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

                <!--            <div class="row page-titles mx-0">
                                <div class="col p-md-0">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="javascript:void(0)">Dashboard</a></li>
                                        <li class="breadcrumb-item active"><a href="javascript:void(0)">Home</a></li>
                                    </ol>
                                </div>
                            </div>-->
                <!-- row -->

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
                                    <h2 style="text-align: center">${sessionScope.STORE.storeName}</h2>    
                                    <div id ="header_title_table">

                                        <h4 class="card-title">Thông tin hóa đơn</h4>                                            
                                    </div>
                                    <div>
                                        <input id="create_customer" type="submit" name="action" onclick="location.href = 'comfirm_create.jsp'" value="Tạo thông tin khách hàng"/>
                                    </div>

                                    <div class="table-responsive"  >

                                        <%
                                            ArrayList<BillDTO> bill = (ArrayList<BillDTO>) session.getAttribute("bill");
                                            ArrayList<BillDTO> billOver = (ArrayList<BillDTO>) session.getAttribute("BILL_OVER");
                                            ArrayList<BillDTO> billClose = (ArrayList<BillDTO>) session.getAttribute("BILL_CLOSE");
                                            Map<Integer, Integer> item_bill = (Map<Integer, Integer>) session.getAttribute("ITEM_BILL");
                                            String error = (String) request.getAttribute("message");
                                            BillDAO daoBill = new BillDAO();
                                            ItemDAO daoItem = new ItemDAO();
                                            ArrayList<ItemDTO> itemdto = daoItem.getAllItem();
                                            if (bill != null) {
                                        %>
                                        <table class="table table-striped table-bordered zero-configuration text-center">
                                            <thead>
                                                <tr>
                                                    <th>Họ Tên</th>
                                                    <th>Tên đồ vật</th>
                                                    <th>Ngày gửi</th>
                                                    <th>Số ngày gửi</th>
                                                    <th>Thông tin</th>
                                                    <th>Cảnh báo</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    for (BillDTO b : bill) {
                                                        int n = 0;
                                                        ItemDTO item = daoItem.viewItem(b.getItemID());
                                                %>
                                                <tr>
                                                    <td style="text-align: center;padding: 10px 5px;"><%= daoBill.getCustomerByItemID(b).getFullname()%></td>
                                                    <td style="text-align: center;padding: 10px 5px;"><%= daoItem.viewItem(b.getItemID()).getItemName()%></td>
                                                    <td style="text-align: center;padding: 10px 5px;"><%= b.getBillBeginDate()%></td>
                                                    <td style="text-align: center;padding: 10px 5px;"><%= b.getNumberDays()%></td>
                                                    <td style="text-align: center;padding: 10px 5px;"><button><a style="text-decoration: none;padding: 10px;" href="DetailBillOfItem?action=detail&id=<%= b.getItemID()%>">Chi tiết</a></button></td>
                                                                <% if (!item.isStatusID()) {
                                                                %>
                                                    <td style="text-align: center;padding: 10px 5px;">Đã hoàn tất
                                                        <%
                                                        } else {
                                                            if (billOver != null) {
                                                                for (BillDTO over : billOver) {
                                                                    if (over.getBillID() == b.getBillID()) {
                                                                        n++;
                                                        %>
                                                    <td style="text-align: center;padding: 10px 5px;">Đã quá hạn 
                                                        <%
                                                            if (item.isIsKeep() && item.isStatusID()) {
                                                        %>
                                                        <a style="color: red ;font: bold"href="ItemCheckController?action=update&itemID=<%= item.getItemID()%>
                                                           &itemName=<%= item.getItemName()%>
                                                           &itemPic=<%= item.getItemPic()%>
                                                           &customerID=<%= item.getCustomerID()%>
                                                           &storeID=<%= item.getStoreID()%>
                                                           &itemSendingDate=<%= item.getItemSendingDate()%>
                                                           &itemGettingDate=<%= item.getItemGettingDate()%>
                                                           &statusID=<%= item.isStatusID()%>
                                                           &isKeep=<%= item.isIsKeep()%>">> Tịch thu món đồ < </a></td>
                                                        <% }
                                                                    }
                                                                }
                                                            }
                                                            if (n == 0 && billClose != null) {
                                                                for (BillDTO close : billClose) {
                                                                    if (close.getBillID() == b.getBillID()) {
                                                                        n++;
                                                        %>
                                                        <td style="text-align: center;padding: 10px 5px;">Sắp đến hạn trong <%= daoBill.distanceDate(b.getBillBeginDate(), b.getNumberDays()) %> ngày
                                                        <a href="DetailBillOfItem?id=<%= item.getItemID()%>&action=detail" style="color: green"> [ Gọi cho khách ]</a>
                                                    </td>
                                                    <%
                                                                }
                                                            }
                                                        }
                                                        if (!item_bill.containsValue(b.getBillID())) {
                                                            n++;
                                                    %>
                                                    <td style="text-align: center;padding: 10px 5px;">Hóa đơn đã được gia hạn </td>
                                                    <%
                                                        }
                                                        if (n == 0) {
                                                    %>
                                                    <td style="text-align: center;padding: 10px 5px;">Hóa đơn đang thực hiện </td>
                                                    <%
                                                            }

                                                        }
                                                    %>
                                                </tr>
                                                <% } %>
                                            </tbody>
                                        </table>
                                        <% }
                                        %>
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
