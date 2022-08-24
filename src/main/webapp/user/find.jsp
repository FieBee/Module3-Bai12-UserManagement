<%--
  Created by IntelliJ IDEA.
  User: PV
  Date: 23/08/2022
  Time: 4:11 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Search User By Country</title>
</head>
<style>
    *{
        border-collapse:collapse;
        justify-content: center;
        text-align: center;
    }
    fieldset ,table{
        margin: auto;
    }
    tr{

        width :500px;
    }
    td{
        width :200px;
    }
</style>
<body>
<h1>Search User By Country</h1>
<br>
<h3>
    <a href="/users"><button>Back To User List </button></a>
</h3>
<form action="" method="post">

    <br>
    <input type="text" name="country" id="name" placeholder="country">
    <input type="submit" value="Search">
    <br><br><br>


        <fieldset style="width: 500px">

            <legend style="text-align: center">Product</legend>
<c:forEach var="user" items="${user}">
            <table border="1px solid black" >

                <tr >
                    <td>Name</td>
                    <td>
                            ${user.name}
                    </td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>
                            ${user.email}
                    </td>
                </tr>
                <tr>
                    <td>Country</td>
                    <td>
                            ${user.country}
                    </td>
                </tr>
                     <br>

            </table>
            </c:forEach>
        </fieldset>
        <br>


</form>
</body>
</html>
