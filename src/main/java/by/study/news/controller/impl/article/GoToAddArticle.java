package by.study.news.controller.impl.article;

import java.io.IOException;
import by.study.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToAddArticle implements Command {

	private static final String EDIT_ARTICLE_ATTRIBUTE = "editArticle";
	private static final String VIEW_ARTICLE_ATTRIBUTE = "viewArticle";
	private static final String ADD_ARTICLE_ATTRIBUTE = "addArticle";
	private static final String TARGETLINK_ATTRIBUTE = "targetLink";
	
	private static final String ACTIVE_STATUS = "active";
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session = request.getSession(true);
			session.setAttribute(VIEW_ARTICLE_ATTRIBUTE, null);
			session.setAttribute(EDIT_ARTICLE_ATTRIBUTE, null);
			session.setAttribute(ADD_ARTICLE_ATTRIBUTE, ACTIVE_STATUS);
			session.setAttribute(TARGETLINK_ATTRIBUTE, "controller?command=go_to_add_article");

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp");
			requestDispatcher.forward(request, response);

	}

}
