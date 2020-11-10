package com.boc_dev.lge_model.bus;

import com.boc_dev.event_bus.interfaces.Event;
import com.boc_dev.lge_model.gcs.Component;

import java.util.UUID;

public class RenderableUpdateEvent implements Event<Component> {

	private final Component component;
	private final RenderableUpdateEventType renderableUpdateEventType;

	public RenderableUpdateEvent(Component component, RenderableUpdateEventType renderableUpdateEventType) {
		this.component = component;
		this.renderableUpdateEventType = renderableUpdateEventType;
	}

	public UUID getID() {
		return component.getUuid();
	}

	@Override
	public Component getData() {
		return component;
	}

	@Override
	public RenderableUpdateEventType getType() {
		return renderableUpdateEventType;
	}
}
