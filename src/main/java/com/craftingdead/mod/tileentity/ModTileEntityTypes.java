package com.craftingdead.mod.tileentity;

import com.craftingdead.mod.CraftingDead;
import com.craftingdead.mod.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CraftingDead.ID)
@Mod.EventBusSubscriber(modid = CraftingDead.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntityTypes {

  public static final TileEntityType<?> LOOT = null;

  @SubscribeEvent
  public static void handle(RegistryEvent.Register<TileEntityType<?>> event) {
    event.getRegistry().registerAll(TileEntityType.Builder //
        .create(TileEntityLoot::new, ModBlocks.RESIDENTIAL_LOOT) //
        .build(null) //
        .setRegistryName(new ResourceLocation(CraftingDead.ID, "loot")) //
    );
  }
}