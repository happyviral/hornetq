/*
 * Copyright 2009 Red Hat, Inc.
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
package org.hornetq.service;

import org.hornetq.core.server.HornetQServer;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucaz Amador</a>
 * @author <a href="mailto:andy.taylor@jboss.org">Andy Taylor</a>
 */
public interface HornetQStarterServiceMBean
{
   void create() throws Exception;

   public void start() throws Exception;

   public void stop() throws Exception;

   HornetQServer getServer();

   void setStart(boolean start);

   void setSecurityManagerService(JBossASSecurityManagerServiceMBean securityManagerService);

   void setConfigurationService(HornetQFileConfigurationServiceMBean configurationService);
}
