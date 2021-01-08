module com.boc_dev.lge_model {
	requires com.boc_dev.maths;
	requires com.boc_dev.event_bus;

	opens com.boc_dev.lge_model.generated.components to luaj.jse.module;

	exports com.boc_dev.lge_model.generated.components;
	exports com.boc_dev.lge_model.generated.enums;
	exports com.boc_dev.lge_model.gcs;
	exports com.boc_dev.lge_model.systems;
	exports com.boc_dev.lge_model.bus;
}