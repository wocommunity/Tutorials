package your.app;

import your.app.model.Author;
import er.extensions.appserver.ERXSession;

public class Session extends ERXSession {
	private static final long serialVersionUID = 1L;
  private Author _loggedAuthor;

	public Session() {
	  this._loggedAuthor = null;
	}
		
	public Author loggedAuthor() {
	  return this._loggedAuthor;
	}
	
	public void setAuthor(Author loggedAuthor) {
	  this._loggedAuthor = loggedAuthor;
	}
}
