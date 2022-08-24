<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<style>

    table,tr, th,td {
        padding: 10px;
        border: 2px solid gray;
        border-collapse:collapse
    }
    .csw-btn-button {
        cursor: pointer;
        font-size: 16px;
        padding: 10px 35px;
        color: #ffffff !important;
        border-radius: 5px;
        background: #e79a20;
        border: 1px solid #c98925;
        transition: 0.4s;
    }
    .csw-btn-button:hover {
        background: #292929;
    }
    a{
        color: black;
        text-decoration: none;
    }
</style>
<body>
<center>
    <h1>User Management</h1>
    <h2><a href="/users?action=create" class="csw-btn-button" rel="nofollow">Creat New User</a></h2>

    <br>
<%--    <h2>--%>
<%--        <a href="/users?action=find" class="csw-btn-button" rel="nofollow" >Find User</a>--%>
<%--    </h2>--%>

    <form action="/users?action=find" method="post">
        <input type="text" name="search" id="search" placeholder="country">
        <input type="submit" value="Search">
    </form>
    <br>
    <h2>
       <a href="/users?action=sort" class="csw-btn-button" rel="nofollow" >Display User List By Name</a>
         <a href="/users" class="csw-btn-button" rel="nofollow" >Display User List</a>
    </h2>



</center>
<div align="center">

    <table >
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.country}"/></td>
                <td>
                    <a  href="/users?action=edit&id=${user.id}">Edit</a>
                    <a href="/users?action=delete&id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>