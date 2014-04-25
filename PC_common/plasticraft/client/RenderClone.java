package plasticraft.client;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import plasticraft.entity.EntityClone;
import plasticraft.lib.References;

public class RenderClone extends RenderLiving{

	private ResourceLocation rs = new ResourceLocation(References.MOD_ID.toLowerCase() + ":textures/entities/Clone.png");
	private ModelClone model;
	public RenderClone(ModelClone model) {
		super(model, 0.5F);
		this.model = model;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return rs;
	}
	
	@Override
	protected void renderEquippedItems(EntityLivingBase entity, float par2)
    {
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		EntityClone clone = (EntityClone)entity;
		ItemStack itemstack = clone.getStackInSlot(0);
		ItemStack itemstack1 = clone.getStackInSlot(1);
		this.model.heldItemLeft = itemstack1 != null ? 1: 0;
		this.model.heldItemRight = itemstack != null ? 1:0;
		Item item;
		float f1;
		
		if(itemstack != null){
			GL11.glPushMatrix();
			item = itemstack.getItem();
			
			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
			
			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
			boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));
			
			if (item instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(item).getRenderType())))
			{
				f1 = 0.5F;
                GL11.glTranslatef(-0.2F, 0.1875F, -0.5F);
                f1 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f1, -f1, f1);
			}else if (item == Items.bow)
            {
                f1 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f1, -f1, f1);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }else if (item.isFull3D())
            {
                f1 = 0.625F;

                if (item.shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(f1, -f1, f1);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }else
            {
                f1 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f1, f1, f1);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }
			float f2;
            float f3;
            int j;

            if (itemstack.getItem().requiresMultipleRenderPasses())
            {
                for (j = 0; j <= 1; ++j)
                {
                    int i = itemstack.getItem().getColorFromItemStack(itemstack, j);
                    f2 = (float)(i >> 16 & 255) / 255.0F;
                    f3 = (float)(i >> 8 & 255) / 255.0F;
                    float f4 = (float)(i & 255) / 255.0F;
                    GL11.glColor4f(f2, f3, f4, 1.0F);
                    for (int x = 1; x < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); x++)
                    {
                        this.renderManager.itemRenderer.renderItem((EntityLivingBase) entity, itemstack, x);
                    }
                }
            }
            else
            {
                j = itemstack.getItem().getColorFromItemStack(itemstack, 0);
                float f5 = (float)(j >> 16 & 255) / 255.0F;
                f2 = (float)(j >> 8 & 255) / 255.0F;
                f3 = (float)(j & 255) / 255.0F;
                GL11.glColor4f(f5, f2, f3, 1.0F);
                this.renderManager.itemRenderer.renderItem((EntityLivingBase) entity, itemstack, 0);
            }
            GL11.glPopMatrix();
		}
            if(itemstack1 != null){
    			GL11.glPushMatrix();
    			item = itemstack1.getItem();
    			
    			GL11.glTranslatef(0.25F, 0.4375F, -0.15F);
    			
    			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, EQUIPPED);
    			boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack1, BLOCK_3D));
    			
    			if (item instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(item).getRenderType())))
    			{
    				f1 = 0.5F;
                    GL11.glTranslatef(0F, 0.1875F, -0.3F);
                    f1 *= 0.75F;
                    GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(-f1, -f1, f1);
    			}else if (item == Items.bow)
                {
                    f1 = 0.625F;
                    GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                    GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(f1, -f1, f1);
                    GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                }else if (item.isFull3D())
                {
                    f1 = 0.625F;

                    if (item.shouldRotateAroundWhenRendering())
                    {
                        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                    }

                    GL11.glTranslatef(0F, 0.1875F, 0.0F);
                    GL11.glScalef(f1, -f1, f1);
                    GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                }else
                {
                    f1 = 0.375F;
                    GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                    GL11.glScalef(f1, f1, f1);
                    GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                }
    			float f2;
                float f3;
                int j;

                if (itemstack1.getItem().requiresMultipleRenderPasses())
                {
                    for (j = 0; j <= 1; ++j)
                    {
                        int i = itemstack1.getItem().getColorFromItemStack(itemstack1, j);
                        f2 = (float)(i >> 16 & 255) / 255.0F;
                        f3 = (float)(i >> 8 & 255) / 255.0F;
                        float f4 = (float)(i & 255) / 255.0F;
                        GL11.glColor4f(f2, f3, f4, 1.0F);
                        for (int x = 1; x < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); x++)
                        {
                            this.renderManager.itemRenderer.renderItem((EntityLivingBase) entity, itemstack1, x);
                        }
                    }
                }
                else
                {
                    j = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
                    float f5 = (float)(j >> 16 & 255) / 255.0F;
                    f2 = (float)(j >> 8 & 255) / 255.0F;
                    f3 = (float)(j & 255) / 255.0F;
                    GL11.glColor4f(f5, f2, f3, 1.0F);
                    this.renderManager.itemRenderer.renderItem((EntityLivingBase) entity, itemstack1, 0);
                }
			GL11.glPopMatrix();
		}
    }
}
