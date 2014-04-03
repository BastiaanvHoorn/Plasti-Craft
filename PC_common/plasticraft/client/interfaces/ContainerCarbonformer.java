package plasticraft.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import plasticraft.PlastiCraft;
import plasticraft.tileentities.TileEntityCarbonFormer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCarbonformer extends Container{

	private TileEntityCarbonFormer carbonformer;
	
	private int lastCookTime;
	private int lastburnTime;

	private int lastFluidAmount;
	public ContainerCarbonformer(InventoryPlayer invPlayer, TileEntityCarbonFormer carbonformer){
		this.carbonformer = carbonformer;
		
		for(int x=0; x < 9; x++){
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 130));
		}
		
		for(int y = 0; y<3; y++){
			for(int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(invPlayer,x + y * 9 + 9,8 + 18 * x, 72 + y * 18));
			}
		}
		addSlotToContainer(new SlotCarbon(carbonformer, 0, 26, 17));
		addSlotToContainer(new SlotFuel(carbonformer, 1, 26, 47));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return carbonformer.isUseableByPlayer(entityplayer);
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i){
		return null;
	}

	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int par1, int par2){
		if(par1 == 0){
			this.carbonformer.cookTime = par2;
		}
		
		if(par1 == 1){
			this.carbonformer.burnTime = par2;
		}
		
		if(par1 == 2){
			this.updateFluid(new FluidStack(PlastiCraft.plastic_fluid, par2));
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	public void updateFluid( FluidStack stack){
		this.carbonformer.tank.setFluid(stack);
	}
	
	@Override
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); i++){
			ICrafting crafting = (ICrafting)this.crafters.get(i);
			
			if(this.lastCookTime != this.carbonformer.cookTime){
				crafting.sendProgressBarUpdate(this, 0, this.carbonformer.cookTime);
			}
			
			if(this.lastburnTime != this.carbonformer.burnTime){
				crafting.sendProgressBarUpdate(this, 1, this.carbonformer.burnTime);
			}
			
			if(this.lastFluidAmount != this.carbonformer.tank.getFluidAmount()){
				crafting.sendProgressBarUpdate(this, 2, this.carbonformer.tank.getFluidAmount());
			}
		}
	}
	
}
