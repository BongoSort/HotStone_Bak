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
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.SelectionTool;

/** Visual test program to develop use power tool */
public class ShowUsePowerTool {
  public static void main(String[] args) {
    Game game = new FakeObjectGame();

    DrawingEditor editor =
            new MiniDrawApplication("Click Hero to use power...",
                    new HotStoneFactory(game, Player.FINDUS,
                            HotStoneDrawingType.HOTSEAT_MODE));
    editor.open();
    // TODO: Solve exercise by developing a UsePowerTool
    editor.setTool(new SelectionTool(editor));
  }
}
