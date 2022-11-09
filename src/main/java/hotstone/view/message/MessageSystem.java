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

package hotstone.view.message;

import hotstone.view.GfxConstants;
import minidraw.framework.Drawing;
import minidraw.framework.Figure;
import minidraw.framework.ZOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** The role of a message system which prints texts in a scroll about events
 * for the game. The scroll automatically expires messages once they have
 * been shown for a given specific number of seconds.
 */
public class MessageSystem {
  private final Drawing drawing;
  private final CountDownTimerThread countDownTimerThread;
  private List<MessageFigure> messageFigureList;
  private boolean counterIsActive;

  public MessageSystem(Drawing drawing) {
    this.drawing = drawing;
    messageFigureList = new ArrayList<>();
    // Decorate with a synchronized version
    messageFigureList = Collections.synchronizedList(messageFigureList);
    countDownTimerThread = new CountDownTimerThread();
    countDownTimerThread.sleepInMilli = GfxConstants.DISPLAY_TIME_MESSAGES_MS;
    counterIsActive = false;
  }

  public void addText(String text) {
    MessageFigure newMsg = new MessageFigure();
    newMsg.setText(text);
    newMsg.moveBy(GfxConstants.MESSAGE_X,GfxConstants.MESSAGE_Y
            + GfxConstants.MESSAGE_HEIGHT * messageFigureList.size());

    drawing.add(newMsg);
    messageFigureList.add(newMsg);
    startCountDownTimer();
  }

  private void startCountDownTimer() {
    if (! counterIsActive)
      new Thread(countDownTimerThread).start();
  }

  private void removeTopText() {
    if (messageFigureList.size() == 0) {
      return;
    }

    // Pop the top text
    Figure figure = messageFigureList.remove(0);
    drawing.remove(figure);

    // And move the rest up
    messageFigureList.stream()
            .forEach(fig -> {
              fig.moveBy(0, -GfxConstants.MESSAGE_HEIGHT);
              drawing.zOrder(fig, ZOrder.TO_TOP);
            }
            );

    startCountDownTimer();
  }

  // A count down which waits a set time and then
  // removes the top message
  class CountDownTimerThread implements Runnable {
    int sleepInMilli;

    public void run() {
      try {
        counterIsActive = true;
        Thread.sleep(sleepInMilli);
      } catch( InterruptedException e) {}
      counterIsActive = false;
      removeTopText();
    }
  }
}
