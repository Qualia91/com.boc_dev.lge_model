package com.nick.wood.game_engine.gcs_model.systems;

import com.nick.wood.game_engine.gcs_model.gcs.Component;
import com.nick.wood.game_engine.gcs_model.gcs.Registry;
import com.nick.wood.game_engine.gcs_model.generated.components.ComponentType;

import java.util.ArrayList;

public interface GcsSystem<T extends Component> {
	void update(long time, ArrayList<T> transformComponents, Registry registry);
	ComponentType getTypeSystemUpdates();
}
