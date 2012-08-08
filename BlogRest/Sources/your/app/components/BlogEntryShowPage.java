package your.app.components;

import your.app.model.BlogEntry;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

import er.rest.routes.ERXRouteParameter;
import er.rest.routes.IERXRouteComponent;

public class BlogEntryShowPage extends WOComponent implements IERXRouteComponent {
    public BlogEntryShowPage(WOContext context) {
        super(context);
    }
    
    private BlogEntry blogEntry;
    
    @ERXRouteParameter
    public void setBlogEntry(BlogEntry blogEntryFromController) {
      this.blogEntry = blogEntryFromController;
    }
    
    public BlogEntry blogEntry() {
      return this.blogEntry;
    }
}