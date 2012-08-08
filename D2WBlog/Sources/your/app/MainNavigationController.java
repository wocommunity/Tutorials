package your.app;

import your.app.model.Author;
import your.app.model.BlogEntry;

import com.webobjects.appserver.WOComponent;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.ErrorPageInterface;
import com.webobjects.directtoweb.ListPageInterface;
import com.webobjects.directtoweb.QueryPageInterface;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eocontrol.EODataSource;

public class MainNavigationController {

  private Session _session;

  public MainNavigationController(Session s) {
    super();
    _session = s;
  }

  // NAV ACTIONS

  public WOComponent homeAction() {
    return listPageForEntityName(BlogEntry.ENTITY_NAME);
  }
  
  public WOComponent listPostsAction() {
    return listPageForEntityName(BlogEntry.ENTITY_NAME);    
  }
  
  public WOComponent listAuthorsAction() {
    return listPageForEntityName(Author.ENTITY_NAME);    
  }
    
  public WOComponent createPostAction() {
    return newObjectForEntityName(BlogEntry.ENTITY_NAME);
  }
  
  public WOComponent createAuthorAction() {
    return newObjectForEntityName(Author.ENTITY_NAME);
  }
  
  public WOComponent searchAuthorsAction() {
    return queryPageForEntityName(Author.ENTITY_NAME);
  }
  
  public WOComponent searchPostsAction() {
    return queryPageForEntityName(BlogEntry.ENTITY_NAME);
  }
  
  // GENERIC ACTIONS

  public WOComponent listPageForEntityName(String entityName) {
    ListPageInterface listPage = D2W.factory().listPageForEntityNamed(entityName, session());
    EODataSource dataSource = new EODatabaseDataSource(session().defaultEditingContext(), entityName);
    listPage.setDataSource(dataSource);
    return (WOComponent) listPage;
  }
  
  public WOComponent queryPageForEntityName(String entityName) {
    QueryPageInterface newQueryPage = D2W.factory().queryPageForEntityNamed(entityName, session());
    return (WOComponent) newQueryPage;
  }

  public WOComponent newObjectForEntityName(String entityName) {
    WOComponent nextPage = null;
    try {
      EditPageInterface epi = D2W.factory().editPageForNewObjectWithEntityNamed(entityName, session());
      epi.setNextPage(session().context().page());
      nextPage = (WOComponent) epi;
    } catch (IllegalArgumentException e) {
      ErrorPageInterface epf = D2W.factory().errorPage(session());
      epf.setMessage(e.toString());
      epf.setNextPage(session().context().page());
      nextPage = (WOComponent) epf;
    }
    return nextPage;
  }

  // ACCESSORS

  public Session session() {
    return _session;
  }

  public void setSession(Session s) {
    _session = s;
  }
}
