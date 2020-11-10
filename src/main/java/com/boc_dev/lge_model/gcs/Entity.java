package com.boc_dev.lge_model.gcs;

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
