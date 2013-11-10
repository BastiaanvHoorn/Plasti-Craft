package plasticraft.events;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import plasticraft.PlastiCraft;

public class bucketevent{

	@ForgeSubscribe
	public void onBucketFill(FillBucketEvent event){
		
		ItemStack result = fillBucket(event.world, event.target);
		
		if(result == null) return;
		
		event.result = result;
		event.setResult(Result.ALLOW);
	}

	private ItemStack fillBucket(World world, MovingObjectPosition target) {
		int blockId = world.getBlockId(target.blockX, target.blockY, target.blockZ);
		
		if(PlastiCraft.fluidPlasticId == blockId && world.getBlockMetadata(target.blockX, target.blockY, target.blockZ) == 0){
			world.setBlock(target.blockX, target.blockY, target.blockZ, 0);
			return new ItemStack(PlastiCraft.bucketplastic);
		}else return null;
		

	}
	
}
