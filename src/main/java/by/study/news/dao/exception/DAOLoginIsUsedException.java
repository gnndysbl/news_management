package by.study.news.dao.exception;

public class DAOLoginIsUsedException extends Exception {
	private static final long serialVersionUID = 1L;

	public DAOLoginIsUsedException() {

		super();
	}

	public DAOLoginIsUsedException(String message) {
		super(message);
	}

	public DAOLoginIsUsedException(Exception e) {
		super(e);
	}

	public DAOLoginIsUsedException(String message, Exception e) {
		super(message, e);
	}
}