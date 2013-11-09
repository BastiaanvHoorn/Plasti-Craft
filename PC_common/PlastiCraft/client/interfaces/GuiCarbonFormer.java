package plasticraft.client.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import plasticraft.lib.References;
import plasticraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCarbonFormer extends GuiContainer{

	private int time;
	
	public GuiCarbonFormer(InventoryPlayer invPlayer, TileEntityCarbonFormer carbonformer) {
		super(new ContainerCarbonformer(invPlayer,carbonformer));
		xSize = 176;
		ySize = 154;
	}
	
	private static final ResourceLocation texture = new ResourceLocation("pc", "textures/gui/carbonformer.png");
	private static final ResourceLocation plasticTexture = new ResourceLocation(References.MOD_ID.toLowerCase() , "textures/blocks/fluidplastic.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y){
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		if(TileEntityCarbonFormer.tank.getCapacity() - TileEntityCarbonFormer.tank.getFluidAmount() >= 750 ){
			time = TileEntityCarbonFormer.getTime();
			int barWith = (int) (0.85 * time);
			if(time > 0){
				drawTexturedModalRect(48,17, 176, 0, barWith, 15);
			}
		}
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(plasticTexture);
		if(TileEntityCarbonFormer.tank.getFluidAmount() != 0){
			drawTexturedModalRect(100, 17 + (TileEntityCarbonFormer.tank.getCapacity()/ 500) - (TileEntityCarbonFormer.tank.getFluidAmount() / 500) , 0, 0, 16, TileEntityCarbonFormer.tank.getFluidAmount() / 500);
		}
	}


}
