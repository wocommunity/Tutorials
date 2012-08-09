package your.app.components;

import your.app.Session;
import your.app.model.Author;
import your.app.model.BlogEntry;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;

import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXEOControlUtilities;

public class EditBlogEntry extends ERXComponent {

  public EditBlogEntry(WOContext context) {
    super(context);
  }

  private BlogEntry _blogEntry;

  public BlogEntry blogEntry() {
    return this._blogEntry;
  }

  public void setBlogEntry(BlogEntry blogEntry) {
    if (blogEntry == null) {
      this._blogEntry = ERXEOControlUtilities.createAndInsertObject(editingContext(), BlogEntry.class);
      Author localUser = ERXEOControlUtilities.localInstanceOfObject(editingContext(), session().loggedAuthor());
      this._blogEntry.setAuthorRelationship(localUser);
    } else {
      this._blogEntry = ERXEOControlUtilities.localInstanceOfObject(editingContext(), blogEntry);
    }
  }

  private EOEditingContext _ec;

  public EOEditingContext editingContext() {
    if (_ec == null) {
      _ec = ERXEC.newEditingContext();
    }
    return _ec;
  }

  @Override
  public Session session() {
    return ((Session)super.session());
  }

  public WOActionResults save() {
    editingContext().saveChanges();
    return pageWithName(AdminMainPage.class);
  }
}