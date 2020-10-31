
package PACKAGE;

IMPORTS

/** CLASS_COMMENT
*/
public class TYPEObject extends Component {

FIELDS

	public TYPEObject(Registry registry, String name, CONSTRUCTION_PARAMS) {
		super(ComponentType.COMPONENT_T, name, registry);
FIELD_INITS
		DESCRIPTOR
FIELD_DESCRIPTOR_CREATION;
	}

GETTERS_AND_SETTERS

	@Override
	public TYPEUpdater getUpdater() {
		return new TYPEUpdater(super.registry, DESCRIPTOR, this);
	}

	public static class TYPEUpdater extends ComponentUpdater {

		public TYPEUpdater(Registry registry, ComponentDescriptor componentDescriptor, TYPEObject object) {
			super(registry, componentDescriptor, object);
		}
		
UPDATERS
	}
UPDATERENDERABLEFUNCTION
}
