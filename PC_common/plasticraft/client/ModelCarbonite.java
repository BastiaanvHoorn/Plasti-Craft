package plasticraft.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCarbonite extends ModelBase
{
  //fields
    ModelRenderer Body;
    ModelRenderer Leg2;
    ModelRenderer Leg1;
    ModelRenderer Arm1;
    ModelRenderer Arm2;
    ModelRenderer Shape1;
  
  public ModelCarbonite()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Body = new ModelRenderer(this, 4, 0);
      Body.addBox(0F, 0F, 0F, 5, 10, 4);
      Body.setRotationPoint(-2F, 0F, -1F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Leg2 = new ModelRenderer(this, 0, 0);
      Leg2.addBox(-0.5F, 0F, -0.5F, 1, 14, 1);
      Leg2.setRotationPoint(-2F, 10F, 1F);
      Leg2.setTextureSize(64, 32);
      Leg2.mirror = true;
      setRotation(Leg2, 0F, 0F, 0F);
      Leg1 = new ModelRenderer(this, 0, 0);
      Leg1.addBox(-0.5F, 0F, -0.5F, 1, 14, 1);
      Leg1.setRotationPoint(3F, 10F, 1F);
      Leg1.setTextureSize(64, 32);
      Leg1.mirror = true;
      setRotation(Leg1, 0F, 0F, 0F);
      Arm1 = new ModelRenderer(this, 22, 0);
      Arm1.addBox(0F, 0F, 0F, 1, 9, 1);
      Arm1.setRotationPoint(3F, 0F, 0F);
      Arm1.setTextureSize(64, 32);
      Arm1.mirror = true;
      setRotation(Arm1, 0F, 0F, 0F);
      Arm2 = new ModelRenderer(this, 22, 0);
      Arm2.addBox(0F, 0F, 0F, 1, 9, 1);
      Arm2.setRotationPoint(-3F, 0F, 0F);
      Arm2.setTextureSize(64, 32);
      Arm2.mirror = true;
      setRotation(Arm2, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 0, 15);
      Shape1.addBox(-1.5F, -2F, -1F, 3, 2, 2);
      Shape1.setRotationPoint(0.5F, 0F, 1F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Body.render(f5);
    Leg2.render(f5);
    Leg1.render(f5);
    Arm1.render(f5);
    Arm2.render(f5);
    Shape1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  

}
