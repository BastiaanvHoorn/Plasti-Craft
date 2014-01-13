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
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import plasticraft.items.Items;

public class EntityCarbonite extends EntityMob {

	public EntityCarbonite(World par1World) {
		super(par1World);
		PlastiCraft.info("carbonite");
		this.setSize(1.0F, 2.0F);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.22D);
	}
	
	@Override
	protected boolean isAIEnabled(){
		return true;
	}
	
	@Override
	protected int getDropItemId(){
		return Items.plastic_Item.itemID;
	}
	
	@Override
	protected boolean canDespawn(){
		return true;
	}
	
	@Override
	public boolean canBeCollidedWith(){
		return !isDead;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(){
		return boundingBox;
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity entity){
		return entity.boundingBox;
	}

}
