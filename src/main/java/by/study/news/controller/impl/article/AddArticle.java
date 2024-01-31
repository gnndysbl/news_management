package by.study.news.controller.impl.article;

import java.io.IOException;

import jakarta.servlet.http.HttpSession;
import by.study.news.bean.Article;
import by.study.news.bean.ArticleStatus;
import by.study.news.controller.Command;
import by.study.news.service.NewsService;
import by.study.news.service.exception.ServiceException;
import by.study.news.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddArticle implements Command {

	private NewsService newsService = ServiceFactory.getInstance().getNewsService();

	private static final String TITLE_PARAM = "title";
	private static final String BRIEF_PARAM = "brief";
	private static final String CONTENT_PARAM = "content";
	private static final String USER_ID_ATTRIBUTE = "userId";

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		int id = (Integer) session.getAttribute(USER_ID_ATTRIBUTE);

		Article article = new Article(request.getParameter(TITLE_PARAM), request.getParameter(BRIEF_PARAM),
				request.getParameter(CONTENT_PARAM), ArticleStatus.ACTIVE, id);

		try {
			newsService.addArticle(article);

			response.sendRedirect("controller?command=go_to_base_page");

		} catch (ServiceException e) {

			response.sendRedirect("controller?command=go_to_error_page");
			e.getMessage();
			e.printStackTrace();

		}
	}
}
