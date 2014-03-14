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
import plasticraft.items.PCItems;
import plasticraft.tileentities.TeGrindStone;

public class ContainerGrindStone extends Container{

	private TeGrindStone teGrindStone;
	
	private int lastDurability;
	
	public ContainerGrindStone(InventoryPlayer player, TeGrindStone grindStone) {
		this.teGrindStone = grindStone;
		
		for(int x = 0; x < 9; x++){
			addSlotToContainer(new Slot(player, x, 8 + 18 * x, 130));
		}
		
		for(int y = 0; y < 3; y++){
			for(int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + 18 * x, 72 + y * 18));
			}
		}
		
		addSlotToContainer(new SlotKnife(grindStone, 0, 86, 18));
		addSlotToContainer(new SlotGrindFrame(grindStone, 1, 50, 18));
		
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
	
	@Override
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); i++){
			ICrafting crafting = (ICrafting)this.crafters.get(i);
			if (this.lastDurability != this.teGrindStone.getDurability())
			{
				crafting.sendProgressBarUpdate(this, 0, this.teGrindStone.getDurability());
			}
		}
	}
	
	public TeGrindStone getTe(){
		return this.teGrindStone;
	}
	
	/*@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int par1, int par2)
	{
		if (this.teGrindStone.getStackInSlot(0) != null)
		{
			this.teGrindStone.getStackInSlot(0).setItemDamage(par2);
		}
	}*/
}
