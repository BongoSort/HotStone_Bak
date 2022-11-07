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

package hotstone.view.core;

import hotstone.framework.*;
import hotstone.observer.GameObserver;
import hotstone.standard.StandardHotStoneGame;
import hotstone.view.GfxConstants;
import hotstone.view.figure.*;
import hotstone.view.message.MessageFigure;
import hotstone.view.message.MessageSystem;
import hotstone.view.tool.EndTurnTool;
import minidraw.framework.*;
import minidraw.standard.StandardFigureCollection;
import minidraw.standard.handlers.ForwardingFigureChangeHandler;
import minidraw.standard.handlers.StandardDrawingChangeListenerHandler;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.locks.Lock;

/** This is ALMOST COMPLETE TEMPLATE CODE for the MiniDraw
 * integration exercise. Find the TODO's in the code below
 * and use the 'ShowUpdate' visual test to implement
 * the missing aspects.
 *
 * HotStoneDrawing implements the Drawing role of MiniDraw (i.e. is
 * the 'Model' component of MVC holding all the Figures that MiniDraw
 * will draw on the screen) as well as the role of GameObserver (i.e.
 * receives events on all state changes in the underlying HotStone
 * game, so the Drawing can update the associated figures - which will
 * trigger a graphical redraw of the window).
 *
 * <p>
 *
 * It is designed as a composition of standard implementations from
 * MiniDraw for figureCollection and Listener handling (for figure
 * changes and drawing changes), while of course implement all the
 * special behaviour required for the HotStone game GFX itself.
 *
 * <p>
 *
 * All manipulations of the Figures in the Drawing are through the
 * GameObserver role: Any event notifications from the game (like
 * onCardDraw or onCardRemove) are translated into the equivalent
 * graphical representation like adding or removing figures to
 * Drawing, updating figure graphics, etc., leading to the UI is
 * updated.
 *
 * <p>
 *
 * As all game events are expressed in terms of instances of 'Card' or
 * 'Hero', internal mappings (actorMap and heroMap) are maintained using
 * the card/hero as key, and having the associated Figure as value. It
 * is **VITAL** that this mapping is maintained correctly - any card
 * add events must create a figure and add it to the mapping. Do not
 * mutate the actorMap directly, rather use the private methods to
 * update it (createActor... and removeActor...) as there is internal
 * state that must be kept consistent.
 */

public class HotStoneDrawing implements Drawing, GameObserver {

  // The operational mode that this UI will run in, either as
  // a hotseat (players use same screen) or remote (players
  // each have their own screen)
  private final HotStoneDrawingType uiType;

  // Standard delegates from the MiniDraw Framework
  private final StandardDrawingChangeListenerHandler listenerHandler;
  private final FigureChangeListener figureChangeListener;
  private final FigureCollection figureCollection;

  // Associations to game and editor
  private Game game;
  private DrawingEditor editor;

  // Internal mappings (card, cardfigure) and (hero, herofigure)
  // so any game event from the observer can be translated into
  // the associated figure to update/delete etc.
  // In game engines, the 'moving figures' are often called 'actors'
  // (for instance in LibGDX), so it is called actorMap here.
  // DO NOT manipulate mappings directly, use the private mutator
  // methods instead!
  private Map<Card, HotStoneActorFigure> actorMap;
  private Map<Player, HeroFigure> heroMap;

  // Define which player is currently shown on the UI,
  // is it findus or peddersen that the UI represents?
  private Player playerShown;

  // Figures representing buttons and text on the UI
  private ButtonFigure endOfTurnButton;
  private ButtonFigure hotseatButton;
  private ButtonFigure awaitNextActionButton;

  // Opponent player's stats is just text
  private TextFigure opponentSummary;

  // The 'blue messages' system providing info about events
  private MessageSystem messageSystem;

