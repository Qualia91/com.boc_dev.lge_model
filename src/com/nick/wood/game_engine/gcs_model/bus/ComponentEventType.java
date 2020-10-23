package com.nick.wood.game_engine.gcs_model.bus;

import com.nick.wood.game_engine.event_bus.interfaces.EventType;

public enum ComponentEventType implements EventType {
	CREATE,
	CHANGE,
	DESTROY
}
