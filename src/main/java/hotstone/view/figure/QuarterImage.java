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

package hotstone.view.figure;

import minidraw.standard.ImageFigure;

import java.awt.*;
import java.awt.image.BufferedImage;


/** A utility Figure class representing an image
 * and then downsize it to 1/4 of the original size.
 *
 * This facilitates downloading Blizzard Entertainment
 * card graphics from https://playhearthstone.com/
 * card library, cut the emblem using a graphics
 * tool editor, and use these directly in MiniDraw.
 *
 * If you use GIMP you can automate the process by the
 * scripted function
 *
 * (gimp-image-select-ellipse image CHANNEL-OP-ADD 82 50 193 262)
 *
 * According to Blizzard Entertainment's copyright
 * notices, graphics can be used for personal and
 * non-commercial use.
 *
 */
public class QuarterImage extends ImageFigure {
  public QuarterImage(String imageName, Point position) {
    super(imageName, position);
    // Credits: https://www.baeldung.com/java-resize-image
    int targetWidth = fImage.getWidth(null) / 2;
    int targetHeight = fImage.getHeight(null) / 2;
    // Draw image in 1/4 size
    Image resultingImage = fImage.getScaledInstance(targetWidth, targetHeight,
            Image.SCALE_DEFAULT);
    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight,
            BufferedImage.TYPE_4BYTE_ABGR);
    resizedImage.getGraphics().drawImage(resultingImage, 0, 0, null);
    // And override original image
    fImage = resizedImage;
    fDisplayBox = new Rectangle(displayBox().x, displayBox().y,
            targetWidth, targetHeight);
  }
}
