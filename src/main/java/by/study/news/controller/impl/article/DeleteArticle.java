package by.study.news.controller.impl.article;

import java.io.IOException;
import by.study.news.controller.Command;
import by.study.news.service.NewsService;
import by.study.news.service.exception.ServiceException;
import by.study.news.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteArticle implements Command {

	private NewsService newsService = ServiceFactory.getInstance().getNewsService();
	
	private static final String ID_PARAM = "id";

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameterValues(ID_PARAM) == null) {

			request.getRequestDispatcher("controller?command=go_to_base_page").forward(request, response);

		} else {
			String selectedId = request.getParameter(ID_PARAM);

			try {
				newsService.deleteArticle(Integer.parseInt(selectedId));
			} catch (ServiceException e) {
				
				response.sendRedirect("controller?command=go_to_error_page");
				e.printStackTrace();

			}
			
			request.getRequestDispatcher("controller?command=go_to_base_page").forward(request, response);

		}
	}
}