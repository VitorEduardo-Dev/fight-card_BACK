package model.dao.controller;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.entities.User;

import java.io.IOException;

@WebServlet("api/user")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao dao;

    public UserController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        User user = dao.findById(request.getIntHeader("id"));

        if(user != null) {
            User userGet = new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
            String jsonOut = new Gson().toJson(userGet);
            response.getWriter().write(jsonOut);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id obrigatório!");
        }
    }
}