  /** Construct a MiniDraw Drawing that manipulates its
   * internal FigureCollection in sync with the associated
   * game instance.
   * @param editor the minidraw editor
   * @param game the associated hotstone game
   * @param operatingPlayer the player that this UI is showing
   * @param uiType enum type for the type of UI needed, either
   *               a hotseat (two player on one screen) or a
   *               remote (two player on their own screen)
   */
  public HotStoneDrawing(DrawingEditor editor, Game game,
                         Player operatingPlayer,
                         HotStoneDrawingType uiType) {
    super();
    this.playerShown = operatingPlayer;

    // Create default delegates, largely equivalent to that of
    // CompositionalDrawing in MiniDraw, except SelectionHandler
    // is not used (it makes no sense in this context).
    listenerHandler = new StandardDrawingChangeListenerHandler();
    figureChangeListener =
            new ForwardingFigureChangeHandler(this, listenerHandler);
    figureCollection = new StandardFigureCollection(figureChangeListener);

    this.editor = editor;
    this.game = game;
    this.uiType = uiType;

    // Add a message system which displays 'action text'
    messageSystem = new MessageSystem(this);

    // Create the internal mappings to figures
    actorMap = new HashMap<>();
    heroMap = new HashMap<>();

    // Listen to all events coming from the game to keep the
    // drawing updated with the game state
    game.addObserver(this);

    // Initialize Figures for all (visible) game state
    createFiguresForButtons();
    createAndAddFiguresForGameState();
  }

  // === Delegation methods for the DrawingChangeListeners
  /**
   * Adds a listener for this drawing.
   */
  @Override
  public void addDrawingChangeListener(DrawingChangeListener listener) {
    listenerHandler.addDrawingChangeListener(listener);
  }

  /**
   * Removes a listener from this drawing.
   */
  @Override
  public void removeDrawingChangeListener(DrawingChangeListener listener) {
    listenerHandler.removeDrawingChangeListener(listener);
  }

  /**
   * Invalidates a rectangle and merges it with the existing damaged area.
   *
   * @see FigureChangeListener
   */
  @Override
  public void figureInvalidated(FigureChangeEvent e) {
    listenerHandler.fireDrawingInvalidated(this, e.getInvalidatedRectangle());
  }

  @Override
  public void figureChanged(FigureChangeEvent e) {
    listenerHandler.fireDrawingRequestUpdate(this);
  }

  // === Delegate to the figure collection
  @Override
  public Figure add(Figure figure) {
    return figureCollection.add(figure);
  }

  @Override
  public Figure remove(Figure figure) {
    return figureCollection.remove(figure);
  }

  @Override
  public Iterator<Figure> iterator() {
    return figureCollection.iterator();
  }

  @Override
  public Figure findFigure(int x, int y) {
    return figureCollection.findFigure(x, y);
  }

  @Override
  public Figure zOrder(Figure f, ZOrder order) {
    return figureCollection.zOrder(f, order);
  }

  @Override
  public Lock readLock() {
    return figureCollection.readLock();
  }

  @Override
  public Lock writeLock() {
    return figureCollection.writeLock();
  }

  /** Request update means rebuild Gfx from scratch. */
  @Override
  public void requestUpdate() {
    removeAllFigures();
    createAndAddFiguresForGameState();
    listenerHandler.fireDrawingRequestUpdate(this);
  }

  // === Delegation methods for the Selection handling
  // These are all void actions, as there is no use for
  // selecting multiple figures in a HotStone UI.

  private List<Figure> theNullList = new ArrayList<>();
  @Override
  public List<Figure> selection() { return theNullList;}
  @Override
  public void addToSelection(Figure figure) {}
  @Override
  public void removeFromSelection(Figure figure) {}
  @Override
  public void toggleSelection(Figure figure) {}
  @Override
  public void clearSelection() {}

  /** Create figures for (most) buttons. Note - they are not inserted in the
   * figure collection, just initialized. They are 'flipped on and off'
   * depending upon the state of the UI.
   */
  private void createFiguresForButtons() {
    // Define the buttons used for game play state changes

    // The button to end the turn
    endOfTurnButton = new ButtonFigure(
            HotStoneFigureType.TURN_BUTTON,
            GfxConstants.END_TURN_TEXT,
            GfxConstants.TURN_BUTTON_POSITION);

    // The button to 'fetch what the opponent have done'
    // acting like a browser's refresh button
    // Only relevant for distributed play
    awaitNextActionButton = new ButtonFigure(
            HotStoneFigureType.OPPONENT_ACTION_BUTTON,
            GfxConstants.NEXT_ACTION_TEXT,
            GfxConstants.HOTSEAT_BUTTON_POSITION);

    // The button to in 'hotseat' mode which
    // toggles which player should play
    hotseatButton = new ButtonFigure(
            HotStoneFigureType.SWAP_BUTTON,
            "Will be replaced",
            GfxConstants.HOTSEAT_BUTTON_POSITION);
  }

