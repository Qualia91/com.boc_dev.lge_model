
package <PACKAGE>.generated;

<IMPORTS>

<CLASS_COMMENT>
public class <TYPE>Object extends Component {

	<FIELDS>

	public <TYPE>Object(<CONSTRUCTION_PARAMS>) {
		super(ComponentType.<COMPONENT_TYPE>, name);
		<FIELDS_INITS>
		DESCRIPTOR
				.addFieldDescriptor(<FIELD_DESCRIPTOR>);
	}

	<GETTERS>

	<SETTERS>

	@Override
	public <TYPE>Updater getUpdater(Registry registry) {
		return new <TYPE>Updater(registry, DESCRIPTOR, this);
	}

	public static class <TYPE>Updater extends ComponentUpdater {

		public <TYPE>Updater(Registry registry, ComponentDescriptor componentDescriptor, <TYPE>Object object) {
			super(registry, componentDescriptor, object);
		}
		
		<UPDATERS>

	}
}
