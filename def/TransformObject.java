
package com.nick.wood.game_engine.gcs_model.generated;

import com.nick.wood.game_engine.gcs_model.gcs.*;
import com.nick.wood.maths.objects.vector.Vec3f;
import com.nick.wood.maths.objects.QuaternionF;


/** Transform of an object. Will affect the objects underneath it.
*/
public class TransformObject extends Component {

	// scale component of transform
	private Vec3f scale
	// positon component of transform
	private Vec3f position
	// rotation component of transform
	private QuaternionF rotation


	public TransformObject(Vec3f scale, Vec3f position, QuaternionF rotation) {
		super(ComponentType.TRANSFORM, name);
		this.scale = scale;
		this.position = position;
		this.rotation = rotation;

		DESCRIPTOR
			.addFieldDescriptor(scale, this::setScale)
			.addFieldDescriptor(position, this::setPosition)
			.addFieldDescriptor(rotation, this::setRotation);
	}

	public Vec3f getScale();

	private void setScale(Vec3f scale);

	public Vec3f getPosition();

	private void setPosition(Vec3f position);

	public QuaternionF getRotation();

	private void setRotation(QuaternionF rotation);


	@Override
	public TransformUpdater getUpdater(Registry registry) {
		return new TransformUpdater(registry, DESCRIPTOR, this);
	}

	public static class TransformUpdater extends ComponentUpdater {

		public TransformUpdater(Registry registry, ComponentDescriptor componentDescriptor, TransformObject object) {
			super(registry, componentDescriptor, object);
		}
		
		public TransformUpdater setScale(Vec3f scale) {
			setValue((TransformObject obj) -> obj.setScale(scale), "scale");
			return this;
		}

		public TransformUpdater setPosition(Vec3f position) {
			setValue((TransformObject obj) -> obj.setPosition(position), "position");
			return this;
		}

		public TransformUpdater setRotation(QuaternionF rotation) {
			setValue((TransformObject obj) -> obj.setRotation(rotation), "rotation");
			return this;
		}


	}
}
