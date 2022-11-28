package hotstone.view.tool;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.view.figure.HotStoneFigure;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class DualUserInterfaceTool extends NullTool {
    private final Tool theNullTool;
    private final Drawing model;
    private final Player whoToPlay;
    private Tool state;
    private DrawingEditor editor;
    private Game game;

    public DualUserInterfaceTool(DrawingEditor editor, Game game, Player whoToPlay) {
        this.editor = editor;
        this.game = game;
        this.whoToPlay = whoToPlay;
        model = editor.drawing();
        state = theNullTool = new NullTool();
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        if (figureAtPosition instanceof HotStoneFigure) {
            HotStoneFigure hsf = (HotStoneFigure) figureAtPosition;
            switch (hsf.getType()) {
                case CARD_FIGURE -> state = new CardPlayTool(editor, game, whoToPlay);
                case TURN_BUTTON, SWAP_BUTTON -> state = new EndTurnTool(editor, game);
                case MINION_FIGURE -> state = new MinionAttackTool(editor,game,whoToPlay);
                case HERO_FIGURE -> state = new UsePowerTool(editor,game);
                case WIN_BUTTON -> state = new EndGameTool(editor);
                case OPPONENT_ACTION_BUTTON -> state = new OpponentActionTool(editor);
            }
        }
        state.mouseDown(e, x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        state.mouseUp(e, x, y);
        if(state != theNullTool) {
            Drawing drawing = editor.drawing();
            drawing.requestUpdate();
        }
        state = theNullTool;
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        state.mouseDrag(e, x, y);
    }

    @Override
    public void mouseMove(MouseEvent e, int x, int y) {
        state.mouseMove(e, x, y);
    }
}
