package controller.userControllers;

import com.google.gson.Gson; 

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getuser")
public class UserFindByIdController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao dao = DaoFactory.createUserDao();
    
    public UserFindByIdController() {
    	super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        PrintWriter out = response.getWriter();
        String jsonOut = null;

        if(idParam != null && !idParam.isEmpty()) {
        	try {
	        	Integer id = Integer.parseInt(idParam);
	            User user = dao.findById(id);
	            
	            if(user != null) {
	            	jsonOut = new Gson().toJson(user);
	            } else {
	            	response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found!");
	            } 
        	} catch (NumberFormatException e) {
        		e.printStackTrace();
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID not number valid!");
			}
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id mandatory!");
        }
        
        out.write(jsonOut);
    	
    }
   
}
