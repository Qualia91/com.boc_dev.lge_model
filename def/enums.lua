
local enums = 
	{
		GeometryType = {
			"SPHERE",
			"CUBOID",
			"SQUARE",
			"TEXT",
			"MODEL",
			"TERRAIN",
			"POINT",
			"WATER",
			"TRIANGLE",
			"CIRCLE"
		},
		LightingType = {
			"POINT",
			"SPOT",
			"DIRECTIONAL"
		},
		SkyboxType = {
			"SPHERE",
			"CUBE",
			"MODEL"
		},
		CameraObjectType = {
			"PRIMARY",
			"FBO"
		},
		RigidBodyObjectType = {
			"CUBOID",
			"SPHERE",
			"SPHERE_INNER",
			"NONE"
		}
	}

return enums
