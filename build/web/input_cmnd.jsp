<%-- 
    Document   : input_cmnd
    Created on : Jul 14, 2022, 12:46:20 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Page</title>
    </head>
    <body>
        <div>
            <div class="container-login100" style="background-image: url('https://khoanh24.com/gallery/save3?code=k-img2018k-img04k-img20k-imghinh-nen-chu-de-kinh-te-tai-chinh-dep-cho-powerpoint-anh-25ad9448d242d5_0cf1524d33806e56ccc351a09df190batype-jpg') "</div>

            <form action="InputCMND" method="POST">
                <h1 style="background-color: #664d03; font-size: 50px;margin-top:20px;border-radius: 10px;text-align: center;padding: 20px; align-content: space-around" >Xác thực Thông Tin Khách Hàng </h1></br>
                
                <div width="700" height="500" border="2" cellpaddixng="5" style="border-radius: 10px;background-color: #c0bffc;max-width: 50rem;padding:30px;margin-top: 50px"></br>
                        <p style="font-size: 25px;border-radius: 10px;font-family: inherit">Mã Khách Hàng:</p><input type="text" name="customerID" placeholder="Nhập Mã Khách Hàng" style="width: 700px;height: 30px;border-radius: 10px;border-color: #000;padding: 20px;margin-bottom: 20px"/></br>
                        
                        <input type="submit" name="action" value="Tiếp" style="color:green;width: 700px;background-color: threedshadow;border-radius: 10px ;text-align: center;background-color: threedface;margin-top: 20px;padding: 20px;font-size: 20px"/>
                    
                </div>
            </form>
        </div>
    </body>
</html>
