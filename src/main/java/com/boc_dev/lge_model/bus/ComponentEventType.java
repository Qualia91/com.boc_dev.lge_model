package com.boc_dev.lge_model.bus;

import com.boc_dev.event_bus.interfaces.EventType;

public enum ComponentEventType implements EventType {
	CREATE,
	CHANGE,
	DESTROY
}
