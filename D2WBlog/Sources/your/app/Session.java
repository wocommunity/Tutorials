package your.app;

import your.app.model.Author;
import er.extensions.appserver.ERXSession;
import er.extensions.foundation.ERXThreadStorage;

public class Session extends ERXSession {
  private static final long serialVersionUID = 1L;
  private MainNavigationController _navController;

  public Session() {
  }

  public MainNavigationController navController() {
    if (_navController == null) {
      _navController = new MainNavigationController(this);
    }
    return _navController;
  }

  private Author _author;

  public Author author() {
    return _author;
  }

  public void setAuthor(Author author) {
    _author = author;
    ERXThreadStorage.takeValueForKey(author(), "author");
  }


  public String navigationRootChoice() { 
    Author author = (Author) author();
    if(author != null ) {
      return "home";
    }
    return "none";
  }

}
