<%-- 
    Document   : createitem
    Created on : Jun 23, 2022, 12:27:01 AM
    Author     : Admin
--%>


<%@page import="dto.ItemError"%>
<%@page import="dto.CustomerDTO"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo mới món đồ</title>

        <script>
            var loadFile = function (event) {
                var reader = new FileReader();
                reader.onload = function () {
                    var output = document.getElementById('output');
                    output.src = reader.result;
                };
                reader.readAsDataURL(event.target.files[0]);
            };// code display image upload
        </script>
        <style>
            #imageOut{
                margin: auto;
                position: absolute;
                float: left;
                width: 150px;
                height: 80px;
                padding-bottom: 50px;
            }
            #output{
                width: 150px;
                height: 80px;            
            }
        </style>
    </head>
    <body>
        <%
            CustomerDTO cus = (CustomerDTO) session.getAttribute("CUSTOMER");
            ItemError itemError = (ItemError) request.getAttribute("ITEM_ERROR");
            if (itemError == null) {
                itemError = new ItemError();
            }
        %>
        <div>
            <div class="container-login100" style="background-image: url('https://khoanh24.com/gallery/save3?code=k-img2018k-img04k-img20k-imghinh-nen-chu-de-kinh-te-tai-chinh-dep-cho-powerpoint-anh-25ad9448d242d5_0cf1524d33806e56ccc351a09df190batype-jpg') ">

                <form action="CreateItemController" method="post" enctype="multipart/form-data">
                    <h1 style="background-color: #664d03;align-items: flex-start; font-size: 40px;margin-top:10px;border-radius: 10px;text-align: center;padding: 20px" >Thông Tin Vật Phẩm </h1></br>

                    <div width="700" height="500" border="2" cellpaddixng="5" style="border-radius: 10px;background-color: #c0bffc;max-width: 50rem;padding:20px;margin: 10px"></br>

                        <p style="font-size: 20px;border-radius: 10px;">Tên Món Đồ:</p><input type="text" name="itemName" placeholder="Nhập Tên Món Đồ" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"/></br>
                        <font style="color: red"> <%= itemError.getItemNameError()%></font>
                        <p style="font-size: 20px;border-radius: 10px;">Hình ảnh:</p><input type="file" name="itemPic" accept="image" onchange="loadFile(event)" style="border: 1px solid #ccc;
                                                                                            display: inline-block;
                                                                                            padding: 6px 12px;
                                                                                            cursor: pointer;width: 700px"/></br>
                        <div id="imageOut"><img id="output" /></div></br>
                        <font style="color: red">    <%= itemError.getItemPicError()%></font>
                        <p style="font-size: 20px;border-radius: 10px; margin-top: 50px">Mã Khách Hàng:</p><input type="text" name="customerID" value="<%= cus.getCustomerID()%>" placeholder="Mã Khách Hàng" readonly="" style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"></br>


                        <p style="font-size: 20px;border-radius: 10px;">Ngày Cầm:</p><input type="date" name="itemSendingDate"  style="width: 700px;height: 30px;border-radius: 10px;padding: 20px"/></br>
                        <font style="color: red">   <%= itemError.getItemSendingDateError()%></font>

                        <input type="submit" name="action" value="Next" style="color:green;width: 700px;background-color: beige;border-radius: 10px ;text-align: center;background-color: aqua;margin-top: 20px;padding: 20px;font-size: 20px"/>

                    </div>
                </form>
            </div>
    </body>
</html>
