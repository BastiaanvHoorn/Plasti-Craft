package plasticraft.entity;

import plasticraft.PlastiCraft;
import plasticraft.items.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import plasticraft.items.Items;

public class EntityCarbonite extends EntityMob {

	public EntityCarbonite(World par1World) {
		super(par1World);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(3, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(30.0D);
	}
	
	@Override
	protected boolean isAIEnabled(){
		return true;
	}
	
	@Override
	public int getMaxSafePointTries(){
		return this.getAttackTarget() == null ? 3: 3 + (int)(this.getHealth() - 1.0F);
	}
	
	protected int getDropItemId(){
		return Items.plastic_Item.itemID;
	}
	
	@Override
	protected int getDropItemId(){
		return Items.plastic_Item.itemID;
	}
	
	@Override
	protected boolean canDespawn(){
		return false;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity){
		return true;
	}

}
