package plasticraft.events;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import plasticraft.blocks.PCBlocks;
import plasticraft.items.PCItems;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class bucketevent{

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event){
		
		ItemStack result = fillBucket(event.world, event.target);
		
		if(result == null) return;
		
		event.result = result;
		event.setResult(Result.ALLOW);
	}

	private ItemStack fillBucket(World world, MovingObjectPosition target) {
		Block block = world.getBlock(target.blockX, target.blockY, target.blockZ);
		
		if(PCBlocks.Fluid_Plastic_Block.equals(block)&& world.getBlockMetadata(target.blockX, target.blockY, target.blockZ) == 0){
			world.setBlock(target.blockX, target.blockY, target.blockZ, Blocks.air);
			return new ItemStack(PCItems.bucketplastic);
		}else return null;
		

	}
	
}
