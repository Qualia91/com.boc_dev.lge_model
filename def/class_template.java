
package PACKAGE;

IMPORTS

/** CLASS_COMMENT
*/
public class TYPEObject extends Component {

FIELDS

	public TYPEObject(String name, CONSTRUCTION_PARAMS) {
		super(ComponentType.COMPONENT_T, name);
FIELD_INITS
		DESCRIPTOR
FIELD_DESCRIPTOR_CREATION;
	}

GETTERS_AND_SETTERS

	@Override
	public TYPEUpdater getUpdater(Registry registry) {
		return new TYPEUpdater(registry, DESCRIPTOR, this);
	}

	public static class TYPEUpdater extends ComponentUpdater {

		public TYPEUpdater(Registry registry, ComponentDescriptor componentDescriptor, TYPEObject object) {
			super(registry, componentDescriptor, object);
		}
		
UPDATERS

	}
}
