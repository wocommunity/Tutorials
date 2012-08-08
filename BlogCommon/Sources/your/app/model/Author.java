package your.app.model;

import org.apache.log4j.Logger;

import com.webobjects.eocontrol.EOEditingContext;

public class Author extends _Author {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(Author.class);
	
	public String fullName() {
	  return this.firstName() + " " + this.lastName();
	}

  public static Author validateLogin(EOEditingContext newEditingContext, String email) {
    return Author.fetchAuthor(newEditingContext, Author.EMAIL.eq(email));
  }
}
