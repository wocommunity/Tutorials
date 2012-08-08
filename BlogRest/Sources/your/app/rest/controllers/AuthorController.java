package your.app.rest.controllers;

import your.app.model.Author;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXKeyFilter;

public class AuthorController extends BaseRestController {

  public AuthorController(WORequest request) {
    super(request);
  }

  @Override
  public WOActionResults createAction() throws Throwable {
    Author author = create(filter());
    editingContext().saveChanges();
    return response(author, filter());
  }

  @Override
  public WOActionResults indexAction() throws Throwable {
    NSArray<Author> entries = Author.fetchAllAuthors(editingContext());
    return response(entries, filter());
  }
  
  protected ERXKeyFilter filter() {
    ERXKeyFilter filter = ERXKeyFilter.filterWithAttributes();
    return filter;
  }
  
  @Override
  protected boolean isAutomaticHtmlRoutingEnabled() {
    return true;
  }

}
