// DO NOT EDIT.  Make changes to BlogEntry.java instead.
package your.app.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

import er.extensions.eof.*;
import er.extensions.foundation.*;

@SuppressWarnings("all")
public abstract class _BlogEntry extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "BlogEntry";

  // Attribute Keys
  public static final ERXKey<String> CONTENT = new ERXKey<String>("content");
  public static final ERXKey<NSTimestamp> CREATION_DATE = new ERXKey<NSTimestamp>("creationDate");
  public static final ERXKey<NSTimestamp> LAST_MODIFIED = new ERXKey<NSTimestamp>("lastModified");
  public static final ERXKey<String> TITLE = new ERXKey<String>("title");
  // Relationship Keys
  public static final ERXKey<your.app.model.Author> AUTHOR = new ERXKey<your.app.model.Author>("author");

  // Attributes
  public static final String CONTENT_KEY = CONTENT.key();
  public static final String CREATION_DATE_KEY = CREATION_DATE.key();
  public static final String LAST_MODIFIED_KEY = LAST_MODIFIED.key();
  public static final String TITLE_KEY = TITLE.key();
  // Relationships
  public static final String AUTHOR_KEY = AUTHOR.key();

  private static Logger LOG = Logger.getLogger(_BlogEntry.class);

  public BlogEntry localInstanceIn(EOEditingContext editingContext) {
    BlogEntry localInstance = (BlogEntry)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String content() {
    return (String) storedValueForKey(_BlogEntry.CONTENT_KEY);
  }

  public void setContent(String value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating content from " + content() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.CONTENT_KEY);
  }

  public NSTimestamp creationDate() {
    return (NSTimestamp) storedValueForKey(_BlogEntry.CREATION_DATE_KEY);
  }

  public void setCreationDate(NSTimestamp value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating creationDate from " + creationDate() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.CREATION_DATE_KEY);
  }

  public NSTimestamp lastModified() {
    return (NSTimestamp) storedValueForKey(_BlogEntry.LAST_MODIFIED_KEY);
  }

  public void setLastModified(NSTimestamp value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating lastModified from " + lastModified() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.LAST_MODIFIED_KEY);
  }

  public String title() {
    return (String) storedValueForKey(_BlogEntry.TITLE_KEY);
  }

  public void setTitle(String value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
    	_BlogEntry.LOG.debug( "updating title from " + title() + " to " + value);
    }
    takeStoredValueForKey(value, _BlogEntry.TITLE_KEY);
  }

  public your.app.model.Author author() {
    return (your.app.model.Author)storedValueForKey(_BlogEntry.AUTHOR_KEY);
  }
  
  public void setAuthor(your.app.model.Author value) {
    takeStoredValueForKey(value, _BlogEntry.AUTHOR_KEY);
  }

  public void setAuthorRelationship(your.app.model.Author value) {
    if (_BlogEntry.LOG.isDebugEnabled()) {
      _BlogEntry.LOG.debug("updating author from " + author() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setAuthor(value);
    }
    else if (value == null) {
    	your.app.model.Author oldValue = author();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _BlogEntry.AUTHOR_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _BlogEntry.AUTHOR_KEY);
    }
  }
  

  public static BlogEntry createBlogEntry(EOEditingContext editingContext, String content
, NSTimestamp creationDate
, NSTimestamp lastModified
, String title
, your.app.model.Author author) {
    BlogEntry eo = (BlogEntry) EOUtilities.createAndInsertInstance(editingContext, _BlogEntry.ENTITY_NAME);    
		eo.setContent(content);
		eo.setCreationDate(creationDate);
		eo.setLastModified(lastModified);
		eo.setTitle(title);
    eo.setAuthorRelationship(author);
    return eo;
  }

  public static ERXFetchSpecification<BlogEntry> fetchSpec() {
    return new ERXFetchSpecification<BlogEntry>(_BlogEntry.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<BlogEntry> fetchAllBlogEntries(EOEditingContext editingContext) {
    return _BlogEntry.fetchAllBlogEntries(editingContext, null);
  }

  public static NSArray<BlogEntry> fetchAllBlogEntries(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _BlogEntry.fetchBlogEntries(editingContext, null, sortOrderings);
  }

  public static NSArray<BlogEntry> fetchBlogEntries(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<BlogEntry> fetchSpec = new ERXFetchSpecification<BlogEntry>(_BlogEntry.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<BlogEntry> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static BlogEntry fetchBlogEntry(EOEditingContext editingContext, String keyName, Object value) {
    return _BlogEntry.fetchBlogEntry(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static BlogEntry fetchBlogEntry(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<BlogEntry> eoObjects = _BlogEntry.fetchBlogEntries(editingContext, qualifier, null);
    BlogEntry eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one BlogEntry that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static BlogEntry fetchRequiredBlogEntry(EOEditingContext editingContext, String keyName, Object value) {
    return _BlogEntry.fetchRequiredBlogEntry(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static BlogEntry fetchRequiredBlogEntry(EOEditingContext editingContext, EOQualifier qualifier) {
    BlogEntry eoObject = _BlogEntry.fetchBlogEntry(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no BlogEntry that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static BlogEntry localInstanceIn(EOEditingContext editingContext, BlogEntry eo) {
    BlogEntry localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
