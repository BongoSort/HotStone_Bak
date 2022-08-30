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

package hotstone.standard;

/** Placeholder class for a set of HotStone domain wide constants.
 * Note some of these constants are actually variant specific, and
 * could beneficially be moved out of this more general purpose
 * constant container. */

public class GameConstants {
  public static final int HERO_MAX_HEALTH = 21;
  public static final int HERO_POWER_COST = 2;
  public static final int HERO_HEALTH_PENALTY_ON_EMPTY_DECK = 2;

  // The alpha variant's hero type
  public static final String BABY_HERO_TYPE = "Baby";

  // The first variants use cooks as hero types from different nations
  public static final String THAI_CHEF_HERO_TYPE = "Bunyasaranand";
  public static final String DANISH_CHEF_HERO_TYPE = "Meyer";
  public static final String FRENCH_CHEF_HERO_TYPE = "Bocuse";
  public static final String ITALIAN_CHEF_HERO_TYPE = "Bottura";

  // Card names for Alpha
  public static final String DOS_CARD = "Dos";
  public static final String UNO_CARD = "Uno";
  public static final String TRES_CARD = "Tres";
  public static final String CUATRO_CARD = "Cuatro";
  public static final String CINCO_CARD = "Cinco";
  public static final String SEIS_CARD = "Seis";
  public static final String SIETE_CARD = "Siete";

  // Card Names for Delta
  public static final String BROWN_RICE_CARD = "Brown-Rice";
  public static final String FRENCH_FRIES_CARD = "French-Fries";
  public static final String GREEN_SALAD_CARD = "Green-Salad";
  public static final String TOMATO_SALAD_CARD = "Tomato-Salad";
  public static final String POKE_BOWL_CARD = "Poke-Bowl";
  public static final String PUMPKIN_SOUP_CARD = "Pumpkin-Soup";
  public static final String NOODLE_SOUP_CARD = "Noodle-Soup";
  public static final String SPRING_ROLLS_CARD = "Spring-Rolls";
  public static final String BAKED_SALMON_CARD = "Baked-Salmon";
  public static final String CHICKEN_CURRY_CARD = "Chicken-Curry";
  public static final String BEEF_BURGER_CARD = "Beef-Burger";
  public static final String FILET_MIGNON_CARD = "Filet-Mignon";

  // Hero card
  public static final String SOVS_CARD = "Sovs";

  // Card Names for Theta
  public static final String SHRIMP_COCKTAIL_CARD = "Shrimp-Cocktail";
  public static final String TZATZIKI_CARD = "Tzatziki";
  public static final String LASAGNA_CARD = "Lasagna";
  public static final String MUSLI_BAR_CARD = "Musli-Bar";
}
