package com.nick.wood.game_engine.gcs_model;

import com.nick.wood.game_engine.gcs_model.bus.ComponentCreateEvent;
import com.nick.wood.game_engine.gcs_model.bus.ComponentDestroyEvent;
import com.nick.wood.game_engine.gcs_model.bus.ComponentEventType;
import com.nick.wood.game_engine.gcs_model.bus.ComponentUpdateEvent;
import com.nick.wood.game_engine.gcs_model.ces.Component;
import com.nick.wood.game_engine.gcs_model.ces.Registry;
import com.nick.wood.game_engine.gcs_model.generated.ComponentType;
import com.nick.wood.game_engine.gcs_model.systems.GcsSystem;
import com.nick.wood.game_engine.event_bus.busses.GameBus;
import com.nick.wood.game_engine.event_bus.interfaces.Event;
import com.nick.wood.game_engine.event_bus.interfaces.Subscribable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

public class GameLoop implements Subscribable {

	private final HashSet<Class> supported = new HashSet<>();

	private final Registry registry;
	private final ArrayList<GcsSystem<Component>> gcsSystems;

	private final ArrayBlockingQueue<ComponentUpdateEvent> changeEventsQueue = new ArrayBlockingQueue<>(100);
	private final ArrayBlockingQueue<ComponentCreateEvent> createEventsQueue = new ArrayBlockingQueue<>(100);
	private final ArrayBlockingQueue<ComponentDestroyEvent> destroyEventsQueue = new ArrayBlockingQueue<>(100);
	private final ArrayList<ComponentUpdateEvent> drainToListChange = new ArrayList<>();
	private final ArrayList<ComponentCreateEvent> drainToListCreate = new ArrayList<>();
	private final ArrayList<ComponentDestroyEvent> drainToListDestroy = new ArrayList<>();

	public GameLoop(ArrayList<GcsSystem<Component>> gcsSystems) {

		supported.add(ComponentUpdateEvent.class);
		supported.add(ComponentCreateEvent.class);
		supported.add(ComponentDestroyEvent.class);

		GameBus gameBus = new GameBus();
		gameBus.register(this);
		this.registry = new Registry(gameBus);
		this.gcsSystems = gcsSystems;

	}

	public Registry getRegistry() {
		return registry;
	}

	public void run() {

		try {
			long timestep = 0;
			while (true) {
				// run systems
				for (GcsSystem<Component> gcsSystem : gcsSystems) {
					gcsSystem.update(timestep, registry.getComponentMap().get(gcsSystem.getTypeSystemUpdates()), registry);
				}

				// create events
				applyCreateEvents();
				// destroy events
				applyDestroyEvents();
				// update events
				applyUpdateEvents();

				timestep++;

				System.out.println(registry.getComponentMap().get(ComponentType.TRANSFORM).size());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void applyCreateEvents() {
		createEventsQueue.drainTo(drainToListCreate);

		// update world state
		for (ComponentCreateEvent componentCreateEvent : drainToListCreate) {
			registry.createComponent(componentCreateEvent.getData());
		}

		drainToListCreate.clear();
	}

	private void applyDestroyEvents() {
		destroyEventsQueue.drainTo(drainToListDestroy);

		// update world state
		for (ComponentDestroyEvent componentDestroyEvent : drainToListDestroy) {
			registry.deleteComponent(componentDestroyEvent.getData());
		}

		drainToListDestroy.clear();
	}

	private void applyUpdateEvents() {
		changeEventsQueue.drainTo(drainToListChange);

		// update world state
		for (ComponentUpdateEvent componentUpdateEvent : drainToListChange) {
			for (int valueIndex = 0; valueIndex < componentUpdateEvent.getChangeSet().getValues().length; valueIndex++) {
				if (componentUpdateEvent.getChangeSet().getValues()[valueIndex] != null) {
					componentUpdateEvent.getChangeSet().getValues()[valueIndex].accept(componentUpdateEvent.getData());
				}
			}
		}

		drainToListChange.clear();
	}

	@Override
	public void handle(Event<?> event) {
		if (event.getType().equals(ComponentEventType.CHANGE)) {
			changeEventsQueue.offer((ComponentUpdateEvent) event);
		} else if (event.getType().equals(ComponentEventType.DESTROY)) {
			destroyEventsQueue.offer((ComponentDestroyEvent) event);
		} else if (event.getType().equals(ComponentEventType.CREATE)) {
			createEventsQueue.offer((ComponentCreateEvent) event);
		}
	}

	@Override
	public boolean supports(Class<? extends Event> aClass) {
		return supported.contains(aClass);
	}

}
