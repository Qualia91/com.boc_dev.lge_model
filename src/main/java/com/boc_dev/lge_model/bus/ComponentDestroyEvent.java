package com.boc_dev.lge_model.bus;

import com.boc_dev.lge_model.gcs.Component;
import com.boc_dev.event_bus.interfaces.Event;

public class ComponentDestroyEvent implements Event<Component> {

	private final Component component;
	private final ComponentEventType componentEventType;

	public ComponentDestroyEvent(Component component) {
		this.component = component;
		this.componentEventType = ComponentEventType.DESTROY;
	}

	@Override
	public Component getData() {
		return component;
	}

	@Override
	public ComponentEventType getType() {
		return componentEventType;
	}
}
