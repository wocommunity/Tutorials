package your.app;

import java.util.NoSuchElementException;

import your.app.components.Main;
import your.app.model.Author;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WORequest;

import er.directtoweb.ERD2WDirectAction;
import er.extensions.eof.ERXEC;
import er.extensions.foundation.ERXStringUtilities;


public class DirectAction extends ERD2WDirectAction {
  public DirectAction(WORequest request) {
    super(request);
  }

  @Override
  public WOActionResults defaultAction() {
    return pageWithName(Main.class.getName());
  }

  /**
   * Checks if a page configuration is allowed to render.
   * Provide a more intelligent access scheme as the default just returns false. And
   * be sure to read the javadoc to the super class.
   * @param pageConfiguration
   * @return
   */
  protected boolean allowPageConfiguration(String pageConfiguration) {
    return false;
  }

  public WOActionResults loginAction() {
    WOComponent nextPage = null;
    String email = request().stringFormValueForKey("username");
    String errorMessage = null;
    
    try {
      Author user = Author.fetchAuthor(ERXEC.newEditingContext(), Author.EMAIL.eq(email));
      ((Session) session()).setAuthor(user);
      nextPage = ((Session) session()).navController().homeAction();
    }
    catch (NoSuchElementException e) {
      errorMessage = "No user found for that combination of username and password.";
    }
    catch (Exception e) {
      // TODO: handle exception
    }
    if (!ERXStringUtilities.stringIsNullOrEmpty(errorMessage)) {
      nextPage = pageWithName(Main.class);
      nextPage.takeValueForKey(errorMessage, "errorMessage");
      nextPage.takeValueForKey(email, "username");
    }
    return nextPage;
  }

}
