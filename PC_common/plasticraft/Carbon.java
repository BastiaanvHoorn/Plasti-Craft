package plasticraft;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
//This class is used to store all items and blocks that are considered processable by the carbon former
public class Carbon {//these work togheter so they could be one multidimensional array
 private static Object[] carbon_obj = {Items.diamond, Items.coal, Blocks.coal_block};
 private static int[] carbon_amount = {1000, 100, 900};
 //used so you can add an object to the list
 public boolean addCarbon(Object obj, int amount){
	 if(obj != null && amount!= 0){
		 int lenght = carbon_obj.length;
		 if(carbon_obj[lenght] != null){
			 return false;
		 }else{
			 carbon_obj[lenght] = obj;
			 return true;
		 }
	 }else{
		 return false;
	 }
 }
 //checks if the objectg is in the list
 public static boolean isCarbon(ItemStack stack){
	 for(int i = 0; i < carbon_obj.length; i++){
		 if(stack!=null){
		 if(stack.getItem().equals(carbon_obj[i])){
			 return true;
		 }
	 }else{
		 return false;
		 }
	 }
	return false;
 }
 //gets the amount of liquid plastic that one of these items will produce(in milibuckets)
 public static int produces(ItemStack stack){
	 if(Carbon.isCarbon(stack)){
		 for(int i = 0; i < carbon_obj.length; i++){
			 if(stack.getItem().equals(carbon_obj[i])){
				 return carbon_amount[i];
			 }
		 }
	 }
	 return 0;
 }
}
