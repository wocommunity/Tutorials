package your.app.rest.controllers;

import your.app.model.BlogEntry;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXKeyFilter;

public class BlogEntryController extends BaseRestController {

  public BlogEntryController(WORequest request) {
    super(request);
  }

  @Override
  public WOActionResults createAction() throws Throwable {
    BlogEntry entry = create(filter());
    editingContext().saveChanges();
    return response(entry, filter());
  }

  @Override
  public WOActionResults indexAction() throws Throwable {
    NSArray<BlogEntry> entries = BlogEntry.fetchAllBlogEntries(editingContext());
    return response(entries, filter());
  }
  
  protected ERXKeyFilter filter() {
    ERXKeyFilter personFilter = ERXKeyFilter.filterWithAttributes();
    personFilter.setAnonymousUpdateEnabled(true);

    ERXKeyFilter filter = ERXKeyFilter.filterWithAttributes();
    filter.include(BlogEntry.AUTHOR, personFilter);
    filter.setUnknownKeyIgnored(true);
    
    return filter;
  }
  
  @Override
  protected boolean isAutomaticHtmlRoutingEnabled() {
    return true;
  }

}
