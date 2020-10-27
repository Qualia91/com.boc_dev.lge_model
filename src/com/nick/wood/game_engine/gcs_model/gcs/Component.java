package com.nick.wood.game_engine.gcs_model.gcs;

import com.nick.wood.game_engine.gcs_model.generated.components.ComponentType;

import java.util.*;

public abstract class Component {

	private final String name;
	private final UUID uuid;
	private boolean dirty = true;
	private final ComponentType componentType;
	private Component parent;
	private final ArrayList<Component> children = new ArrayList<>();

	protected final ComponentDescriptor DESCRIPTOR =
			new ComponentDescriptor()
					.addFieldDescriptor("parent", this::setParent);


	public Component(ComponentType componentType, String name) {
		this.componentType = componentType;
		this.name = name;
		this.uuid = UUID.randomUUID();
	}

	public abstract ComponentUpdater getUpdater(Registry registry);

	void setParent(Component parent) {
		this.parent = parent;
		parent.addChild(this);
	}

	void addChild(Component component) {
		children.add(component);
		if (!component.getParent().equals(this)) {
			component.setParent(this);
		}
	}

	void removeParent() {
		if (parent != null) {
			parent.removeChild(this);
			this.parent = null;
		}
	}

	void removeChild(Component component) {
		children.remove(component);
	}

	public String getName() {
		return name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public ComponentType getComponentType() {
		return componentType;
	}

	public Component getParent() {
		return parent;
	}

	public List<Component> getChildren() {
		return Collections.unmodifiableList(children);
	}

	public void setDirty() {
		this.dirty = true;
	}

	public void setClean() {
		this.dirty = false;
	}

	public boolean isDirty() {
		return dirty;
	}
}
