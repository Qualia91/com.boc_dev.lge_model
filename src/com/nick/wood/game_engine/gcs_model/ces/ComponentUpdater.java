package com.nick.wood.game_engine.gcs_model.ces;

import com.nick.wood.game_engine.gcs_model.bus.ComponentDestroyEvent;
import com.nick.wood.game_engine.gcs_model.bus.ComponentUpdateEvent;

import java.util.function.Consumer;

public abstract class ComponentUpdater {

	private final Registry registry;
	private final Component component;
	private final ComponentDescriptor componentDescriptor;
	private final Consumer[] values;

	public ComponentUpdater(Registry registry, ComponentDescriptor componentDescriptor, Component component) {
		this.registry = registry;
		this.values = new Consumer[componentDescriptor.getFieldDescriptors().size()];
		this.component = component;
		this.componentDescriptor = componentDescriptor;
	}

	public void delete() {
		registry.getBus().dispatch(new ComponentDestroyEvent(component));
	}

	public ComponentUpdater setParent(Component parent) {
		setValue((Consumer<Component>) component -> component.setParent(parent), "parent");
		return this;
	}

	public <T> void setValue(Consumer<T> setter, String fieldName) {
		values[componentDescriptor.getFieldDescriptors().get(fieldName).getIndex()] = setter;
	}

	public void sendUpdate() {

		int i;
		for (i = 0; i < values.length; i++) {
			if (values[i] != null) break;
		}

		// if all the values are null (no data is updated) this will not run
		if (i != values.length) {

			ChangeSet changeSet = new ChangeSet(componentDescriptor, values);

			// send update the game bus which game look is registered too.
			// game look will then apply these updates at the end of the step
			registry.getBus().dispatch(new ComponentUpdateEvent(component, changeSet));

		}
	}
}
