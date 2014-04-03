package plasticraft;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Carbon {
 private static Object[] carbon_obj = {Items.diamond, Items.coal, Blocks.coal_block};
 private static int[] carbon_amount = {1000, 100, 900};
 
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
