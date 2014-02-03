package tconstruct.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tconstruct.blocks.logic.FurnaceLogic;
import tconstruct.inventory.FurnaceContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FurnaceGui extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("textures/gui/container/furnace.png");
    private FurnaceLogic logic;

    public FurnaceGui(InventoryPlayer inventory, FurnaceLogic furnace)
    {
        super(new FurnaceContainer(inventory, furnace));
        this.logic = furnace;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer (int par1, int par2)
    {
        String s = this.logic.isInvNameLocalized() ? this.logic.getInvName() : I18n.getStringParams(this.logic.getInvName());
        this.field_146289_q.drawString(s, this.field_146999_f / 2 - this.field_146289_q.getStringWidth(s) / 2, 6, 4210752);
        this.field_146289_q.drawString(I18n.getStringParams("container.inventory"), 8, this.field_147000_g - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void func_146976_a (float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        int i1;

        if (this.logic.isBurning())
        {
            i1 = this.logic.gaugeFuelScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.logic.gaugeProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
}