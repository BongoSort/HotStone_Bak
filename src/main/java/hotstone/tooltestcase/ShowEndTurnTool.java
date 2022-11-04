/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package hotstone.tooltestcase;

import hotstone.figuretestcase.doubles.FakeObjectGame;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.view.core.HotStoneDrawingType;
import hotstone.view.core.HotStoneFactory;
import hotstone.view.tool.EndTurnTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

/** Demonstration of EndTurnTool */
public class ShowEndTurnTool {
  public static void main(String[] args) {
    Game game = new FakeObjectGame();

    DrawingEditor editor =
            new MiniDrawApplication( "Click End of Turn button to enact 'end of turn' tool",
                    new HotStoneFactory(game, Player.FINDUS,
                            HotStoneDrawingType.HOTSEAT_MODE) );
    editor.open();
    editor.setTool(new EndTurnTool(editor, game));
  }
}
