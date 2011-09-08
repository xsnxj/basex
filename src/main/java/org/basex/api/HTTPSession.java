package org.basex.api;

import static org.basex.api.HTTPText.*;
import java.io.IOException;
import org.basex.server.ClientSession;
import org.basex.server.LocalSession;
import org.basex.server.Session;

/**
 * This is a container for an HTTP session.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class HTTPSession {
  /** Database context. */
  public final HTTPContext http;
  /** User name. */
  public String user;
  /** Password. */
  public String pass;

  /**
   * Constructor.
   * @param ht http context
   * @param u user
   * @param p password
   */
  public HTTPSession(final HTTPContext ht, final String u, final String p) {
    http = ht;
    final String su = System.getProperty(DBUSER);
    final String sp = System.getProperty(DBPASS);
    user = su != null ? su : u;
    pass = sp != null ? sp : p;
  }

  /**
   * Returns a new session instance.
   * @return session
   * @throws IOException I/O exception
   */
  public Session login() throws IOException {
    /* retrieve host and port information from system properties:
    final String host = System.getProperty(DBHOST);
    final int port = Integer.parseInt(System.getProperty(DBPORT));

    // local login: check if user exists
    final User usr = ctx.users.get(user);
    if(usr == null || !eq(usr.password, token(md5(pass)))) return null;
    */

    return http.client ? new ClientSession(http.context, user, pass) :
      new LocalSession(http.context);
  }
}
