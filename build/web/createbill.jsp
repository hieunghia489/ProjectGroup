<%-- 
    Document   : createbill
    Created on : Jun 23, 2022, 12:25:04 AM
    Author     : Admin
--%>

<%@page import="dto.BillError"%>
<%@page import="dto.StaffDTO"%>
<%@page import="dto.ItemDTO"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo mới hóa đơn</title>
    </head>
    <body>
        <div>
            <%

                StaffDTO staff = (StaffDTO) session.getAttribute("STAFF");
                BillError billError = (BillError) session.getAttribute("BILL_ERROR");
                if (billError == null) {
                    billError = new BillError();
                }
            %>
            <div class="container-login100" style="background-image: url('https://khoanh24.com/gallery/save3?code=k-img2018k-img04k-img20k-imghinh-nen-chu-de-kinh-te-tai-chinh-dep-cho-powerpoint-anh-25ad9448d242d5_0cf1524d33806e56ccc351a09df190batype-jpg') ">

                <form action="CreateBillController" method="POST">
                    <h1 style="background-color: #664d03;align-items: flex-start; font-size: 40px;margin-top:30px;border-radius: 10px;text-align: center;padding: 20px" >Thông Chi Tiết Cầm Đồ </h1></br>

                    <div width="700" height="500" border="2" cellpaddixng="5" style="border-radius: 10px;background-color: #c0bffc;max-width: 50rem;padding:20px;margin: 10px"></br>

                        <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Số Tiền Cầm:</p><input type="text" name="pawnMoney" placeholder="Nhập Số Tiền Cầm" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px">
                        <p style="color: red"><%= billError.getBillPawnMoneyError()%></p>
                        <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Số Ngày Cầm:</p><input type="text" name="numberDays" placeholder="Nhập Số Tiền Cầm" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px">
                        <p style="color: red"><%= billError.getBillNumberDaysError()%></p>

                        <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Lãi Suất:</p><input type="text" name="interestRate" placeholder="Nhập Số Lãi Suất" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"/>
                        <p style="color: red"><%= billError.getBillInterestRateError()%></p>
                        <%--<p style="font-size: 20px;border-radius: 10px;font-family: inherit">Ngày tạo Bill:</p><input type="date" name="billBeginDate"  style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"/>
                        <p><%= billError.getBillBeginDateError()%></p>--%>
                        <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Mã Nhân Viên:</p><input type="text" name="staffID" value="<%= staff.getStaffID()%>" readonly="" placeholder="Nhập Mã Nhân Viên" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"/>
                        <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Tiền trả:</p><input type="text" name="returnMoney" placeholder="Nhập Tiền trả" value="0" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"/></br>
                        <p style="color: red"><%= billError.getBillReturnMoneyError()%></p>
                        <div style="text-align:  center">
                            <input type="submit" name="action" value="Tạo" style="color:green; width: 345px;background-color: beige;border-radius: 10px ;background-color: aqua;margin-top: 20px;padding: 20px;font-size: 20px"/>
                        </div>    

                        <h3 style="color: red"><% if (!billError.getWrongFormat().isEmpty()) {
                                out.print("Cảnh báo : " + billError.getWrongFormat());
                            }%></h3>
                        </
                    </div>
                </form>
            </div>
    </body>
</html>
