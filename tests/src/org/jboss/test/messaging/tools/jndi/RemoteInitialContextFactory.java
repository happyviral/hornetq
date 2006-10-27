/*
* JBoss, Home of Professional Open Source
* Copyright 2005, JBoss Inc., and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jboss.test.messaging.tools.jndi;

import org.jboss.logging.Logger;
import org.jboss.test.messaging.tools.jmx.rmi.RMITestServer;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

/**
 * @author <a href="mailto:ovidiu@jboss.org">Ovidiu Feodorov</a>
 * @version <tt>$Revision$</tt>
 *
 * $Id$
 */
public class RemoteInitialContextFactory implements InitialContextFactory
{
   // Constants -----------------------------------------------------

   private static final Logger log = Logger.getLogger(RemoteInitialContextFactory.class);

   // Static --------------------------------------------------------

   private static RemoteContext[] initialContexts = new RemoteContext[RMITestServer.RMI_REGISTRY_PORTS.length];
   
   private static final String REMOTE_SERVER_INDEX_KEY_NAME = "jboss.messaging.test.remoteserverindex";

   /**
    * @return the JNDI environment to use to get this InitialContextFactory.
    */
   public static Hashtable getJNDIEnvironment(int index)
   {
      Hashtable env = new Hashtable();
      env.put("java.naming.factory.initial",
              "org.jboss.test.messaging.tools.jndi.RemoteInitialContextFactory");
      env.put("java.naming.provider.url", "");
      env.put("java.naming.factory.url.pkgs", "");
      env.put(REMOTE_SERVER_INDEX_KEY_NAME, String.valueOf(index));
      return env;
   }

   // Attributes ----------------------------------------------------
   
   // Constructors --------------------------------------------------
   
   // Public --------------------------------------------------------

   public Context getInitialContext(Hashtable environment) throws NamingException
   {
      String s = (String)environment.get(REMOTE_SERVER_INDEX_KEY_NAME);
      
      if (s == null)
      {
         throw new IllegalArgumentException("Initial context environment must contain entry for " + REMOTE_SERVER_INDEX_KEY_NAME);
      }
      
      int remoteServerIndex = Integer.parseInt(s);
      
      if (initialContexts[remoteServerIndex] == null)
      {
         try
         {
            initialContexts[remoteServerIndex] = new RemoteContext(remoteServerIndex);
         }
         catch(Exception e)
         {
            log.error("Cannot get the remote context", e);
            throw new NamingException("Cannot get the remote context");
         }
      }
      return initialContexts[remoteServerIndex];
   }

   // Package protected ---------------------------------------------
   
   // Protected -----------------------------------------------------
   
   // Private -------------------------------------------------------
   
   // Inner classes -------------------------------------------------   
}
