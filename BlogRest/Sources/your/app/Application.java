package your.app;

import your.app.model.Author;
import your.app.model.BlogEntry;
import er.extensions.appserver.ERXApplication;
import er.rest.routes.ERXRouteRequestHandler;

public class Application extends ERXApplication {
  public static void main(String[] argv) {
    ERXApplication.main(argv, Application.class);
  }

  public Application() {
    ERXApplication.log.info("Welcome to " + name() + " !");
    /* ** put your initialization code in here ** */
    setAllowsConcurrentRequestHandling(true);

    ERXRouteRequestHandler restRequestHandler = new ERXRouteRequestHandler();
    restRequestHandler.addDefaultRoutes(BlogEntry.ENTITY_NAME);
    restRequestHandler.addDefaultRoutes(Author.ENTITY_NAME);
    ERXRouteRequestHandler.register(restRequestHandler);
    setDefaultRequestHandler(restRequestHandler);
  }
}