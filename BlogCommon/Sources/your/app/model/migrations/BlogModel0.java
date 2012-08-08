package your.app.model.migrations;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class BlogModel0 extends ERXMigrationDatabase.Migration {
  @Override
  public NSArray<ERXModelVersion> modelDependencies() {
    return null;
  }
  
  @Override
  public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
    // DO NOTHING
  }

  @Override
  public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
    ERXMigrationTable authorTable = database.newTableNamed("Author");
    authorTable.newStringColumn("email", 100, false);
    authorTable.newStringColumn("firstName", 50, false);
    authorTable.newIntegerColumn("id", false);
    authorTable.newStringColumn("lastName", 50, false);
    authorTable.create();
    authorTable.setPrimaryKey("id");

    ERXMigrationTable blogEntryTable = database.newTableNamed("BlogEntry");
    blogEntryTable.newIntegerColumn("authorID", false);
    blogEntryTable.newLargeStringColumn("content", false);
    blogEntryTable.newTimestampColumn("creationDate", false);
    blogEntryTable.newIntegerColumn("id", false);
    blogEntryTable.newTimestampColumn("lastModified", false);
    blogEntryTable.newStringColumn("title", 255, false);
    blogEntryTable.create();
    blogEntryTable.setPrimaryKey("id");

    blogEntryTable.addForeignKey("authorID", "Author", "id");
  }
}