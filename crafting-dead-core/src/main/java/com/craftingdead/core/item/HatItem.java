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
package com.craftingdead.core.item;

import java.util.List;
import javax.annotation.Nullable;
import com.craftingdead.core.capability.ModCapabilities;
import com.craftingdead.core.capability.SimpleCapabilityProvider;
import com.craftingdead.core.capability.hat.DefaultHat;
import com.craftingdead.core.util.Text;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class HatItem extends Item {

  private final float headshotReductionPercentage;

  private final boolean immuneToFlashes;

  private final boolean immuneToGas;

  private final boolean nightVision;

  public HatItem(Properties properties) {
    super(properties);
    this.headshotReductionPercentage = properties.headshotReductionPercentage;
    this.immuneToFlashes = properties.immuneToFlashes;
    this.immuneToGas = properties.immuneToGas;
    this.nightVision = properties.nightVision;

  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> lore,
      ITooltipFlag tooltipFlag) {
    if (this.headshotReductionPercentage > 0.0F) {
      ITextComponent percentageText =
          Text.of(String.format("%.1f", this.headshotReductionPercentage) + "%")
              .mergeStyle(TextFormatting.RED);

      lore.add(Text.translate("item_lore.hat_item.headshot_reduction")
          .mergeStyle(TextFormatting.GRAY)
          .append(percentageText));
    }
    if (this.immuneToFlashes) {
      lore.add(
          Text.translate("item_lore.hat_item.immune_to_flashes").mergeStyle(TextFormatting.GRAY));
    }
    if (this.immuneToGas) {
      lore.add(Text.translate("item_lore.hat_item.immune_to_gas").mergeStyle(TextFormatting.GRAY));
    }
    if (this.nightVision) {
      lore.add(
          Text.translate("item_lore.hat_item.has_night_vision").mergeStyle(TextFormatting.GRAY));
    }
  }

  @Override
  public ICapabilityProvider initCapabilities(ItemStack itemStack, @Nullable CompoundNBT nbt) {
    return new SimpleCapabilityProvider<>(new DefaultHat(this.nightVision,
        this.headshotReductionPercentage, this.immuneToFlashes, this.immuneToGas),
        () -> ModCapabilities.HAT);
  }

  public static class Properties extends Item.Properties {

    private float headshotReductionPercentage;
    private boolean immuneToFlashes;
    private boolean immuneToGas;
    private boolean nightVision;

    public Properties setHeadshotReductionPercentage(float headshotReductionPercentage) {
      this.headshotReductionPercentage = headshotReductionPercentage;
      return this;
    }

    public Properties setNightVision(boolean nightVision) {
      this.nightVision = nightVision;
      return this;
    }

    public Properties setImmuneToFlashes(boolean immuneToFlashes) {
      this.immuneToFlashes = immuneToFlashes;
      return this;
    }

    public Properties setImmuneToGas(boolean immuneToGas) {
      this.immuneToGas = immuneToGas;
      return this;
    }
  }
}
