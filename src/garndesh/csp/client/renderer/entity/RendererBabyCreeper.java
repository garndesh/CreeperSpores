package garndesh.csp.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderCreeper;

public class RendererBabyCreeper extends RenderCreeper {

	protected ModelBase creeperModel = new ModelCreeper(1.0F);
	
	public RendererBabyCreeper(){
		super();
		
	}
}
