package controller.userControllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

@WebServlet("/getall")
public class UserFindAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao = DaoFactory.createUserDao();
	
	public UserFindAllController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		String jsonOut = null;
		List<User> userList = dao.findAll();
		
		if(!userList.isEmpty() && userList != null) {
			try {
				for(User user : userList) {
					if(user != null) {
						jsonOut = new Gson().toJson(user);
						out.write(jsonOut);
					} else {
						response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "List not found!");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "List users not found or not exists!");
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "List not uses!");
		}
	}
}
