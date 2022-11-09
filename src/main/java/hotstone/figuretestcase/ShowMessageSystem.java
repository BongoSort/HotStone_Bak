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

package hotstone.figuretestcase;

import hotstone.view.message.MessageSystem;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** Visual test case for the message system */
public class ShowMessageSystem {
  public static void main(String[] args) {
    DrawingEditor editor =
            new MiniDrawApplication( "Showing MessageSystem, click to add messages...",
                    new SimpleViewFactory() );
    editor.open();
    MessageSystem messageSystem = new MessageSystem(editor.drawing());
    editor.setTool(new AddAMessageTool(messageSystem));
  }
}

class AddAMessageTool extends NullTool {
  private int count;
  private MessageSystem messageSystem;

  public AddAMessageTool(MessageSystem messageSystem) {
    this.messageSystem = messageSystem;
    count = 0;
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    super.mouseUp(e, x, y);
    messageSystem.addText("Message number: " + count);
    count++;
  }
}
