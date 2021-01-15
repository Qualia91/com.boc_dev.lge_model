
local components = 
	{
		Transform = {
			comment = "Transform of an object. Will affect the objects underneath it.",
      render = "false",
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
		Controllable = {
			comment = "Object that enables user control to a transform it is under.",
      render = "false",
			fields = {
				enableMove = {
					comment = "Can translate object",
					type = "boolean",
				},
				enableLook = {
					comment = "Can rotate object",
					type = "boolean",
				},
				speed = {
					comment = "translate speed",
					type = "float",
				},
				sensitivity = {
					comment = "rotation speed",
					type = "float",
				},
			}
		},
		ImpulseControllable = {
			comment = "Object that enables user control to a transform it is under via linear and angular momentum impulses.",
      render = "false",
			fields = {
				enableMove = {
					comment = "Can translate object.",
					type = "boolean",
				},
				enableRotate = {
					comment = "Can rotate object.",
					type = "boolean",
				},
				linearSpeed = {
					comment = "Translate speed.",
					type = "float",
				},
				angularSpeed = {
					comment = "Rotation speed.",
					type = "float",
				},
			}
		},
		RigidBody = {
			comment = "Component that gets affected by the rigid body system. It must be under a transform, and thats the one that gets transformed.",
      render = "false",
			fields = {
				mass = {
					comment = "Mass in kg's of entity.",
					type = "double",
				},
				dimensions = {
					comment = "Dimensions of entity.",
					type = "Vec3d",
				},
				linearMomentum = {
					comment = "linearMomentum of entity.",
					type = "Vec3d",
				},
				angularMomentum = {
					comment = "angularMomentum of entity",
					type = "Vec3d",
				},
				rigidBodyType = {
					comment = "Type of rigid body.",
					type = "RigidBodyObjectType",
				},
			}
		},
		Gravity = {
			comment = "Component to enable gravity on transform object above.",
      render = "false",
			fields = {
				simple = {
					comment = "If simple gravity is true, each object is accelerated towards negative z axis. If false, it uses universal law of gravitation",
					type = "boolean"
				},
				G = {
					comment = "Gravitational",
					type = "float"
				},
			}
		},
    Impulse = {
			comment = "Impulse given from player.",
      render = "false",
			fields = {
				linearVelocityImpulse = {
					comment = "linear velocity impulse of entity",
					type = "Vec3d",
				},
				angularVelocityImpulse = {
					comment = "angular velocity impulse of entity",
					type = "Vec3d",
				},
			}
		},
		Light = {
			comment = "Component that gets affected by the rigid body system. It must be under a transform, and thats the one that gets transformed.",
      render = "true",
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
			comment = "Skybox used in scene.",
      render = "true",
			fields = {
				skyboxType = {
					comment = "Type of geometry used to make sykbox. Can be CUBE or SPHERE",
					type = "SkyboxType"
				},
				texture = {
					comment = "Texture of the skybox",
					type = "String"
				},
				distance = {
					comment = "Distance the skybox is rendered at",
					type = "float"
				}
			}
		},
		Camera = {
			comment = "",
      render = "true",
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
					type = "int"
				},
				height = {
					comment = "",
					type = "int"
				},
				fov = {
					comment = "",
					type = "float"
				},
				cameraObjectType = {
					comment = "",
					type = "CameraObjectType"
				},
				CameraProjectionType = {
					comment = "",
					type = "CameraProjectionType"
				},
			}
		},
		Geometry = {
			comment = "",
      render = "true",
			fields = {
				localTransformation = {
					comment = "",
					type = "Matrix4f"
				},
				modelFile = {
					comment = "",
					type = "String"
				},
				material = {
					comment = "",
					type = "UUID"
				},
			}
		},
		Material = {
			comment = "",
      render = "true",
			fields = {
				diffuseColour = {
					comment = "",
					type = "Vec3f"
				},
				specularColour = {
					comment = "",
					type = "Vec3f"
				},
				shininess = {
					comment = "",
					type = "float"
				},
				reflectance = {
					comment = "",
					type = "float"
				},
			}
		},
		Texture = {
			comment = "",
      render = "true",
			fields = {
				path = {
					comment = "",
					type = "String"
				},
			}
		},
		NormalMap = {
			comment = "",
      render = "true",
			fields = {
				path = {
					comment = "",
					type = "String"
				},
			}
		},
		Text = {
			comment = "",
      render = "true",
			fields = {
				text = {
					comment = "",
					type = "String"
				},
				fontName = {
					comment = "",
					type = "String"
				},
				fontSize = {
					comment = "",
					type = "float"
				},
        fontAlignment = {
					comment = "",
					type = "FontAlignment"
				},
			}
		},
		WaterGeneration = {
			comment = "",
      render = "false",
			fields = {
				cellSpace = {
					comment = "",
					type = "int"
				},
				chunkSize = {
					comment = "",
					type = "int"
				},
				height = {
					comment = "",
					type = "int"
				},
			}
		},
		WaterChunk = {
			comment = "",
      render = "true",
			fields = {
        grid = {
					comment = "",
					type = "float[][]"
				},
				cellSpace = {
					comment = "",
					type = "int"
				},
			}
		},
		TerrainGeneration = {
			comment = "",
      render = "false",
			fields = {
				octaves = {
					comment = "",
					type = "int"
				},
				lacunarity = {
					comment = "",
					type = "float"
				},
				generationRange = {
					comment = "",
					type = "int"
				},
				cellSpace = {
					comment = "",
					type = "int"
				},
				chunkSize = {
					comment = "",
					type = "int"
				},
				segmentSize = {
					comment = "",
					type = "int"
				},
				amplitude = {
					comment = "",
					type = "int"
				},
        materialID = {
          comment = "",
          type = "UUID"
        },
			}
		},
		TerrainChunk = {
			comment = "",
      render = "true",
			fields = {
				index = {
					comment = "",
					type = "Vec2i"
				},
				grid = {
					comment = "",
					type = "float[][]"
				},
        cellSpace = {
					comment = "",
					type = "double"
				},
        origin = {
					comment = "",
					type = "Vec3f"
				},
        materialID = {
          comment = "",
          type = "UUID"
        },
			}
		},
		Boid = {
			comment = "",
      render = "false",
			fields = {
				goal = {
					comment = "",
					type = "Vec3f"
				},
				velocity = {
					comment = "",
					type = "Vec3f"
				},
				speed = {
					comment = "",
					type = "float"
				},
				radius = {
					comment = "",
					type = "float"
				},
				minSpeed = {
					comment = "",
					type = "float"
				},
				lengthAwayGroupSquared= {
					comment = "",
					type = "float"
				},
				lengthAwayMinSquared= {
					comment = "",
					type = "float"
				},
				boundScale= {
					comment = "",
					type = "float"
				},
				velocityMatchScale= {
					comment = "",
					type = "float"
				},
				antiCollideScale= {
					comment = "",
					type = "float"
				},
				perceivedCenterScale= {
					comment = "",
					type = "float"
				},
			}
		},
		Pickable = {
			comment = "",
      render = "true",
			fields = {
				active = {
					comment = "",
					type = "boolean"
				},
			}
		},
		Selectable = {
			comment = "",
      render = "false",
			fields = {
				selected = {
					comment = "",
					type = "boolean"
				},
				unselectedMaterialUUID = {
					comment = "",
					type = "UUID"
				},
				selectedMaterialUUID = {
					comment = "",
					type = "UUID"
				},
			}
		},
		SelectedItems = {
			comment = "",
      render = "false",
			fields = {
				pickingHappened = {
					comment = "Flag to tell selection system that a picking event occured and selection needs to clear and update",
					type = "boolean"
				},
			}
		},
		ParticleBody = {
			comment = "",
      render = "false",
			fields = {
				mass = {
					comment = "",
					type = "float"
				},
				velocity = {
					comment = "",
					type = "Vec3d"
				},
			}
		},
		ParticleSpring = {
			comment = "",
      render = "false",
			fields = {
				restLength = {
					comment = "",
					type = "float"
				},
				springConstant = {
					comment = "",
					type = "float"
				},
				dampingConstant = {
					comment = "",
					type = "float"
				},
			}
		},
		ViscousDrag = {
			comment = "",
      render = "false",
			fields = {
				coefficientOfDrag = {
					comment = "",
					type = "float"
				},
			}
		},
		Collision = {
			comment = "",
      render = "false",
			fields = {
				collisionRestitution = {
					comment = "",
					type = "float"
				},
			}
		},
		Mesh = {
			comment = "",
      render = "true",
			fields = {
				vertexPositions = {
					comment = "",
					type = "Vec3f[]"
				},
				index = {
					comment = "",
					type = "Vec3i"
				},
				materialID = {
					comment = "",
					type = "UUID"
				},
			}
		},
		MarchingCubeGeneration = {
			comment = "",
      render = "false",
			fields = {
				generationRange = {
					comment = "",
					type = "int"
				},
				chunkSize = {
					comment = "",
					type = "int"
				},
				materialID = {
					comment = "",
					type = "UUID"
				},
			}
		},
		Timer = {
			comment = "",
      render = "false",
			fields = {
				active = {
					comment = "",
					type = "boolean"
				},
				startFrame = {
					comment = "",
					type = "long"
				},
				timeoutLength = {
					comment = "",
					type = "long"
				},
				timeoutFlag = {
					comment = "",
					type = "boolean"
				},
				repeate = {
					comment = "",
					type = "boolean"
				},
				functionName = {
					comment = "this will be the name of what function is ativated when this timer runs out",
					type = "String"
				},
			}
		},
		Button = {
			comment = "",
      render = "false",
			fields = {
				active = {
					comment = "",
					type = "boolean"
				},
				functionName = {
					comment = "this will be the name of what function is activated when this button is pressed",
					type = "String"
				},
			}
		},
		List = {
			comment = "",
      render = "false",
			fields = {
				spacerZ = {
					comment = "space each object is from the next one in the z axis",
					type = "float"
				},
        spacerY = {
					comment = "space each object is from the next one in the y axis",
					type = "float"
				},
				indentZ = {
					comment = "indent of a sub list in the z axis",
					type = "float"
				},
        indentY = {
					comment = "indent of a sub list in the y axis",
					type = "float"
				},
			}
		},
    Script = {
			comment = "",
      render = "false",
			fields = {
				script = {
					comment = "Lua Script file. This is searched for within ",
					type = "String"
				},
			}
		},
	}

return components
