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

package hotstone.figuretestcase.doubles;

import hotstone.framework.Card;
import hotstone.framework.Player;

/** A Test Double Stub implementation of Card to facilitateUI testing
 * and development.
 *
 * Note that is has some 'fake object' behaviour as you can modify
 * some of the objects fields.
 */
public class StubCard implements Card {
  private final String name;
  private final String effDesc;
  private boolean isActive;
  private int health;
  private Player owner;
  private int attack;

  public StubCard(String name, Player owner) {
    this(name, owner, 1);
  }

  public StubCard(String name, Player who, int health) {
    this(name, who, health, "");
  }

  public StubCard(String name, Player who, int health, String effectDescription) {
    this.name = name;
    this.owner = who;
    this.health = health;
    this.effDesc = effectDescription;
    this.isActive = false;
    attack = 1;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getManaCost() {
    return 1;
  }

  @Override
  public int getAttack() {
    return attack;
  }

  @Override
  public int getHealth() {
    return health;
  }

  @Override
  public boolean isActive() {
    return isActive;
  }

  @Override
  public Player getOwner() {
    return owner;
  }

  @Override
  public String getEffectDescription() {
    return "something about an effect, i don't know i am just a stub";
  }

  public void setHealth(int newValue) {
    health = newValue;
  }

  public void setActiveTo(boolean newValue) {
    isActive = newValue;
  }

  public void deltaHealth(int delta) {
    health += delta;
  }

  public void deltaAttack(int delta) {
    attack += delta;
  }
}
