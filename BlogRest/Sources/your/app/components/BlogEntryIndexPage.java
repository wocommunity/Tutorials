package your.app.components;

import your.app.model.BlogEntry;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXEC;
import er.rest.routes.IERXRouteComponent;

public class BlogEntryIndexPage extends WOComponent implements IERXRouteComponent {
    public BlogEntryIndexPage(WOContext context) {
        super(context);
    }
    
    public NSArray<BlogEntry> entries() {
      EOEditingContext ec = ERXEC.newEditingContext();
      return BlogEntry.fetchAllBlogEntries(ec, BlogEntry.CREATION_DATE.descs());
    }
    
    private BlogEntry entryItem;
    
    public BlogEntry entryItem() {
      return entryItem;
    }
    
    public void setEntryItem(BlogEntry entryItem) {
      this.entryItem = entryItem;
    }
}