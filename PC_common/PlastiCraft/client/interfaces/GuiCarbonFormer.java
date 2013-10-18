package PlastiCraft.client.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import PlastiCraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCarbonFormer extends GuiContainer{

	public GuiCarbonFormer(InventoryPlayer invPlayer, TileEntityCarbonFormer carbonformer) {
		super(new ContainerCarbonformer(invPlayer,carbonformer));
		xSize = 176;
		ySize = 154;
	}
	
	private static final ResourceLocation texture = new ResourceLocation("pc", "textures/gui/carbonformer.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}

}
