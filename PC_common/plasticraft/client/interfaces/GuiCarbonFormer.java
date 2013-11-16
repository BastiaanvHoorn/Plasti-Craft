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

	private TileEntityCarbonFormer carbonformer;
	
	public GuiCarbonFormer(InventoryPlayer invPlayer, TileEntityCarbonFormer carbonformer) {
		super(new ContainerCarbonformer(invPlayer,carbonformer));
		this.carbonformer = carbonformer;
		xSize = 176;
		ySize = 154;
	}
	
	private static final ResourceLocation texture = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/gui/carbonformer.png");
	private static final ResourceLocation plasticTexture = new ResourceLocation(References.MOD_ID.toLowerCase() , "textures/blocks/fluidplastic_still.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		int i1;
		
		i1 = this.carbonformer.getCookProgressScaled(34);
		this.drawTexturedModalRect(guiLeft + 48, guiTop + 17, 176, 0, i1, 15);
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y){

	}


}
