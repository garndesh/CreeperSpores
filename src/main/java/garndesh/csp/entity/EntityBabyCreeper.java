package garndesh.csp.entity;

import garndesh.csp.lib.Constants;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityBabyCreeper extends EntityCreeper {

	public EntityBabyCreeper(World world) {
		super(world);
		
		@SuppressWarnings("unchecked")
		List<EntityAITaskEntry> taskList = new ArrayList<EntityAITaskEntry>(tasks.taskEntries);
		EntityAIBase task = null;
		for(EntityAITaskEntry e : taskList){
			if(e.action instanceof EntityAIAttackOnCollide || e.action instanceof EntityAICreeperSwell){
				task = e.action;
				this.tasks.removeTask(task);
			}
		}
		
		EntityAITaskEntry taskEntry = (EntityAITaskEntry)(this.targetTasks.taskEntries.get(1));
		this.targetTasks.removeTask(taskEntry.action);
		this.setSize(0.75F, 0.4F);

        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 12.0F, 1.3D, 1.6D));
	}
	
	@Override
	public void onUpdate(){
		super.onUpdate();
		if(this.ticksExisted >= Constants.CREEPER_CHILD_TIME){
			tryGrowUp();
		}
	}
	
	private void tryGrowUp(){
		int x = (int)this.posX;
		int y = (int)this.posY;
		int z = (int)this.posZ;
		if(this.worldObj.isAirBlock(x, y+1, z)){
			EntityCreeper creeper = new EntityCreeper(this.worldObj);
			creeper.setPosition(x+0.5, y+0.5, z+0.5);
			this.worldObj.spawnEntityInWorld(creeper);
			this.setDead();
		}
	}

}
