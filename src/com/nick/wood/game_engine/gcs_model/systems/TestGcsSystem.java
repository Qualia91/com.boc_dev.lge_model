package com.nick.wood.game_engine.gcs_model.systems;

import com.nick.wood.game_engine.gcs_model.ces.Registry;
import com.nick.wood.game_engine.gcs_model.generated.ComponentType;
import com.nick.wood.game_engine.gcs_model.generated.TransformObject;
import com.nick.wood.maths.objects.QuaternionF;
import com.nick.wood.maths.objects.vector.Vec3f;

import java.util.ArrayList;

public class TestGcsSystem implements GcsSystem<TransformObject> {


	@Override
	public void update(long time, ArrayList<TransformObject> transformComponents, Registry registry) {

		TransformObject newTransformObject = new TransformObject("hello", Vec3f.ZERO, Vec3f.ONE, QuaternionF.Identity);
		if (time % 10 == 0) {
			registry.registerComponent(newTransformObject);
		}

		for (TransformObject transformObject : transformComponents) {
			TransformObject.TransformUpdater transformUpdater = transformObject.getUpdater(registry)
					.setPosition(new Vec3f(time, 0, 0));
			if (time % 10 == 0) {
				newTransformObject.getUpdater(registry).setParent(transformObject);
			}
			transformUpdater.sendUpdate();
			if (time % 10 == 9 && transformObject.getName().equals("hello")) {
				transformObject.getUpdater(registry).delete();
			}
		}



	}

	@Override
	public ComponentType getTypeSystemUpdates() {
		return ComponentType.TRANSFORM;
	}
}
