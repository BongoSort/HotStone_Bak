/** Demonstration of the Personalized Deck generator.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     2nd Edition
   Author: 
     Henrik Bærbak Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

package demo;

import thirdparty.CardPODO;
import thirdparty.PersonalDeckReader;

public class DemoDeck {

  public static void main(String[] args) {
    System.out.println("=== Demonstration of the Personal Deck Builder ===");

    System.out.println("=== Animal Deck ===");
    // Given a reader of the animaldeck
    PersonalDeckReader reader = new PersonalDeckReader("animaldeck.json");
    // I can read all cards and print them
    for (CardPODO acard : reader) {
      System.out.println("--> " + acard);
    }
    // Also for the second deck
    System.out.println("=== Norse God Deck ===");
    reader = new PersonalDeckReader("allOtherFileNamesAreTheSame.json");
    // I can read all cards and print them
    for (CardPODO acard : reader) {
      System.out.println("--> " + acard);
    }
  }
}
