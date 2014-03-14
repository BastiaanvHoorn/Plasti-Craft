package plasticraft.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import plasticraft.PlastiCraft;
import plasticraft.client.interfaces.BoxInventory;
import plasticraft.client.interfaces.GhostContainer;
import plasticraft.client.interfaces.ISlotController.InventoryRulesController;
import plasticraft.client.interfaces.SimpleInventory;
import plasticraft.client.interfaces.SlotExtended;

public class LunchBox extends Item{

	private int itemUseDuration;
	
	public LunchBox(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(PlastiCraft.tabsPC);
	}
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		openGui(player);
		if(!player.isSneaking()){
			player.setItemInUse(stack, getMaxItemUseDuration(stack));
		}
		return super.onItemRightClick(stack, world, player);
    }
	
	private ItemStack eatFood(EntityPlayer player) {
		if(!player.isSneaking()){
			IInventory inv = getBoxInventory(player);
			ItemStack stack = inv.getStackInSlot(0);
			if(stack != null){
				if(stack.itemID == PCItems.steak.itemID){
						this.itemUseDuration = 32;
						ItemStack stack2 = PCItems.steak.onEaten(stack, player.worldObj, player);
						inv.setInventorySlotContents(0, stack2);
						inv.closeChest();
				}
			}
		}
		return player.getHeldItem();
		
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack){
		if(stack.itemID == PCItems.lunchBox.itemID){
			return EnumAction.eat;
		}else{
			return null;
		}
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack){
		return 32;
	}
	
	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer player){
		return eatFood(player);
	}
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
		openGui(player);
		if(!player.isSneaking()){
			player.setItemInUse(stack, getMaxItemUseDuration(stack));
		}
		return super.onItemUse(stack, player, par3World, par4, par5, par6, par7, par8, par9, par10);
	}
	private void openGui(EntityPlayer player) {
		if(player.isSneaking()){
			player.openGui(PlastiCraft.instance, 1, player.worldObj, 0, 0, 0);
		}
	}
	public static Container getContainer(final EntityPlayer player) {
		IInventory boxInventory = getBoxInventory(player);
		
		if(boxInventory == null)
			boxInventory = new BoxInventory(player, new ItemStack(PCItems.lunchBox));
		
		GhostContainer container =new GhostContainer(player.inventory) {
			@Override
			public GhostContainer addCustomSlot(SlotExtended slot){
				if(slot.getSlotIndex() == player.inventory.currentItem){
					if(slot.inventory == player.inventory){
						return super.addCustomSlot(slot.setRemoval(false));
					}
					
				}
				return super.addCustomSlot(slot);
			}
		};
		

				
		SlotExtended s = new SlotExtended(boxInventory, 0, 80, 29).setCheck(InventoryRulesController.instance);
			
		container.addCustomSlot(s);
			

		
		container.addPlayerInventory(8 ,86);
		return container;

	}
	public static IInventory getBoxInventory(EntityPlayer player) {
		SimpleInventory inv = null;
		ItemStack held = player.getHeldItem();
		if(held != null && held.itemID == PCItems.lunchBox.itemID){
			inv = new BoxInventory(player, held);
			inv.load(held.getTagCompound(), "contents");
		}
		return inv;
	}

}
