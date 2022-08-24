package com.example.usermanager.controller;

import com.example.usermanager.dao.IUserDAO;
import com.example.usermanager.dao.UserDAO;
import com.example.usermanager.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "UserServlet",urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try{
            switch (action){
                case "create":
                    showCreate(request,response);
                    break;
                case "edit":
                    showEdit(request,response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "sort":
                    sortListUserByName(request,response);
                    break;
                case "find":
                    findByCountry(request,response);
                    break;
                default:
                    listUser(request,response);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    createUser(request, response);
                    break;
                case "edit":
                    editUser(request, response);
                    break;
                case "sort":
                    sortListUserByName(request,response);
                    break;
                case "find":
                    findByCountry(request,response);
                    break;
                default:
                    listUser(request,response);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void  findByCountry(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String country = request.getParameter("country");
        List<User> user = this.userDAO.selectCountry(country);
        request.setAttribute("user",user);
        request.setAttribute("message","User information");

        RequestDispatcher dispatcher = request.getRequestDispatcher("user/find.jsp");
        dispatcher.forward(request,response);
    }

    private void listUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser  = userDAO.selectAllUsers();
        request.setAttribute("listUser",listUser );
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request,response);
    }

    private void sortListUserByName(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = userDAO.selectUserByName();
        request.setAttribute("listUser",listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showCreate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request,response);
    }

    private void createUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, email, country);

        userDAO.insertUser(newUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request,response);
    }

    private void showEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = this.userDAO.selectUser(id);
        RequestDispatcher dispatcher;

        if (user == null){
            dispatcher = request.getRequestDispatcher("user/error-404.jsp");
        }else {
            request.setAttribute("user",user);
            dispatcher = request.getRequestDispatcher("user/edit.jsp");
        }
        dispatcher.forward(request,response);
    }

    private void editUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User user = new User(id,name,email,country);
        userDAO.updateUser(user);

        request.setAttribute("message","Thanh cong");
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void deleteUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);

        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }


}