  /**
   * Perform a full state resynchronization with the associated
   * game: that is, retrieve all (visible) state from game and build
   * each associated figure and add them to figure collection
   * and enter into the actorMap etc.
   * PRECONDITION: the figure collection must be empty.
   */
  private void createAndAddFiguresForGameState() {
    // Show appropriate button depending on whose turn it is
    if (game.getPlayerInTurn() == playerShown)
      add(endOfTurnButton);
    else
      add(awaitNextActionButton);

    createHeroFigureAndUpdateMapping(playerShown);

    Hero hero = game.getHero(playerShown);
    // TODO: Add the text figure for the shown player's power
    TextFigure myHeroPowerText = new TextFigure(hero.getEffectDescription(),
            GfxConstants.MY_HERO_POWER_DESCRIPTION_POSITION,
            Color.YELLOW, GfxConstants.SMALL_FONT_SIZE);
    add(myHeroPowerText);

    createHeroFigureAndUpdateMapping(Utility.computeOpponent(playerShown));

    // Opponent power
    hero = game.getHero(Utility.computeOpponent(playerShown));
    TextFigure oppHeroPowerText = new TextFigure(hero.getEffectDescription(),
            GfxConstants.OPPONENT_HERO_POWER_DESCRIPTION_POSITION,
            Color.YELLOW, GfxConstants.SMALL_FONT_SIZE);
    add(oppHeroPowerText);

    // Iterate shown player's hand
    for (Card card : game.getHand(playerShown)) {
      createActorAndUpdateMapping(card, HotStoneFigureType.CARD_FIGURE);
    }
    // Finally, position the cards 'nicely'
    refreshHand(playerShown);

    // Opponent's hand is shown in another way
    opponentSummary =
            new TextFigure(computeHeroSummary(Utility.computeOpponent(playerShown)),
            GfxConstants.OPPONENT_SUMMARY_POSITION, Color.WHITE,
                    GfxConstants.SMALL_FONT_SIZE);
    add(opponentSummary);

    // Handle Fielded minions for both players
    for (Player player : Player.values()) {
      // Iterate all Minions in field
      for (Card card : game.getField(player)) {
        createActorAndUpdateMapping(card,
                HotStoneFigureType.MINION_FIGURE);
      }
      // Finally position the cards 'nicely'
      refreshField(player);
    }
  }

  private String computeHeroSummary(Player who) {
    String shortPlayername = who.toString().substring(0, 1)
            + who.toString().substring(1).toLowerCase();
    return shortPlayername + ": Hand (" + game.getHandSize(who)
            + "), Deck (" + game.getDeckSize(who) +")";
  }

  // === Observer event handling - update the UI based upon what event notifications
  // are sent by the game. These methods rely heavily on the actorMap/heroMap is
  // properly updated to find the figure associated.

  @Override
  public void onCardPlay(Player who, Card card) {
    addMessage("" + who + " plays " + card.getName() + ".");
    // TODO: Add another message if the card has an effect

    // As this direct mutator call has known side effects which are
    // not represented by the indirect observer notifications, the
    // card/minion updates are effected here: Remove the card figure
    // and replace it with a minion figure.
    removeActorAndUpdateMapping(card);
    createActorAndUpdateMapping(card, HotStoneFigureType.MINION_FIGURE);

    refreshField(who);

    opponentSummary.setText(computeHeroSummary(
            Utility.computeOpponent(playerShown)));
  }

  @Override
  public void onTurnChangeTo(Player playerBecomingActive) {
    addMessage("Turn ends (" + game.getTurnNumber()
            + "). Next is " + playerBecomingActive);
    // Now - depending upon if we play hot seat or dual UI
    // initiate the proper UI changes
    if (uiType == HotStoneDrawingType.HOTSEAT_MODE) {
      enterHotSeatState();
      endHotSeatState();
    } else {
      // IFF we are not playing hotseat then 'playerShown' is the same value
      // throughout a game, namely the player that is 'owning' this UI, so
      // we can determine action based upon this

      // IFF endOf turn was called with next player == playerShown then
      // we hand over control to the opponent
      if (playerShown != playerBecomingActive) {
        enterOpponentActionState();
      } else {
        endOpponentActionState();
      }
    }
  }

