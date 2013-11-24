package plasticraft.tileentities;

import net.minecraft.tileentity.TileEntity;
import plasticraft.PlastiCraft;

public class TeFluidPlastic extends TileEntity{

	private int counter;
	
	public TeFluidPlastic(){
		this.counter = 0;
	}
	@Override
	public void updateEntity(){
		if(!this.worldObj.isRemote){
			if(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) == 0){
				this.counter++;
				if(this.counter >= 200){
					this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, PlastiCraft.BlockPlastic.blockID);
				}
			}
		}
	}
	
}
