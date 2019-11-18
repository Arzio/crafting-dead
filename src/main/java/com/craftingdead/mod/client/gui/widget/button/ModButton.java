package com.craftingdead.mod.client.gui.widget.button;

import java.awt.Color;
import com.craftingdead.mod.client.util.RenderUtil;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.math.MathHelper;

public class ModButton extends Button {

  private static final int HOVER_COLOUR = 0xcb171e;
  private static final int COLOUR = 0xE51C23;
  private static final int SHADOW_COLOR = 0xA01318;

  private static final float FADE_LENGTH = 20 * 1F;
  private float fadeTimer;

  private boolean wasHovered;

  public ModButton(int x, int y, int width, int height, String text, IPressable onPress) {
    super(x, y, width, height, text, onPress);
  }

  @Override
  public void renderButton(int mouseX, int mouseY, float partialTicks) {
    if (this.wasHovered != this.isHovered) {
      this.fadeTimer = 0;
    }
    this.wasHovered = this.isHovered;
    if (this.fadeTimer < FADE_LENGTH) {
      this.fadeTimer++;
    }

    float fadePct = this.fadeTimer / FADE_LENGTH;

    int i = this.isHovered ? COLOUR : HOVER_COLOUR;
    Color colour1 = new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF,
        MathHelper.ceil(this.alpha * 255.0F));
    i = this.isHovered ? HOVER_COLOUR : COLOUR;
    Color colour2 = new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF,
        MathHelper.ceil(this.alpha * 255.0F));
    Color colour = RenderUtil.lerp(colour1, colour2, fadePct);

    int borderHeight = 2;
    RenderUtil
        .drawGradientRectangle(this.x, this.y, this.x + this.width,
            this.y + this.height - borderHeight, colour.getRGB(), colour.getRGB());
    RenderUtil
        .drawGradientRectangle(this.x, this.y + this.height - borderHeight, this.x + this.width,
            this.y + this.height, SHADOW_COLOR | MathHelper.ceil(this.alpha * 255.0F) << 24,
            SHADOW_COLOR | MathHelper.ceil(this.alpha * 255.0F) << 24);


    GlStateManager.enableBlend();

    this
        .drawCenteredString(Minecraft.getInstance().fontRenderer, this.getMessage(),
            this.x + this.width / 2, this.y + (this.height - 8) / 2,
            0xFFFFFF | MathHelper.ceil(this.alpha * 255.0F) << 24);
    GlStateManager.disableBlend();
  }
}