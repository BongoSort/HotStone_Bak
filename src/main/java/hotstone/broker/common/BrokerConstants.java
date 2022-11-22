/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.broker.common;

/** Constants across the client and the server:
 * which port are we communicating across, and which
 * HTTP path are communication over?
 */
public class BrokerConstants {
  public static final String HOTSTONE_TUNNEL_PATH = "/hotstone";
  public static final int HOTSTONE_PORT = 5555;
}
