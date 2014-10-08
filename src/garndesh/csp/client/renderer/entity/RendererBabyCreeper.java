package garndesh.csp.client.renderer.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.monster.EntityCreeper;

public class RendererBabyCreeper extends RenderCreeper {

	 protected void preRenderCallback(EntityCreeper entity, float timeTick){
	        super.preRenderCallback(entity, timeTick);
	        GL11.glScalef(0.5F, 0.5F, 0.5F);
	    }
	
}
