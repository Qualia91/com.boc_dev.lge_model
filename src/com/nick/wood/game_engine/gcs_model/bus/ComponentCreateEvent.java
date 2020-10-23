package com.nick.wood.game_engine.gcs_model.bus;

import com.nick.wood.game_engine.gcs_model.ces.Component;
import com.nick.wood.game_engine.event_bus.interfaces.Event;

public class ComponentCreateEvent implements Event<Component> {

	private final Component component;
	private final ComponentEventType componentEventType;

	public ComponentCreateEvent(Component component) {
		this.component = component;
		this.componentEventType = ComponentEventType.CREATE;
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
