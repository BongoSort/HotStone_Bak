package hotstone.view.tool;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.figure.HeroFigure;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class UsePowerTool extends NullTool {
    protected final HotStoneDrawing model;
    private Game game;

    public UsePowerTool(DrawingEditor editor, Game game) {
        model = (HotStoneDrawing) editor.drawing();
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        // Find the button below
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        if (figureAtPosition instanceof HeroFigure) {
            HeroFigure hsf = (HeroFigure) figureAtPosition;
            Player heroPlayer = hsf.getAssociatedHero().getOwner();
            game.usePower(heroPlayer);
        }
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {

    }
}
