package com.boc_dev.lge_model.bus;

import com.boc_dev.lge_model.gcs.ChangeSet;
import com.boc_dev.lge_model.gcs.Component;
import com.boc_dev.event_bus.interfaces.Event;

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
