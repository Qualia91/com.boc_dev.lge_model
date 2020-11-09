package com.nick.wood.game_engine.gcs_model.bus;

import com.nick.wood.game_engine.event_bus.interfaces.EventType;

public enum RenderableUpdateEventType implements EventType {
	DESTROY,
	CREATE,
	UPDATE_TRANSFORM,
	UPDATE_RENDERABLE;
}
