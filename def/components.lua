
local components = 
	{
		Transform = {
			comment = "Transform of an object. Will affect the objects underneath it.",
			fields = {
				position = {
					comment = "positon component of transform",
					type = "Vec3f",
				},
				scale = {
					comment = "scale component of transform",
					type = "Vec3f",
				},
				rotation = {
					comment = "rotation component of transform",
					type = "QuaternionF",
				}
			}
		},
		RigidBody = {
			comment = "Component that gets affected by the rigid body system. It must be under a transform, and thats the one that gets transformed.",
			fields = {
				mass = {
					comment = "Mass in kg's of entity",
					type = "double",
				},
				dimensions = {
					comment = "dimensions of entity",
					type = "Vec3d",
				},
				origin = {
					comment = "origin of entity",
					type = "Vec3d",
				},
				rotation = {
					comment = "rotation component of entity",
					type = "QuaternionD",
				},
				linearMomentum = {
					comment = "linearMomentum of entity",
					type = "Vec3d",
				},
				angularMomentum = {
					comment = "angularMomentum of entity",
					type = "Vec3d",
				},
				rigidBodyType = {
					comment = "Type of rigid body",
					type = "RigidBodyObjectType",
				},
			}
		},
		Light = {
			comment = "Component that gets affected by the rigid body system. It must be under a transform, and thats the one that gets transformed.",
			fields = {
				lightingType = {
					comment = "light type",
					type = "LightingType",
				},
				colour = {
					comment = "",
					type = "Vec3f",
				},
				direction = {
					comment = "",
					type = "Vec3f",
				},
				intensity = {
					comment = "",
					type = "float",
				},
				coneAngle = {
					comment = "",
					type = "float",
				},
				attenuationConstant = {
					comment = "",
					type = "float",
				},
				attenuationLinear = {
					comment = "",
					type = "float",
				},
				attenuationExponent = {
					comment = "",
					type = "float",
				},
			}
		},
		SkyBox = {
			comment = "",
			fields = {
				skyboxType = {
					comment = "Type of geometry used to make sykbox. Can be CUBE, SPHERE, or MODEL",
					type = "SkyboxType"
				},
				texture = {
					comment = "Texture of the skybox",
					type = "String"
				}
			}
		},
		Camera = {
			comment = "",
			fields = {
				near = {
					comment = "",
					type = "float"
				},
				far = {
					comment = "",
					type = "float"
				},
				width = {
					comment = "",
					type = "double"
				},
				height = {
					comment = "",
					type = "double"
				},
				fov = {
					comment = "",
					type = "double"
				},
				cameraObjectType = {
					comment = "",
					type = "CameraObjectType"
				},
			}
		},
		Geometry = {
			comment = "",
			fields = {
				geometryType = {
					comment = "",
					type = "GeometryType"
				},
				invertedNormals = {
					comment = "",
					type = "boolean"
				},
				texture = {
					comment = "",
					type = "String"
				},
				normalTexture = {
					comment = "",
					type = "String"
				},
				localTransformation = {
					comment = "",
					type = "double"
				},
			}
		}
	}

return components
