<%-- 
    Document   : customer_update
    Created on : Jun 28, 2022, 8:17:52 PM
    Author     : Admin
--%>

<%@page import="dto.CustomerError"%>
<%@page import="dto.CustomerDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật thông tin khách hàng</title>
        <link rel="stylesheet" type="text/css" href="css/main.css" />
    </head>
    <body>

        <c:set var="cus" value="${sessionScope.INFORCUSTOMER}"/>
        <c:set var="cusError" value="${requestScope.ErrorCus}"/>
        <div>
            <div class="container-login100" style="background-image: url('images/bg-01.jpg') ">  

                <div width="700" height="500" border="2" cellpaddixng="5" style="border-radius: 10px;background-color: #ff9999;max-width: 50rem;padding:20px;margin: 10px"></br>
                    <form action="UpdateCustomer">
                        <h1 style="background-color: #ff6699;align-items: flex-start; font-size: 40px;margin-top:30px;border-radius: 10px;text-align: center;padding: 20px" >Cập Nhật Thông Tin Khách Hàng </h1></br>
                        <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Mã Khách Hàng:</p><input type="text" name="customerID" placeholder="Mã Khách Hàng" value="${cus.customerID}" style="width: 700px;height: 30px;border-radius: 10px;border-color: #000;padding: 20px;background-color: #B0BED9" readonly="true"/></br>
                        <font style="color: red" >${cusError.customerIDError}</font>
                        <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Họ Và Tên:</p><input type="text" name="fullName" value="${cus.fullname}" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px" readonly="true"/></br>
                        <font style="color: red" >${cusError.customerNameError}</font>
                        <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Số Điện Thoại:</p><input type="text" name="phoneNumber" placeholder="Nhập Số Điện Thoại" value="0${cus.phoneNumber}" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"></br>
                        <font style="color: red" >${cusError.customerSdtError}</font>
                        <p style="font-size: 20px;border-radius: 10px;font-family: inherit">Địa Chỉ:</p><input type="text" name="address"  value="${cus.address}" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"></br>
                        <font style="color: red"> ${cusError.customerAddressError}</font>
                        <div style="text-align: center;">
                            <input type="submit" name="action" value="Cập Nhật" style="padding: 10px; margin-top: 15px;">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

