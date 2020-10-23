package com.nick.wood.game_engine.gcs_model.ces;

public class EntityID {
	private final long app;
	private final long id;

	public EntityID(long app, long id) {
		this.app = app;
		this.id = id;
	}

	public long getApp() {
		return app;
	}

	public long getId() {
		return id;
	}
}
