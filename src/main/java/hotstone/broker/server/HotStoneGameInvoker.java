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

package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Game;

import javax.servlet.http.HttpServletResponse;

public class HotStoneGameInvoker implements Invoker {
  private final Game game;
  private final Gson gson;

  public HotStoneGameInvoker(Game servant) {
    this.game = servant;
    gson = new Gson();
  }

  @Override
  public String handleRequest(String request) {
    // Do the demarshalling
    RequestObject requestObject =
            gson.fromJson(request, RequestObject.class);
    JsonArray array =
            JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

    ReplyObject reply;

    switch (requestObject.getOperationName()) {
      case OperationNames.GAME_GET_TURN_NUMBER -> {
        int turnNumber = game.getTurnNumber();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(turnNumber));
      }
      default -> reply = null;
    }
    return  gson.toJson(reply);
  }
}
