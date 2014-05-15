package plasticraft.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import network.ClonePickupPacket;
import plasticraft.PlastiCraft;
import plasticraft.items.PCItems;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class EntityClone extends EntityLiving implements IInventory{
	
	private boolean printed;
	private ItemStack[] items;
	private int destinationX;
	private int destinationY;
	private int destinationZ;
	private boolean destination;
	
	@Override
	public boolean interact(EntityPlayer player){
		if(player.inventory.getCurrentItem() != null){
			if(player.inventory.getCurrentItem().getItem().equals(PCItems.LocationSetter)){
				if(player.inventory.getCurrentItem().stackTagCompound != null){
					NBTTagCompound compound = player.inventory.getCurrentItem().stackTagCompound;
					this.setAIDestination((double)compound.getInteger("x"), (double)compound.getInteger("y"), (double)compound.getInteger("z"));
					return true;
				}
			}else if(player.inventory.getCurrentItem().getItem().equals(PCItems.BlockBreaker)){
				if(player.inventory.getCurrentItem().stackTagCompound != null){
					NBTTagCompound compound = player.inventory.getCurrentItem().stackTagCompound;
					this.breakBlock(compound.getInteger("x"), compound.getInteger("y"), compound.getInteger("z"));
					return true;
				}
			}
		}
		if(!this.worldObj.isRemote ){
			FMLNetworkHandler.openGui(player, PlastiCraft.instance, 1, this.worldObj, this.getEntityId(), (int)this.posY, (int)this.posZ);
		}
		return true;
	}
	
	
	public EntityClone(World par1World) {
		super(par1World);
		items = new ItemStack[2];
	}
	
	@Override
	public boolean isAIEnabled(){
		return true;
	}
	
	@Override
	public int getSizeInventory() {
		if(getPrinted()){
			return 2;
		}
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);
		if(itemstack != null){
			if(itemstack.stackSize <= count){
				this.setInventorySlotContents(i, null);
			}else{
				itemstack = itemstack.splitStack(count);
			}
		}
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		this.setInventorySlotContents(i, null);
		return item;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		items[i] = stack;
		
		if(stack != null && stack.stackSize > getInventoryStackLimit()){
			stack.stackSize = this.getInventoryStackLimit();
			this.items[i] = stack;
		}
	}

	@Override
	public String getInventoryName() {
		return "Clone";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.isDead ? false : entityplayer.getDistanceSqToEntity(this) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}
	
	public void setPrinted(boolean flag){
		this.printed = flag;
	}
	
	public boolean getPrinted(){
		return printed;
	}
	
	public void setAIDestination(double x, double y, double z){
		this.getNavigator().tryMoveToXYZ(x, y, z, 0.5D);
	}
	
	public void breakBlock(int x, int y, int z){
		if(!this.worldObj.isRemote){
			setAIDestination((double)x, (double)y, (double)z);
			this.destinationX = x;
			this.destinationY = y;
			this.destinationZ = z;
			this.destination = true;
		}
	}
	
	public void drobBlock(int x, int y, int z){
		if(this.worldObj.isAirBlock(x, y, z) == false){
			ArrayList<ItemStack> items = this.worldObj.getBlock(x, y, z).getDrops(this.worldObj, x, y, z, this.worldObj.getBlockMetadata(x, y, z), 0);
			for(int i = 0;i < items.size(); i++){
				ItemStack item = items.get(i);
				float spawnx = x + this.worldObj.rand.nextFloat();
				float spawny = y + this.worldObj.rand.nextFloat();
				float spawnz = z + this.worldObj.rand.nextFloat();
			
				EntityItem entity = new EntityItem(this.worldObj, spawnx, spawny, spawnz, item);
		
				float mult = 0.05F;
				
				entity.motionX = (-0.5F + this.worldObj.rand.nextFloat()) * mult;
				entity.motionY = (4 + this.worldObj.rand.nextFloat()) * mult;
				entity.motionZ = (-0.5F + this.worldObj.rand.nextFloat()) * mult;
			
				this.worldObj.spawnEntityInWorld(entity);
			}
		}
		this.worldObj.setBlockToAir(x, y, z);
	}
	
	@Override
	public void onUpdate(){
		if(!this.worldObj.isRemote){
			if(this.destination){
				if(this.getDistance((double)this.destinationX, (double)this.destinationY, (double)this.destinationZ) <= 3){
					this.drobBlock(this.destinationX, this.destinationY, this.destinationZ);
					this.destination = false;
				}
			}
		}
		if(!this.worldObj.isRemote){	
		Iterator iterator = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(0.5D, 0.0D, 0.5D)).iterator();

	        while (iterator.hasNext())
	        {
	            EntityItem entity = (EntityItem)iterator.next();
	            entity.prevPosX = entity.posX;
	            entity.prevPosY = entity.posY;
	            entity.prevPosZ = entity.posZ;
	            entity.motionY -= 0.03999999910593033D;
	            entity.noClip = true;
	            entity.moveEntity(entity.motionX, entity.motionY, entity.motionZ);
	            boolean flag = (int)entity.prevPosX != (int)entity.posX || (int)entity.prevPosY != (int)entity.posY || (int)entity.prevPosZ != (int)entity.posZ;
	        }
	        
	        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(1.0D, 0.5D, 1.0D));
	        
	        if(list != null){
	        	for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity = (Entity)list.get(i);

                    if (!entity.isDead && entity instanceof EntityItem)
                    {
                    	this.tryToPickupEntity((EntityItem)entity);
                    }
                }
	        }
		}
		
		super.onUpdate();
	}


	public void tryToPickupEntity(EntityItem entity) {
		ItemStack stack = entity.getEntityItem();
		if(stack != null && stack.stackSize > 0 && stack.getItem() != null){
			try{
				int i;
				if(stack.isItemDamaged()){
					i = this.getFirstEmptyStack();
					
					if(i >= 0){
						this.items[i] = ItemStack.copyItemStack(stack);
						
						this.items[i].animationsToGo = 5;
						stack.stackSize = 0;
					}
				}else{
					do
                    {
                        i = stack.stackSize;
                        stack.stackSize = this.storePartialItemStack(stack);
                    }
                    while (stack.stackSize > 0 && stack.stackSize < i);
				}
			}catch(Exception e){
				PlastiCraft.info("Unable to pickup item");			
			}
		}
		for(int i = 0; i < this.getSizeInventory(); i++){
			if(this.getStackInSlot(i) != null){
				PlastiCraft.pipeLine.sendToDimension(new ClonePickupPacket(this.getStackInSlot(i),this.getEntityId(), i), this.worldObj.provider.dimensionId);
			}
		}
	}
	
	public int storePartialItemStack(ItemStack stack) {
		Item item = stack.getItem();
        int i = stack.stackSize;
        int j;

        if (stack.getMaxStackSize() == 1)
        {
            j = this.getFirstEmptyStack();

            if (j < 0)
            {
                return i;
            }
            else
            {
                if (this.items[j] == null)
                {
                    this.items[j] = ItemStack.copyItemStack(stack);
                }

                return 0;
            }
        }else{
        	j = this.storeItemStack(stack);
        	
        	if(j < 0){
        		j = this.getFirstEmptyStack();
        	}
        	
        	if(j < 0){
        		return i;
        	}else{
        		if(this.items[j] == null){
        			this.items[j] = new ItemStack(item, 0, stack.getItemDamage());
        			
        			if (stack.hasTagCompound())
                    {
                        this.items[j].setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
                    }
        			
        			int k = i;
        			
        			if (i > this.items[j].getMaxStackSize() - this.items[j].stackSize)
                    {
                        k = this.items[j].getMaxStackSize() - this.items[j].stackSize;
                    }
        			
        			if (k > this.getInventoryStackLimit() - this.items[j].stackSize)
                    {
                        k = this.getInventoryStackLimit() - this.items[j].stackSize;
                    }
        			
        			if (k == 0)
                    {
                        return i;
                    }else{
                    	i -= k;
                        this.items[j].stackSize += k;
                        this.items[j].animationsToGo = 5;
                        return i;
                    }
        		}
        	}
        }
		return 0;
	}


	public int storeItemStack(ItemStack stack) {
		for (int i = 0; i < this.getSizeInventory(); ++i)
        {
            if (this.items[i] != null && this.items[i].getItem() == stack.getItem() && this.items[i].isStackable() && this.items[i].stackSize < this.items[i].getMaxStackSize() && this.items[i].stackSize < this.getInventoryStackLimit() && (!this.items[i].getHasSubtypes() || this.items[i].getItemDamage() == stack.getItemDamage()) && ItemStack.areItemStackTagsEqual(this.items[i], stack))
            {
                return i;
            }
        }

        return -1;
	}


	public int getFirstEmptyStack(){
		for (int i = 0; i < this.getSizeInventory(); ++i)
        {
            if (this.items[i] == null)
            {
                return i;
            }
        }

        return -1;
	}
	
	@Override
	public void onDeath(DamageSource par1DamageSource){
		for(int i = 0; i < this.getSizeInventory(); i++){
			ItemStack stack = this.getStackInSlotOnClosing(i);
			if(stack != null){
				EntityItem entity = new EntityItem(this.worldObj, this.posX + this.worldObj.rand.nextDouble(), this.posY + this.worldObj.rand.nextDouble(), this.posZ + this.worldObj.rand.nextDouble(), stack);
				entity.motionX = this.worldObj.rand.nextDouble() * 0.05;
				entity.motionY = (4 + this.worldObj.rand.nextDouble()) * 0.05;
				entity.motionZ = this.worldObj.rand.nextDouble() * 0.05;
				entity.age = 0;
				this.worldObj.spawnEntityInWorld(entity);
				PlastiCraft.info(entity.motionY);
				PlastiCraft.info(entity.posX);
				PlastiCraft.info(entity.posY);
				PlastiCraft.info(entity.posZ);
			}
		}
	}
	
}
