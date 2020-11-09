module com.nick.wood.game_engine.gcs_model {
	requires com.nick.wood.maths;
	requires com.nick.wood.game_engine.event_bus;

	exports com.nick.wood.game_engine.gcs_model.generated.components;
	exports com.nick.wood.game_engine.gcs_model.generated.enums;
	exports com.nick.wood.game_engine.gcs_model.gcs;
	exports com.nick.wood.game_engine.gcs_model.systems;
	exports com.nick.wood.game_engine.gcs_model.bus;
}