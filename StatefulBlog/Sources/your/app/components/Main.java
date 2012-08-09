package your.app.components;

import your.app.model.BlogEntry;

import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eocontrol.EOEditingContext;

import er.extensions.batching.ERXBatchingDisplayGroup;
import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;

public class Main extends ERXComponent {
  
  private EOEditingContext _ec;
  private BlogEntry _blogEntryItem;
  private ERXBatchingDisplayGroup<BlogEntry> _dg;

  public Main(WOContext context) {
    super(context);
    EODatabaseDataSource dataSource = new EODatabaseDataSource(editingContext(), BlogEntry.ENTITY_NAME);
    _dg = new ERXBatchingDisplayGroup<BlogEntry>();
    _dg.setNumberOfObjectsPerBatch(20);
    _dg.setDataSource(dataSource);
    _dg.setObjectArray(BlogEntry.fetchAllBlogEntries(editingContext(), BlogEntry.LAST_MODIFIED.descs()));
  }

  public ERXBatchingDisplayGroup<BlogEntry> displayGroup() {
    return this._dg;
  }
  
  private EOEditingContext editingContext() {
    if (_ec == null) {
      _ec = ERXEC.newEditingContext();
    }
    return _ec;
  }
  
  public void setBlogEntryItem(BlogEntry blogEntryItem) {
    this._blogEntryItem = blogEntryItem;
  }
  
  public BlogEntry blogEntryItem() {
    return this._blogEntryItem;
  }
  
}
