package plasticraft.tileentities;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import plasticraft.blocks.PCBlocks;
import plasticraft.lib.References;

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
			if(References.doStill){
				if(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) == 0){
					this.counter++;
					if(this.counter >= this.time){
						this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, PCBlocks.BlockPlastic);
					}
				}
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound){
		compound.setInteger("time", this.time);
		compound.setInteger("counter", this.counter);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound){
		this.counter = compound.getInteger("counter");
		this.time = compound.getInteger("time");
	}
}
