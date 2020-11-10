package com.boc_dev.lge_model.bus;

import com.boc_dev.event_bus.interfaces.EventType;

public enum RenderableUpdateEventType implements EventType {
	DESTROY,
	CREATE,
	UPDATE_TRANSFORM,
	UPDATE_RENDERABLE;
}
