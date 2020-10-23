package com.nick.wood.game_engine.gcs_model.generated;

import com.nick.wood.game_engine.gcs_model.ces.*;
import com.nick.wood.maths.objects.QuaternionF;
import com.nick.wood.maths.objects.vector.Vec3f;

public class TransformObject extends Component {

	private Vec3f position;
	private Vec3f scale;
	private QuaternionF rotation;

	public TransformObject(String name,
	                       Vec3f position,
	                       Vec3f scale,
	                       QuaternionF rotation) {
		super(ComponentType.TRANSFORM, name);
		this.position = position;
		this.scale = scale;
		this.rotation = rotation;
		DESCRIPTOR.addFieldDescriptor("position", this::setPosition)
				.addFieldDescriptor("scale", this::setScale)
				.addFieldDescriptor("rotation", this::setRotation);
	}

	public Vec3f getPosition() {
		return position;
	}

	public Vec3f getScale() {
		return scale;
	}

	public QuaternionF getRotation() {
		return rotation;
	}

	private void setPosition(Vec3f position) {
		this.position = position;
	}

	private void setScale(Vec3f scale) {
		this.scale = scale;
	}

	private void setRotation(QuaternionF rotation) {
		this.rotation = rotation;
	}

	@Override
	public TransformUpdater getUpdater(Registry registry) {
		return new TransformUpdater(registry, DESCRIPTOR, this);
	}

	public static class TransformUpdater extends ComponentUpdater {

		public TransformUpdater(Registry registry, ComponentDescriptor componentDescriptor, TransformObject transformObject) {
			super(registry, componentDescriptor, transformObject);
		}

		public TransformUpdater setPosition(Vec3f position) {
			setValue((TransformObject transformObject) -> transformObject.setPosition(position), "position");
			return this;
		}

		public TransformUpdater setScale(Vec3f scale) {
			setValue((TransformObject transformObject) -> transformObject.setScale(scale), "scale");
			return this;
		}

		public TransformUpdater setRotation(QuaternionF rotation) {
			setValue((TransformObject transformObject) -> transformObject.setRotation(rotation), "rotation");
			return this;
		}

	}
}
