package com.boc_dev.lge_model.systems;

import com.boc_dev.lge_model.gcs.Registry;
import com.boc_dev.lge_model.gcs.Component;
import com.boc_dev.lge_model.generated.components.ComponentType;

import java.util.HashSet;

public interface GcsSystem<T extends Component> {
	void update(long time, HashSet<T> components, Registry registry);
	ComponentType getTypeSystemUpdates();
}
