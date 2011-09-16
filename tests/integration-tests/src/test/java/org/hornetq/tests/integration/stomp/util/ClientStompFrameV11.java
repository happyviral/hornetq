/*
 * Copyright 2010 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.hornetq.tests.integration.stomp.util;

/**
 * 
 * @author <a href="mailto:hgao@redhat.com">Howard Gao</a>
 *
 * pls use factory to create frames.
 */
public class ClientStompFrameV11 extends AbstractClientStompFrame
{
   boolean forceOneway = false;
   
   public ClientStompFrameV11(String command)
   {
      super(command);
   }
   
   public void setForceOneway()
   {
      forceOneway = true;
   }

   @Override
   public boolean needsReply()
   {
      if (forceOneway) return false;
      
      if ("CONNECT".equals(command) || "STOMP".equals(command) || headerKeys.contains(HEADER_RECEIPT))
      {
         return true;
      }
      return false;
   }
}
