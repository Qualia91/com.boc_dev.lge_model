package com.nick.wood.game_engine.gcs_model.ces;

import java.util.HashMap;

public class ComponentDescriptor {

	private int currentIndex = 0;
	private final HashMap<String, FieldDescriptor> fieldDescriptors = new HashMap<>();


	public <T> ComponentDescriptor addFieldDescriptor(String fieldName, Setter<T> setter) {
		fieldDescriptors.put(fieldName, new FieldDescriptor<>(fieldName, currentIndex++, setter));
		return this;
	}

	public HashMap<String, FieldDescriptor> getFieldDescriptors() {
		return fieldDescriptors;
	}
}
