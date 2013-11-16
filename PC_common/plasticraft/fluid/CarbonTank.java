package plasticraft.fluid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import plasticraft.PlastiCraft;
import plasticraft.tileentities.TileEntityCarbonFormer;

public class CarbonTank implements IFluidTank{
	
	private FluidStack fluid;
	private int capacity;
	private TileEntity te;
	
	public CarbonTank(int amount, TileEntityCarbonFormer tile){
		this.fluid = null;
		this.capacity = amount;
		this.te = (TileEntity) tile;
	}
	
	public CarbonTank(FluidStack fluid,int amount, TileEntityCarbonFormer tile){
		this.fluid = fluid;
		this.capacity = amount;
		this.te = (TileEntity) tile;
	}
	
	public CarbonTank(Fluid fluid, int amount, int capacity, TileEntityCarbonFormer tile){
		this.fluid = new FluidStack(fluid, amount);
		this.capacity = capacity;
		this.te = (TileEntity) tile;
	}
	
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        if (this.fluid != null)
        {
            this.fluid.writeToNBT(nbt);
        }
        else
        {
            nbt.setString("Empty", "");
        }
        return nbt;
    }
    
    public CarbonTank readFromNBT(NBTTagCompound nbt)
    {
        if (!nbt.hasKey("Empty"))
        {
            FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);

            if (this.fluid != null)
            {
                this.setFluid(fluid);
            }
        }
        return this;
    }

	public void setFluid(FluidStack fluid2) {
		this.fluid = fluid2;
	}
	
	public void setCapacity(int amount){
		this.capacity = amount;
	}

	public FluidTankInfo getInfo() {
		return new FluidTankInfo((IFluidTank) this);
	}

	public int getFluidAmount() {
        if (this.fluid == null)
        {
            return 0;
        }
        return this.fluid.amount;
	}

	public FluidStack getFluid() {
		if(this.fluid != null){
			return this.fluid;
		}else{
			return null;
		}
	}

	public int getCapacity() {
		return this.capacity;
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		 if (resource == null)
	        {
	            return 0;
	        }
		 if(resource.getFluid() == PlastiCraft.plastic_fluid){
	        if (!doFill)
	        {
	            if (this.fluid == null)
	            {
	                return Math.min(this.capacity, resource.amount);
	            }

	            if (!this.fluid.isFluidEqual(resource))
	            {
	                return 0;
	            }

	            return Math.min(this.capacity - this.fluid.amount, resource.amount);
	        }

	        if (this.fluid == null)
	        {
	            this.fluid = new FluidStack(resource, Math.min(this.capacity, resource.amount));

	            if (this.te != null)
	            {
	                FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, this.te.worldObj, this.te.xCoord, this.te.yCoord, this.te.zCoord, (IFluidTank)this));
	            }
	            return this.fluid.amount;
	        }

	        if (!this.fluid.isFluidEqual(resource))
	        {
	            return 0;
	        }
	        int filled = this.capacity - this.fluid.amount;

	        if (resource.amount < filled)
	        {
	            this.fluid.amount += resource.amount;
	            filled = resource.amount;
	        }
	        else
	        {
	            this.fluid.amount = this.capacity;
	        }

	        if (this.te != null)
	        {
	            FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(this.fluid, this.te.worldObj, this.te.xCoord, this.te.yCoord, this.te.zCoord, (IFluidTank)this));
	        }
	        return filled;
		 }else{
			 return 0;
		 }
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		 if (this.fluid == null)
	        {
	            return null;
	        }

	        int drained = maxDrain;
	        if (this.fluid.amount < drained)
	        {
	            drained = this.fluid.amount;
	        }

	        FluidStack stack = new FluidStack(this.fluid, drained);
	        if (doDrain)
	        {
	            this.fluid.amount -= drained;
	            if (this.fluid.amount <= 0)
	            {
	                this.fluid = null;
	            }

	            if (this.te != null)
	            {
	                FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluid, this.te.worldObj, this.te.xCoord, this.te.yCoord, this.te.zCoord, (IFluidTank)this));
	            }
	        }
	        return stack;
	}

}
