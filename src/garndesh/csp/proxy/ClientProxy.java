package garndesh.csp.proxy;

import garndesh.csp.client.renderer.entity.RendererBabyCreeper;
import garndesh.csp.entity.EntityBabyCreeper;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	// Client stuff
	@Override
    public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBabyCreeper.class, new RendererBabyCreeper()); 
    }
}
