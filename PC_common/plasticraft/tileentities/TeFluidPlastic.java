package plasticraft.tileentities;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import plasticraft.PlastiCraft;
import plasticraft.blocks.Blocks;

public class TeFluidPlastic extends TileEntity{

	private int counter;
	private Random r;
	private int time;
	
	public TeFluidPlastic(){
		this.counter = 0;
		this.r = new Random();
		this.time = 200 + r.nextInt(201);
		
	}
	@Override
	public void updateEntity(){
		if(!this.worldObj.isRemote){
			if(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) == 0){
				this.counter++;
				if(this.counter >= this.time){
					this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.BlockPlastic.blockID);
				}
			}
		}
	}
	
}
