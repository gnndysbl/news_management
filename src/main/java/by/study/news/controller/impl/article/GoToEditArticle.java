package by.study.news.controller.impl.article;

import java.io.IOException;
import by.study.news.bean.Article;
import by.study.news.controller.Command;
import by.study.news.service.exception.ServiceException;
import by.study.news.service.NewsService;
import by.study.news.service.factory.ServiceFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToEditArticle implements Command {

	private NewsService newsService = ServiceFactory.getInstance().getNewsService();

	private static final String ID_PARAM = "id";

	private static final String EDIT_ARTICLE_ATTRIBUTE = "editArticle";
	private static final String VIEW_ARTICLE_ATTRIBUTE = "viewArticle";
	private static final String ADD_ARTICLE_ATTRIBUTE = "addArticle";
	private static final String TARGETLINK_ATTRIBUTE = "targetLink";
	private static final String NEWS_ATTRIBUTE = "news";

	private static final String ACTIVE_STATUS = "active";

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		int id = Integer.parseInt(request.getParameter(ID_PARAM));

		session.setAttribute(VIEW_ARTICLE_ATTRIBUTE, null);
		session.setAttribute(ADD_ARTICLE_ATTRIBUTE, null);
		session.setAttribute(EDIT_ARTICLE_ATTRIBUTE, ACTIVE_STATUS);
		session.setAttribute(TARGETLINK_ATTRIBUTE,
				"controller?command=go_to_edit_article&id=" + request.getParameter(ID_PARAM));

		Article article = null;
		try {
			article = newsService.getArticle(id);
		} catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_error_page");
			e.printStackTrace();
		}

		request.setAttribute(NEWS_ATTRIBUTE, article);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp");
		requestDispatcher.forward(request, response);
	}

}
