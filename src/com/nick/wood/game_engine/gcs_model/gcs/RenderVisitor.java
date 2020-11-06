package com.nick.wood.game_engine.gcs_model.gcs;

import com.nick.wood.game_engine.gcs_model.generated.components.*;
import com.nick.wood.maths.objects.matrix.Matrix4f;

public interface RenderVisitor {

	void sendCreateUpdate(GeometryObject geometryObject);
	void sendCreateUpdate(MaterialObject materialObject);
	void sendCreateUpdate(CameraObject cameraObject);
	void sendCreateUpdate(LightObject lightObject);
	void sendCreateUpdate(SkyBoxObject skyBoxObject);
	void sendCreateUpdate(TextObject textObject);
	void sendCreateUpdate(TextureObject textureObject);
	void sendCreateUpdate(NormalMapObject normalMapObject);
	void sendCreateUpdate(TerrainChunkObject terrainChunkObject);

	void sendInstanceUpdate(GeometryObject geometryObject, Matrix4f newTransform);
	void sendInstanceUpdate(MaterialObject materialObject, Matrix4f newTransform);
	void sendInstanceUpdate(CameraObject cameraObject, Matrix4f newTransform);
	void sendInstanceUpdate(LightObject lightObject, Matrix4f newTransform);
	void sendInstanceUpdate(SkyBoxObject skyBoxObject, Matrix4f newTransform);
	void sendInstanceUpdate(TextObject textObject, Matrix4f newTransform);
	void sendInstanceUpdate(TextureObject textureObject, Matrix4f newTransform);
	void sendInstanceUpdate(NormalMapObject normalMapObject, Matrix4f translation);
	void sendInstanceUpdate(TerrainChunkObject terrainChunkObject, Matrix4f translation);

	void sendDeleteUpdate(GeometryObject geometryObject);
	void sendDeleteUpdate(MaterialObject materialObject);
	void sendDeleteUpdate(CameraObject cameraObject);
	void sendDeleteUpdate(LightObject lightObject);
	void sendDeleteUpdate(SkyBoxObject skyBoxObject);
	void sendDeleteUpdate(TextObject textObject);
	void sendDeleteUpdate(TextureObject textureObject);
	void sendDeleteUpdate(NormalMapObject normalMapObject);
	void sendDeleteUpdate(TerrainChunkObject terrainChunkObject);
}
