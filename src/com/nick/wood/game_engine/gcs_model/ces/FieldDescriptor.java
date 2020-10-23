package com.nick.wood.game_engine.gcs_model.ces;

public class FieldDescriptor<T> {

	private final String name;
	private final int index;
	private final Setter<T> setter;

	public FieldDescriptor(String name, int index, Setter<T> setter) {
		this.name = name;
		this.index = index;
		this.setter = setter;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public Setter<T> getSetter() {
		return setter;
	}
}
