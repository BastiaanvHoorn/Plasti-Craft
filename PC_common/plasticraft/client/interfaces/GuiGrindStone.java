package plasticraft.client.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import network.PacketHandler;

import org.lwjgl.opengl.GL11;

import plasticraft.lib.References;
import plasticraft.tileentities.TeGrindStone;

import com.google.common.collect.Lists;

public class GuiGrindStone extends GuiContainer {

	private ResourceLocation texture = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/gui/grindstone.png");
	
	private TeGrindStone teGrindStone;
	private GuiButton button = new GuiButton(0, this.guiLeft + 61, this.guiTop + 37, 54, 20, "Grind");
	private int progressBar;
	private int durability;
	private EntityPlayer player;
	
	public GuiGrindStone(InventoryPlayer player, TeGrindStone grindstone){
		super(new ContainerGrindStone(player, grindstone));
		this.teGrindStone = grindstone;
		this.player = player.player;
		xSize = 176;
		ySize = 154;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		button.xPosition = guiLeft + 49;
		button.yPosition = guiTop + 37;
		
		int newProgressBar = this.teGrindStone.getDurability(43);

		if (newProgressBar > progressBar)
		{
			if ((newProgressBar - progressBar) / 9 < 1)
			{
				++progressBar;
			}
			else
			{
				progressBar += (newProgressBar - progressBar) / 9;
			}
		}
		else if (newProgressBar < progressBar)
		{
			if ((progressBar - newProgressBar) / 9 < 1)
			{
				--progressBar;
			}
			else
			{
				progressBar -= (progressBar - newProgressBar) / 9;
			}
		}
		
		this.drawTexturedModalRect(guiLeft + 118, guiTop + 57 - progressBar, 176, 43 - progressBar, 11, progressBar);
		
		boolean enableButton = false;
		
		if (teGrindStone.getStackInSlot(0) != null && !this.teGrindStone.isActive)
		{
			if (teGrindStone.getStackInSlot(0).getItemDamage() != 0)
			{
				if (player.capabilities.isCreativeMode)
				{
					enableButton = true;
				}
				else
				{
					enableButton = teGrindStone.getStackInSlot(1) != null;
				}
			}
		}
		
		button.enabled = enableButton;
		
		if (x > guiLeft + 116 && x < guiLeft + 130 && y > guiTop + 14 && y < guiTop + 58)
		{
			String color;
			int durability = this.teGrindStone.getDurability();
			String hoverText = String.valueOf(durability) + "/64";
			
			if (durability == -1)
			{
				color = GuiColor.LIGHTBLUE.toString();
				hoverText = "No knife in slot";
			}
			else if (durability < 21)
			{
				color = GuiColor.RED.toString();
			}
			else if (durability < 43)
			{
				color = GuiColor.ORANGE.toString();
			}
			else
			{
				color = GuiColor.LIME.toString();
			}
			
			this.drawHoveringText(Lists.newArrayList(color + hoverText, "Durability"),x , y , fontRendererObj);
			
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString(this.teGrindStone.getInvName(), 50, 6, 4210752);
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(button);
	}
	
	protected void actionPerformed(GuiButton button)
	{
		PacketHandler.sendInterfacePacket((byte)2,(byte)button.id);
	}
}
