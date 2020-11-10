package com.boc_dev.lge_model.gcs;

import com.boc_dev.lge_model.generated.components.ComponentType;
import com.boc_dev.maths.objects.matrix.Matrix4f;

import java.util.*;

public abstract class Component {

	protected final Registry registry;
	private final String name;
	private final UUID uuid;
	private boolean dirty = true;
	private final ComponentType componentType;
	private Component parent;
	private final ArrayList<Component> children = new ArrayList<>();
	private Matrix4f globalTransform = Matrix4f.Identity;

	protected final ComponentDescriptor DESCRIPTOR =
			new ComponentDescriptor()
					.addFieldDescriptor("parent", this::setParent);


	public Component(ComponentType componentType, String name, Registry registry) {
		this.registry = registry;
		this.registry.registerComponent(this);
		this.componentType = componentType;
		this.name = name;
		this.uuid = UUID.randomUUID();
	}

	public abstract ComponentUpdater getUpdater();

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

	public void createRenderable(RenderVisitor renderVisitor) {
		// implement in renderables
	}

	public void updateRenderable(RenderVisitor renderVisitor, Matrix4f matrix4f) {
		// implement in renderables
	}

	public void deleteRenderable(RenderVisitor renderVisitor) {
		// implement in renderables
	}

	public void setGlobalTransform(Matrix4f globalTransform) {
		this.globalTransform = globalTransform;
	}

	public Matrix4f getGlobalTransform() {
		return this.globalTransform;
	}

	protected Registry getRegistry() {
		return registry;
	}
}
