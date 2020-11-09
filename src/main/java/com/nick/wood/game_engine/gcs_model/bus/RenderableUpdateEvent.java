package com.nick.wood.game_engine.gcs_model.bus;

import com.nick.wood.game_engine.event_bus.interfaces.Event;
import com.nick.wood.game_engine.gcs_model.gcs.Component;

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
