package by.study.news.controller.impl.common;

import java.io.IOException;
import java.util.List;
import by.study.news.bean.Article;
import by.study.news.controller.Command;
import by.study.news.service.exception.ServiceException;
import by.study.news.service.NewsService;
import by.study.news.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ShowPage implements Command {
	
	private static final String NEWS_PER_PAGE_ATTRIBUTE = "newsPerPage";
	private static final String EDIT_ARTICLE_ATTRIBUTE = "editArticle";
	private static final String VIEW_ARTICLE_ATTRIBUTE = "viewArticle";
	private static final String ADD_ARTICLE_ATTRIBUTE = "addArticle";
	private static final String TARGETLINK_ATTRIBUTE = "targetLink";
	private static final String NEWS_ATTRIBUTE = "news";
	private static final String PAGE_ATTRIBUTE = "page";

	private NewsService newsService = ServiceFactory.getInstance().getNewsService();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Article> newsPaged;

		HttpSession session = request.getSession(true);

		try {
			newsPaged = newsService.getNewsPaged((Integer) session.getAttribute(NEWS_PER_PAGE_ATTRIBUTE),
					(Integer) session.getAttribute(PAGE_ATTRIBUTE));

			request.setAttribute(NEWS_ATTRIBUTE, newsPaged);

			session.setAttribute(TARGETLINK_ATTRIBUTE, "controller?command=show_page");
			session.setAttribute(VIEW_ARTICLE_ATTRIBUTE, null);
			session.setAttribute(ADD_ARTICLE_ATTRIBUTE, null);
			session.setAttribute(EDIT_ARTICLE_ATTRIBUTE, null);

			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_error_page");
			e.printStackTrace();
		}

	}
}
