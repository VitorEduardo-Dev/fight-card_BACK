package controller.userControllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

@WebServlet("/postuser")
public class UserInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao = DaoFactory.createUserDao();
	
	public UserInsertController() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		String jsonOut = null;
		User user = new User();
		ObjectMapper mapper = new ObjectMapper();
		
		user = mapper.readValue(request.getReader(), User.class);
		
		if(user != null) {
			dao.insert(user);
			jsonOut = new Gson().toJson(user.getId());
			out.write("ID new: " + jsonOut + " is insert.");
		} else {
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Insert not found!");
		}
	}
}
