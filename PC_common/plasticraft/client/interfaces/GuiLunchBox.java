package plasticraft.client.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import plasticraft.items.LunchBox;
import plasticraft.lib.References;

public class GuiLunchBox extends GhostGuiContainer {

	private ResourceLocation texture = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/gui/lunchbox.png");
	
	public GuiLunchBox(EntityPlayer player, IInventory inv, ItemStack stack){
		super(LunchBox.getContainer(player));
		xSize = 176;
		ySize = 154;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}



}