  @Override
  public void onAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    addMessage("" + playerAttacking
            + " attacks " + defendingCard.getName()
            + " with " + attackingCard.getName() + ".");
  }

  @Override
  public void onAttackHero(Player playerAttacking, Card attackingCard) {
    // TODO: Inform player
    //opponentSummary.setText(computeHeroSummary(Utility.computeOpponent(playerShown)));

    refreshHero(Utility.computeOpponent(playerAttacking));

    addMessage(playerAttacking.toString() + " attacks " +
            Utility.computeOpponent(playerAttacking).toString() +
            " hero with " + attackingCard.getName());
    //addMessage("TODO: tell about attack");
  }

  @Override
  public void onUsePower(Player who) {
    // TODO: Inform player
    //addMessage("TODO: tell about power");
    refreshHero(who);
    addMessage(who.toString() + " used hero power ");
  }

  @Override
  public void onCardDraw(Player who, Card drawnCard) {
    // If showing 'myself' then add the card to the hand,
    // refresh the hand; otherwise just update the summary
    // of the opponent player
    if (who == playerShown) {
      createActorAndUpdateMapping(drawnCard, HotStoneFigureType.CARD_FIGURE);
      refreshHand(who);
    } else {
      // TODO: update opponent's summary
    }
    addMessage(who + " draws a card.");
  }

  @Override
  public void onCardUpdate(Card card) {
    HotStoneActorFigure actor = actorMap.get(card);
    // Opponent cards may not have an associated actor
    // for instance if they are in the hand.
    if (actor != null) {
      actor.updateStats();
    }
  }

  @Override
  public void onCardRemove(Player who, Card card) {
    // TODO: Remove the minion and refresh field
    // NOTE: be SURE to use the
    // right internal data structure manipulation method
    // so both the figure collection AND the actorMap
    // is updated
    removeActorAndUpdateMapping(card);
    refreshField(who);
    addMessage("TODO: " + who + "'s minion " + card.getName()
            + " is killed.");
  }

  @Override
  public void onHeroUpdate(Player playerInTurn) {
    refreshHero(playerInTurn);
  }

  @Override
  public void onGameWon(Player playerWinning) {
    addMessage("Game was won by " + playerWinning);
    haltOnGameWonScreen();
  }


  private void refreshHero(Player playerInTurn) {
    HeroFigure hero = heroMap.get(playerInTurn);
    // During hotseat swap, the game fires a heroUpdate event
    // which occurs after the 'swap players' screen appears
    // thus there ARE no hero to update. Guard against that
    if (hero != null)
      hero.updateStats();
  }

  private void refreshField(Player who) {
    int count = 0;
    // Compute how to lay out the field based upon the
    // number of fielded minions
    int distance = GfxConstants.FIELDED_CARD_DISTANCE;
    int fieldSize = game.getFieldSize(who);
    int offset = fieldSize % 2 == 0 ? 0 : distance/2;
    int centerX = GfxConstants.SCREEN_WIDTH_PIXELS/2
            - offset - (fieldSize/2) * distance + GfxConstants.X_FIELD_OFFSET;
    int yPos = (who == playerShown ?
            GfxConstants.MY_FIELD_Y_POSITION :
            GfxConstants.OPPONENT_FIELD_Y_POSITION);

    // Then iterate all fielded cards
    for (Card card: game.getField(who)) {
      HotStoneActorFigure actor = actorMap.get(card);
      // When replaying multiple 'onCardPlay()' events
      // from the opponent a special situation may occur:
      // no actor/figure is yet associated with the card!
      // We can then just ignore it, because subsequent
      // replays of onCardPlay events will have it added.
      if (actor != null) {
        actor.moveTo(centerX + distance * count++, yPos);
      } else {
        // DO NOT ADD A MINION HERE - it is not the responsibility
        // and you will end up with multiple figures for the same card.
      }
    }
  }

  /** Refresh all Gfx associated with the
   * hand of 'who' which means laying out
   * the cards equidistant
   * @param who the player whose hand must be refreshed
   */
  public void refreshHand(Player who) {
    int offsetX = GfxConstants.HAND_CARD_OFFSET;
    int distance = GfxConstants.HAND_CARD_DISTANCE;
    int yPos = GfxConstants.MY_HAND_POSITION_Y;
    // if it is the shown player
    if (who == playerShown) {
      int count = 0;
      for (Card card: game.getHand(who)) {
        assert card.getOwner() == who;
        HotStoneActorFigure actor = actorMap.get(card);
        actor.moveTo(offsetX + distance * count++, yPos);
        zOrder(actor, ZOrder.TO_TOP);
      }
    } else {
      // it is opponent player, just update the stats of deck and hand size
      opponentSummary.setText(computeHeroSummary(who));
    }
  }

  public void addMessage(String message) {
    messageSystem.addText(message);
  }

  /** Remove ALL figures in the collections
   * and clear associated mappings. The result
   * is the UI being cleared completely and
   * must be rebuilt from scratch.
   */
  private void removeAllFigures() {
    actorMap.clear();
    heroMap.clear();
    // Damn, no clear yet in MiniDraw?!?
    writeLock().lock();
    try {
      for (Figure f : figureCollection)
        // Do not remove the messages
        if (!(f instanceof MessageFigure))
          figureCollection.remove(f);
    } finally { writeLock().unlock(); }
  }

  /** Create a Figure representing a card or a minion,
   * add it to the figure collection (so it is rendered) and
   * update the actor mapping
   * @param card the hotstone card that the figure is associated
   *             with
   * @param type the type of figure, either minion or card
   */
  private void createActorAndUpdateMapping(Card card, HotStoneFigureType type) {
    HotStoneActorFigure actor;
    if (type == HotStoneFigureType.CARD_FIGURE) {
      actor = new CardFigure(card, new Point(GfxConstants.SCREEN_WIDTH_PIXELS,
              GfxConstants.SCREEN_HEIGHT_PIXELS / 2)); // Will be laid out later
    } else {
      actor = new MinionFigure(card, new Point(0, 500)); // will be laid out later
    }

    // Add the figure to the drawing's collection (for rendering)
    add(actor);
    actorMap.put(card, actor);

    // Last drawn card is at the top, so push this below the previous one
    // to simulate the way a player's hand looks like
    zOrder(actor, ZOrder.TO_BOTTOM);
  }

  /** Remove an actor / figure from the figure collection and
   * clean the mapping
   * @param card the card whose actor must be removed
   */
  private void removeActorAndUpdateMapping(Card card) {
    remove(actorMap.get(card));
    actorMap.remove(card);
  }

  /**
   * Create a hero figure and update mapping
   * @param who the player this figure is representing
   */
  private void createHeroFigureAndUpdateMapping(Player who) {
    Point position = (who == playerShown ?
            GfxConstants.MY_HERO_POSITION :
            GfxConstants.OPPONENT_HERO_POSITION);

    HeroFigure heroFigure =
            new HeroFigure(game.getHero(who), position);
    heroMap.put(who, heroFigure);
    add(heroFigure);
  }
  // Note - no need for a corresponding remove method,
  // hero's stay around unless everything is removed.

  // === Update UI to reflect End of Turn
  public void enterHotSeatState() {
    removeAllFigures();
    hotseatButton.setText("To " + game.getPlayerInTurn());
    add(hotseatButton);
  }

  public void endHotSeatState() {
    remove(hotseatButton);
    playerShown = game.getPlayerInTurn();
    createAndAddFiguresForGameState();
  }

  private void enterOpponentActionState() {
    // Remove the EoT button
    remove(endOfTurnButton);
    // Add the next action button
    add(awaitNextActionButton);
  }

  private void endOpponentActionState() {
    // reverse buttons shown
    remove(awaitNextActionButton);
    add(endOfTurnButton);
  }

  /** Enter the 'game won' state in which only a single
   * button with the winner name is shown.
   */
  private void haltOnGameWonScreen() {
    removeAllFigures();

    // Define the win button, which does nothing
    Figure gameWonButton = new ButtonFigure(
            HotStoneFigureType.WIN_BUTTON,
            "Game won by " + game.getWinner(),
            GfxConstants.WIN_BUTTON_POSITION);
    // and show it
    add(gameWonButton);
  }
}


