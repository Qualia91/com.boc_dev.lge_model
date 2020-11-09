package com.nick.wood.game_engine.gcs_model.gcs;

import java.util.function.Consumer;

public class ChangeSet {

	private final ComponentDescriptor componentDescriptor;
	private final Consumer[] values;

	public ChangeSet(ComponentDescriptor componentDescriptor, Consumer[] values) {
		this.componentDescriptor = componentDescriptor;
		this.values = values;
	}

	public ComponentDescriptor getComponentDescriptor() {
		return componentDescriptor;
	}

	public Consumer[] getValues() {
		return values;
	}
}
