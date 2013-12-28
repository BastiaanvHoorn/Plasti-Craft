package plasticraft.client.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import plasticraft.PlastiCraft;
import plasticraft.lib.References;

public class GuiGrindStone extends GuiContainer {

	private ResourceLocation texture = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/gui/grindstone.png");
	
	public GuiGrindStone(InventoryPlayer player){
		super(new ContainerGrindStone(player));
		xSize = 176;
		ySize = 154;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		PlastiCraft.info("drawGuiContainerBackgroundLayer");
	}



}
