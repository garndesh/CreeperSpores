package garndesh.csp.entity;

import java.util.List;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.world.World;

public class EntityBabyCreeper extends EntityCreeper {

	public EntityBabyCreeper(World world) {
		super(world);
		
		@SuppressWarnings("unchecked")
		List<EntityAITaskEntry> taskList = tasks.taskEntries;
		EntityAIBase task = null;
		for(EntityAITaskEntry e : taskList){
			if(e.action instanceof EntityAIAttackOnCollide){
				task = e.action;
				break;
			}
		}
		
		if(task!=null)
			this.tasks.removeTask(task);
		
		EntityAITaskEntry taskEntry = (EntityAITaskEntry)(this.targetTasks.taskEntries.get(1));
		this.targetTasks.removeTask(taskEntry.action);
		
	}

}
