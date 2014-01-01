package plasticraft.client.interfaces;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import plasticraft.PlastiCraft;
import plasticraft.tileentities.TeGrindStone;

public class ContainerGrindStone extends Container{

	private InventoryPlayer player;
	
	private TeGrindStone teGrindStone;
	
	private int lastGrindTime;
	
	public ContainerGrindStone(InventoryPlayer player, TeGrindStone grindStone) {
		this.player = player;
		this.teGrindStone = grindStone;
		
		for(int x = 0; x < 9; x++){
			addSlotToContainer(new Slot(this.player, x, 8 + 18 * x, 130));
		}
		
		for(int y = 0; y < 3; y++){
			for(int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(this.player, x + y * 9 + 9, 8 + 18 * x, 72 + y * 18));
			}
		}
		
		addSlotToContainer(new SlotKnife(grindStone, 0, 44, 21));
		addSlotToContainer(new SlotGrindStone(this.player.player, grindStone, 1, 116, 21));
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return teGrindStone.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i)
	{
		return null;
	}

	@SideOnly(Side.CLIENT)
	
	@Override
	public void updateProgressBar(int par1, int par2){
		this.teGrindStone.grindTime = par2;
	}
	
	@Override
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); i++){
			ICrafting crafting = (ICrafting)this.crafters.get(i);
			
			if(this.lastGrindTime != this.teGrindStone.grindTime)
			{
				crafting.sendProgressBarUpdate(this, 0, this.teGrindStone.grindTime);
			}
		}
	}
}
