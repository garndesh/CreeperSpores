package garndesh.csp.entity;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.world.World;

public class EntityBabyCreeper extends EntityCreeper {

	public EntityBabyCreeper(World world) {
		super(world);
		
		@SuppressWarnings("unchecked")
		List<EntityAITaskEntry> taskList = new ArrayList<EntityAITaskEntry>(tasks.taskEntries);
		EntityAIBase task = null;
		for(EntityAITaskEntry e : taskList){
			if(e.action instanceof EntityAIAttackOnCollide || e.action instanceof EntityAICreeperSwell){
				//FMLLog.info("action found");
				task = e.action;
				this.tasks.removeTask(task);
			}
		}
		
		EntityAITaskEntry taskEntry = (EntityAITaskEntry)(this.targetTasks.taskEntries.get(1));
		this.targetTasks.removeTask(taskEntry.action);
		this.setSize(0.75F, 0.4F);
	}

}
