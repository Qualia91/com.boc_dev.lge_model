package com.nick.wood.game_engine.gcs_model.bus;

import com.nick.wood.game_engine.gcs_model.gcs.ChangeSet;
import com.nick.wood.game_engine.gcs_model.gcs.Component;
import com.nick.wood.game_engine.event_bus.interfaces.Event;

public class ComponentUpdateEvent implements Event<Component> {

	private final Component component;
	private final ChangeSet changeSet;
	private final ComponentEventType componentEventType;

	public ComponentUpdateEvent(Component component, ChangeSet changeSet) {
		this.component = component;
		this.changeSet = changeSet;
		this.componentEventType = ComponentEventType.CHANGE;
	}

	public ChangeSet getChangeSet() {
		return changeSet;
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
