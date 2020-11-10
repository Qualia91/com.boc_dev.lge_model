package com.boc_dev.lge_model.gcs;

import com.boc_dev.lge_model.bus.ComponentCreateEvent;
import com.boc_dev.event_bus.busses.GameBus;
import com.boc_dev.lge_model.bus.RenderableUpdateEvent;
import com.boc_dev.lge_model.bus.RenderableUpdateEventType;
import com.boc_dev.lge_model.generated.components.ComponentType;
import com.boc_dev.lge_model.systems.GcsSystem;

import java.util.HashMap;
import java.util.HashSet;

public class Registry {

	private final HashMap<ComponentType, HashSet<Component>> componentMap = new HashMap<>();
	private final GameBus gameBus;

	public Registry(GameBus gameBus) {
		for (ComponentType value : ComponentType.values()) {
			componentMap.put(value, new HashSet<>());
		}
		this.gameBus = gameBus;
	}

	public HashMap<ComponentType, HashSet<Component>> getComponentMap() {
		return componentMap;
	}

	public void deleteComponent(Component component) {

		// if this is a renderable object, send a message to game loop to notify render conversion system
		// when appropriate
		if (component.getComponentType().isRender()) {
			gameBus.dispatch(new RenderableUpdateEvent(component, RenderableUpdateEventType.DESTROY));
		}

		// remove parent (which removes child)
		component.removeParent();
		// remove component from map
		componentMap.get(component.getComponentType()).remove(component);
		// recursively delete children components
		int children = component.getChildren().size();
		for (int i = 0; i < children; i++) {
			deleteComponent(component.getChildren().get(i));
		}
	}

	public GameBus getBus() {
		return gameBus;
	}

	public void createComponent(Component component) {

		// if this is a renderable object, send a message to game loop to notify render conversion system
		// when appropriate
		if (component.getComponentType().isRender()) {
			gameBus.dispatch(new RenderableUpdateEvent(component, RenderableUpdateEventType.CREATE));
		}

		// now create components underneath unless they are already created
		for (Component child : component.getChildren()) {
			if (!componentMap.get(child.getComponentType()).contains(child)) {
				createComponent(child);
			}
		}

		// add component
		componentMap.get(component.getComponentType()).add(component);
	}

	void registerComponent(Component component) {
		// add component
		gameBus.dispatch(new ComponentCreateEvent(component));

	}

	public void addSystem(GcsSystem inputSystem) {
	}
}
