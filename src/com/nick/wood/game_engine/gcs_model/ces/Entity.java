package com.nick.wood.game_engine.gcs_model.ces;

public class Entity {

	private final String entityName;
	private final EntityID entityID;

	public Entity(String entityName, EntityID entityID) {
		this.entityName = entityName;
		this.entityID = entityID;
	}

	public String getEntityName() {
		return entityName;
	}

	public EntityID getEntityID() {
		return entityID;
	}
}
