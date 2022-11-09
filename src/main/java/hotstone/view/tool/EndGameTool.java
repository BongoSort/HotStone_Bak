package hotstone.view.tool;

import hotstone.framework.Game;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class EndGameTool extends NullTool {
    protected final HotStoneDrawing model;

    public EndGameTool(DrawingEditor editor) {
        model = (HotStoneDrawing) editor.drawing();
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {}

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        // Find the button below
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        if (figureAtPosition instanceof HotStoneFigure) {
            HotStoneFigure hsf = (HotStoneFigure) figureAtPosition;
            if (hsf.getType() == HotStoneFigureType.WIN_BUTTON) {
                System.exit(0);
            }
        }
    }
}
