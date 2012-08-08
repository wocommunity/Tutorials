package your.app.model;

import org.apache.log4j.Logger;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSTimestamp;

public class BlogEntry extends _BlogEntry {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(BlogEntry.class);
	
	@Override
	public void awakeFromInsertion(EOEditingContext editingContext) {
	  super.awakeFromInsertion(editingContext);
	  this.setCreationDate(new NSTimestamp());
	  this.setLastModified(new NSTimestamp());
	}
}
