/**
 * Crafting Dead
 * Copyright (C) 2020  Nexus Node
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.craftingdead.immerse.game;

import java.io.IOException;
import com.craftingdead.core.capability.living.Player;
import net.minecraft.network.PacketBuffer;

public interface IGame<T extends ITeam> {

  default void load() {}

  default void unload() {}

  default void tick() {}

  void write(PacketBuffer packetBuffer, boolean writeAll) throws IOException;

  void read(PacketBuffer packetBuffer) throws IOException;

  boolean isDirty();

  T getTeam(Player<?> player);

  void setTeam(Player<?> player, T team);

//  default Optional<ISchematic> getMap() {
//    return Optional.empty();
//  }

  String getDisplayName();

  GameType getGameType();
}
