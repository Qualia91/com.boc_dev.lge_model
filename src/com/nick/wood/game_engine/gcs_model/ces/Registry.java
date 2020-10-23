package com.nick.wood.game_engine.gcs_model.ces;

import com.nick.wood.game_engine.gcs_model.bus.ComponentCreateEvent;
import com.nick.wood.game_engine.gcs_model.generated.ComponentType;
import com.nick.wood.game_engine.event_bus.busses.GameBus;

import java.util.ArrayList;
import java.util.HashMap;

public class Registry {

	private final HashMap<ComponentType, ArrayList<Component>> componentMap = new HashMap<>();
	private final GameBus gameBus;

	public Registry(GameBus gameBus) {
		componentMap.put(ComponentType.GEOMETRY, new ArrayList<>());
		componentMap.put(ComponentType.TRANSFORM, new ArrayList<>());
		this.gameBus = gameBus;
	}

	public HashMap<ComponentType, ArrayList<Component>> getComponentMap() {
		return componentMap;
	}

	public void deleteComponent(Component component) {
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
		// add component
		componentMap.get(component.getComponentType()).add(component);
	}

	public void registerComponent(Component component) {
		// add component
		gameBus.dispatch(new ComponentCreateEvent(component));
	}
}
