local doc_folder = "documentation"

os.execute("mkdir " .. doc_folder)
local import_locations = { 
  QuaternionF = "com.boc_dev.maths.objects.QuaternionF",
  QuaternionD = "com.boc_dev.maths.objects.QuaternionD",
  Vec3f = "com.boc_dev.maths.objects.vector.Vec3f",
  Vec3d = "com.boc_dev.maths.objects.vector.Vec3d",
  Matrix4f = "com.boc_dev.maths.objects.matrix.Matrix4f",
  Vec2i = "com.boc_dev.maths.objects.vector.Vec2i",
  Vec3i = "com.boc_dev.maths.objects.vector.Vec3i"
}

local package = "com.boc_dev.lge_model.generated.components."
local enums_package = "com.boc_dev.lge_model.generated.enums."

local components = require "components"
local enums = require "enums"

local output_string = "<h1 align='center'>Game Model</h1>\n"

output_string = output_string .. "<details><summary>Components</summary>\n"

-- loop over components in components tabel
for component_name, component in pairs(components) do
  
  -- generate fields
  local comment = component.comment
  local to_render = component.render
  
  output_string = output_string .. "<h2>" .. component_name .. (to_render == "true" and " (Renderable)" or " (Not Renderable)") .."</h2>\n"
  output_string = output_string .. "<p><b>Description:</b> " .. comment .. "</i></p>\n"
  output_string = output_string .. "<p><b>Class:</b> " .. package .. component_name .. ".java" .. "</p>\n"
  output_string = output_string .. "<p><b>Fields:</b></p>\n<ol>"
  
  for field_name, field_data in pairs(component.fields) do
    
    local field_type = field_data.type
    
    -- check for []
    local dimensions = 0
    for word in string.gmatch(field_type, "%[%]") do
      dimensions = dimensions + 1
    end
    
    field_type = string.gsub(field_type, "%[%]", "")
    
    if import_locations[field_type] then
      
      field_type = string.gsub(field_type, "%[%]", "")
    
      field_type = import_locations[field_type]
      
      while (dimensions > 0) do
        field_type = field_type .. "[]"
        dimensions = dimensions - 1
      end
    else
      field_type = field_data.type
    end
  
    output_string = output_string .. "<li><p>" .. field_name .. " (<i>" .. field_type .. "</i>): " .. field_data.comment .. "</p></li>"
    
  end
  
  output_string = output_string .. "</ol>\n"
  
end

output_string = output_string .. "</details>\n<details><summary>Enumerations</summary>"

for enum_name, enum_data in pairs(enums) do
  
  output_string = output_string .. "<h2>" .. enum_name .. "</h2>"
  output_string = output_string .. "<p><b>Description:</b> " .. enum_data.comment .. "</i></p>\n"
  output_string = output_string .. "<p><b>Class:</b> " .. enums_package .. enum_name .. ".java" .. "</p>\n"
  output_string = output_string .. "<p><b>Values:</b></p>\n<ol>"

  output_string = output_string .. "<p>"
  
  for name_count = 1, #enum_data.field do
  
    output_string = output_string .. "<li>" .. enum_data.field[name_count]:upper() .. "</li>"
  
  end

  output_string = output_string .. "</ol>"
  
end

output_string = output_string .. "</details>\n"

local file = io.open(doc_folder .. "/components_markdown.html", "w")

io.output(file)

io.write(output_string)

-- closes the open file
file:close()