<%--
  Created by IntelliJ IDEA.
  User: PV
  Date: 23/08/2022
  Time: 4:11 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search User By Country</title>
</head>
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

    <fieldset>
        <legend>Product</legend>
        <table>
            <tr>
                <td>Name</td>
                <td>
                    ${requestScope['user'].name}
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

        </table>
    </fieldset>
</form>
</body>
</html>
