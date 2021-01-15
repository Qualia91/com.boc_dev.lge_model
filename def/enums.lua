
local enums = 
	{
		LightingType = {
      comment = "Type of light create in the graphics engine.",
			field = {
        "POINT",
        "SPOT",
        "DIRECTIONAL"
      }
		},
		SkyboxType = {
			comment = "Type of object used for the skybox.",
			field = {
        "SPHERE",
        "CUBE"
      }
		},
		CameraObjectType = {
			comment = "Camera type. Primary is the one used as the main camera. FBO is a secondary camera that can be used to create textures to be rendered to an object.",
			field = {
        "PRIMARY",
        "FBO"
      }
		},
		CameraProjectionType = {
			comment = "Type of projection matrix used for camera. Perspective produces a normal 3D camera view, orthographic produces a 2D view.",
			field = {
        "PERSPECTIVE",
        "ORTHOGRAPHIC"
      }
		},
		RigidBodyObjectType = {
			comment = "Type of rigid body created, used in rigid body sim to generate the moment of inertia tensor.",
			field = {
        "CUBOID",
        "SPHERE",
        "SPHERE_INNER",
        "NONE"
      }
		},
		CollisionShape = {
			comment = "Shape used to define how it responds to a collision with another collision shape.",
			field = {
        "SPEHER",
        "CUBOID"
      }
		},
		FontAlignment = {
			comment = "Used to set allignment of text.",
			field = {
        "BEGIN",
        "END",
        "CENTER"
      }
		},
		Orientation = {
			comment = "Direction in which a list positions the objects within it.",
			field = {
        "HORIZONTAL",
        "VERTICLE"
      }
		},
	}

return enums
