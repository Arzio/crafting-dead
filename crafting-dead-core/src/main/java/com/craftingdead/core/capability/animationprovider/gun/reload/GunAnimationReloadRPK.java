package com.craftingdead.core.capability.animationprovider.gun.reload;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class GunAnimationReloadRPK extends GunAnimationReload {

  protected float rotation1 = 0F;
  protected float lastRotation1 = 0F;
  protected float maxRotation1 = 55;

  protected float trans1 = -1F;
  protected float lastTrans1 = 0F;
  protected float maxTrans1 = 0.3F;

  protected boolean up = true;

  /**
   * When set to false, the clip will be loaded into the gun
   */
  public boolean ejectingClip = false;

  public GunAnimationReloadRPK() {
    this.ejectingClip = true;
    trans1 = 0F;
  }

  public GunAnimationReloadRPK(boolean par1) {
    this.ejectingClip = par1;
    trans1 = this.ejectingClip ? 0F : -1F;
  }

  public void setEjectingClip(boolean par1) {
    this.ejectingClip = par1;
    trans1 = this.ejectingClip ? 0F : -1F;
  }

  @Override
  public void onUpdate(Minecraft par1, LivingEntity par2, ItemStack par3, float progress) {
    if (progress * this.getMaxAnimationTick() == 20) {
      up = false;
    }

    lastRotation1 = rotation1;
    lastTrans1 = trans1;

    float roation1Speed = 10F;
    float transSpeed = 0.1F;

    if (this.ejectingClip) {
      trans1 -= transSpeed;
    } else {
      trans1 += transSpeed;

      if (trans1 > 0) {
        trans1 = 0;
      }
    }

    if (up) {
      rotation1 += roation1Speed;
    } else {
      rotation1 -= roation1Speed;
    }

    if (rotation1 > maxRotation1) {
      rotation1 = maxRotation1;
    }

    if (rotation1 < 0) {
      rotation1 = 0;
    }
  }

  @Override
  public void doRenderHand(ItemStack par1, float par2, boolean par3, MatrixStack matrixStack) {
    if (par3) {
      float progress = (lastRotation1 + (rotation1 - lastRotation1) * par2);
      matrixStack.rotate(new Vector3f(1.0F, 0.0F, 1.0F).rotationDegrees(progress * 0.2F));
    } else {
      float transprogress = lastTrans1 + (trans1 - lastTrans1) * par2;
      matrixStack.translate(-transprogress * 1F, transprogress * 0.5F, transprogress);

      float progress = (lastRotation1 + (rotation1 - lastRotation1) * par2);
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(progress * 0.5F));
    }
  }

  @Override
  public void doRender(ItemStack par1, float par2, MatrixStack matrixStack) {
    float progress = lastRotation1 + (rotation1 - lastRotation1) * par2;

    matrixStack.translate(0.0F, (!ejectingClip ? -(progress / 85) : 0.0F), 0.0F);

    matrixStack
        .rotate(new Vector3f(1.0F, 0.0F, (!ejectingClip ? -1.0F : 0.0F)).rotationDegrees(progress));
  }

  public void doRenderAmmo(ItemStack par1, float par2, MatrixStack matrixStack) {
    if (this.ejectingClip) {
      float transprogress = lastTrans1 + (trans1 - lastTrans1) * par2;
      matrixStack.translate(0, -transprogress, 0);
    } else {
      float transprogress = lastTrans1 + (trans1 - lastTrans1) * par2;
      matrixStack.translate(transprogress, -transprogress, 0);
    }
  }

  @Override
  public void onAnimationStopped(ItemStack par1) {}

  @Override
  public float getMaxAnimationTick() {
    return 30;
  }
}