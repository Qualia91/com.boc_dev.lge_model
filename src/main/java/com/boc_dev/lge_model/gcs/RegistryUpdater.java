package com.boc_dev.lge_model.gcs;

import com.boc_dev.lge_model.bus.*;
import com.boc_dev.lge_model.generated.components.ComponentType;
import com.boc_dev.lge_model.systems.GcsSystem;
import com.boc_dev.event_bus.busses.GameBus;
import com.boc_dev.event_bus.interfaces.Event;
import com.boc_dev.event_bus.interfaces.Subscribable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

public class RegistryUpdater implements Subscribable {

	private final HashSet<Class> supported = new HashSet<>();

	private final Registry registry;
	private final GameBus gameBus;
	private final ArrayList<GcsSystem<Component>> gcsSystems;

	private final ArrayBlockingQueue<ComponentUpdateEvent> changeEventsQueue = new ArrayBlockingQueue<>(1_000_000);
	private final ArrayBlockingQueue<ComponentCreateEvent> createEventsQueue = new ArrayBlockingQueue<>(1_000_000);
	private final ArrayBlockingQueue<ComponentDestroyEvent> destroyEventsQueue = new ArrayBlockingQueue<>(1_000_000);
	private final ArrayList<ComponentUpdateEvent> drainToListChange = new ArrayList<>();
	private final ArrayList<ComponentCreateEvent> drainToListCreate = new ArrayList<>();
	private final ArrayList<ComponentDestroyEvent> drainToListDestroy = new ArrayList<>();

	public RegistryUpdater(ArrayList<GcsSystem<Component>> gcsSystems, Registry registry, GameBus gameBus) {

		supported.add(ComponentUpdateEvent.class);
		supported.add(ComponentCreateEvent.class);
		supported.add(ComponentDestroyEvent.class);

		this.registry = registry;
		this.gameBus = gameBus;
		gameBus.register(this);
		this.gcsSystems = gcsSystems;


	}

	public Registry getRegistry() {
		return registry;
	}

	public void run(long timestep) {

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
			componentUpdateEvent.getData().setDirty();

			if (componentUpdateEvent.getData().getComponentType().equals(ComponentType.TRANSFORM)) {
				registry.getBus().dispatch(new RenderableUpdateEvent(componentUpdateEvent.getData(), RenderableUpdateEventType.UPDATE_TRANSFORM));
			} else if (componentUpdateEvent.getData().getComponentType().isRender()) {
				registry.getBus().dispatch(new RenderableUpdateEvent(componentUpdateEvent.getData(), RenderableUpdateEventType.UPDATE_RENDERABLE));
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

	public ArrayList<GcsSystem<Component>> getGcsSystems() {
		return gcsSystems;
	}

}
