package by.study.news.controller.impl.user;
import java.io.IOException;
import by.study.news.bean.User;
import by.study.news.controller.Command;
import by.study.news.service.UserService;
import by.study.news.service.exception.ServiceException;
import by.study.news.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignIn implements Command {

	private UserService userService = ServiceFactory.getInstance().getUserService();

	private static final String USER_ATTRIBUTE = "user";
	private static final String USER_ID_ATTRIBUTE = "userId";
	private static final String USER_NAME_ATTRIBUTE = "userName";
	private static final String USER_ROLE_ATTRIBUTE = "userRole";
	
	private static final String LOGIN_PARAM = "login";
	private static final String PASSWORD_PARAM = "password";
	
	private static final String ACTIVE_STATUS = "active";
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			User user = userService.singIn(request.getParameter(LOGIN_PARAM), request.getParameter(PASSWORD_PARAM));

			HttpSession session = request.getSession(true);
			session.setAttribute(USER_ATTRIBUTE, ACTIVE_STATUS);
			session.setAttribute(USER_ID_ATTRIBUTE, user.getId());
			session.setAttribute(USER_NAME_ATTRIBUTE, user.getName());
			session.setAttribute(USER_ROLE_ATTRIBUTE, user.getRole());
			
			response.sendRedirect("controller?command=go_to_base_page");
			
		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect("controller?command=go_to_base_page&signingInError=signing in error");
		}

	}
}
