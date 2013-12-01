package plasticraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Carbon {
 private static int[] carbon_id = {Item.diamond.itemID, Item.coal.itemID, Block.coalBlock.blockID};
 private static int[] carbon_amount = {1000, 100, 900};
 
 public boolean addCarbon(int id, int amount){
	 if(id !=0 && amount!= 0){
		 int lenght = carbon_id.length;
		 if(carbon_id[lenght] > 0){
			 return false;
		 }else{
			 carbon_id[lenght] = id;
			 return true;
		 }
	 }else{
		 return false;
	 }
 }
 
 public static boolean isCarbon(ItemStack stack){
	 for(int i = 0; i < carbon_id.length; i++){
		 if(stack!=null){
		 if(stack.itemID == carbon_id[i]){
			 return true;
		 }
	 }else{
		 return false;
		 }
	 }
	return false;
 }
 
 public static int produces(ItemStack stack){
	 if(Carbon.isCarbon(stack)){
		 for(int i = 0; i < carbon_id.length; i++){
			 if(stack.itemID == carbon_id[i]){
				 return carbon_amount[i];
			 }
		 }
	 }
	 return 0;
 }
}
