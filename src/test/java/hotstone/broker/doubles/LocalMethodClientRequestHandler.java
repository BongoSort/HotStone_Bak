/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.broker.doubles;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;

/** A FakeObject client requesthandler which bypass any network
 * calls, and instead forwards any request directly to the associated invoker.
 */
public class LocalMethodClientRequestHandler implements ClientRequestHandler {
  private Invoker invoker;

  public LocalMethodClientRequestHandler(Invoker invoker) {
    this.invoker = invoker;
  }

  @Override
  public String sendToServerAndAwaitReply(String request) {
    System.out.println(" --> " + request);
    String reply = invoker.handleRequest(request);
    System.out.println(" --< " + reply);
    return reply;
  }

  @Override
  public void setServer(String hostname, int port) {}

  @Override
  public void setServer(String hostname, int port, boolean useTLS) {}

  @Override
  public void close() {}
}
