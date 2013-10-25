package plasticraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Carbon {
 private static int[] carbon_id = {Item.diamond.itemID, Item.coal.itemID, Block.coalBlock.blockID, };
 
 public boolean addCarbon(int id){
	 int lenght = carbon_id.length;
	 if(carbon_id[lenght+1] > 0){
		 return false;
	 }else{
		 carbon_id[lenght + 1] = id;
		 return true;
	 }
 }
 
 public static boolean isCarbon(ItemStack stack){
	 for(int i = 0; i < carbon_id.length; i++){
		 if(stack.itemID == carbon_id[i]){
			 return true;
		 }
	 }
	 return false;
 }
}
