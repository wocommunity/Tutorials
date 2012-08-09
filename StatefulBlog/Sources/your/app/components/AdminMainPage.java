package your.app.components;

import your.app.Session;
import your.app.model.Author;
import your.app.model.BlogEntry;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eocontrol.EOEditingContext;

import er.extensions.batching.ERXBatchingDisplayGroup;
import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;

public class AdminMainPage extends ERXComponent {
  
  private ERXBatchingDisplayGroup<BlogEntry> _dg;

  public AdminMainPage(WOContext context) {
    super(context);
    EODatabaseDataSource dataSource = new EODatabaseDataSource(editingContext(), BlogEntry.ENTITY_NAME);
    _dg = new ERXBatchingDisplayGroup<BlogEntry>();
    _dg.setNumberOfObjectsPerBatch(20);
    _dg.setDataSource(dataSource);
    _dg.setObjectArray(BlogEntry.fetchBlogEntries(editingContext(), BlogEntry.AUTHOR.eq(session().loggedAuthor()), BlogEntry.LAST_MODIFIED.descs()));
  }
  
  public ERXBatchingDisplayGroup<BlogEntry> displayGroup() {
    return this._dg;
  }
  
  private String _emailAddress;
  
  public String emailAddress() {
    return this._emailAddress;
  }
  
  public void setEmailAddress(String emailAddress) {
    this._emailAddress = emailAddress;
  }
  
  private BlogEntry _blogEntryItem;

  public void setBlogEntryItem(BlogEntry blogEntryItem) {
    this._blogEntryItem = blogEntryItem;
  }
  
  public BlogEntry blogEntryItem() {
    return this._blogEntryItem;
  }
  
  @Override
  public Session session() {
    return ((Session)super.session());
  }
  
  public boolean isLogged() {
    return ((session()).loggedAuthor() == null) ? false: true;
  }
  
  private EOEditingContext _ec;
  
  public EOEditingContext editingContext() {
    if (_ec == null) {
      _ec = ERXEC.newEditingContext();
    }
    return _ec;
  }
  
  private String _errorMessage = null;
  
  public String errorMessage() {
    return this._errorMessage;
  }
  
  public WOActionResults login() {
    Author loggedAuthor = Author.validateLogin(editingContext(), _emailAddress);
    if (loggedAuthor != null) {
      session().setAuthor(loggedAuthor);
    } else {
      _errorMessage = "Invalid email address";
    }
    return null;
  }
  
  public WOActionResults editBlogEntry() {
    EditBlogEntry nextPage = pageWithName(EditBlogEntry.class);
    nextPage.setBlogEntry(_blogEntryItem);
    return nextPage;
  }
  
  public WOActionResults createBlogEntry() {
    EditBlogEntry nextPage = pageWithName(EditBlogEntry.class);
    nextPage.setBlogEntry(null);
    return nextPage;    
  }
  
}