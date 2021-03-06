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
package com.craftingdead.core.capability.magazine;

import com.craftingdead.core.item.MagazineItem;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class DefaultMagazine implements IMagazine {

  private final MagazineItem magazineItem;
  private int size;

  public DefaultMagazine() {
    throw new UnsupportedOperationException("Specify magazine item");
  }

  public DefaultMagazine(MagazineItem magazineItem) {
    this.magazineItem = magazineItem;
    this.size = magazineItem.getSize();
  }

  @Override
  public CompoundNBT serializeNBT() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.putInt("size", this.size);
    return nbt;
  }

  @Override
  public void deserializeNBT(CompoundNBT nbt) {
    this.size = nbt.getInt("size");
  }

  @Override
  public float getArmorPenetration() {
    return this.magazineItem.getArmorPenetration();
  }

  @Override
  public int getSize() {
    return this.size;
  }

  @Override
  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public void refill() {
    this.size = this.magazineItem.getSize();
  }

  @Override
  public void decrementSize() {
    this.size--;
  }

  @Override
  public Item getNextTier() {
    return this.magazineItem.getNextTier().get();
  }

  @Override
  public boolean hasCustomTexture() {
    return this.magazineItem.hasCustomTexture();
  }
}
