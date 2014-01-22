package plasticraft.client.interfaces;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import plasticraft.lib.References;
import plasticraft.tileentities.TeTrashCan;

public class GuiTrashCan extends GuiContainer {
	
	private static final ResourceLocation texture = new ResourceLocation(References.MOD_ID.toLowerCase(),"textures/gui/lunchbox.png");
	
	public GuiTrashCan(InventoryPlayer inventory, TeTrashCan te) {
		super(new ContainerTrashCan(inventory, te));
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
