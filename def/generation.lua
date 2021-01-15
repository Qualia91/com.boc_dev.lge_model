function pairsByKeys (t, f)
      local a = {}
      for n in pairs(t) do table.insert(a, n) end
      table.sort(a, f)
      local i = 0      -- iterator variable
      local iter = function ()   -- iterator function
        i = i + 1
        if a[i] == nil then return nil
        else return a[i], t[a[i]]
        end
      end
      return iter
    end

local generated_folder = "src\\main\\java\\com\\boc_dev\\lge_model\\generated\\generated\\"
local folder = "src\\main\\java\\com\\boc_dev\\lge_model\\generated\\components\\"
local object_folder = "src\\main\\java\\com\\boc_dev\\lge_model\\generated\\objects\\"
local enums_folder = "src\\main\\java\\com\\boc_dev\\lge_model\\generated\\enums\\"
local package = "com.boc_dev.lge_model.generated.components"
local enums_package = "com.boc_dev.lge_model.generated.enums"

-- make directories
os.execute("mkdir ..\\" .. object_folder)
os.execute("mkdir ..\\" .. folder)
os.execute("mkdir ..\\" .. enums_folder)

local import_locations = { 
  QuaternionF = "com.boc_dev.maths.objects.QuaternionF",
  QuaternionD = "com.boc_dev.maths.objects.QuaternionD",
  Vec3f = "com.boc_dev.maths.objects.vector.Vec3f",
  Vec3d = "com.boc_dev.maths.objects.vector.Vec3d",
  Matrix4f = "com.boc_dev.maths.objects.matrix.Matrix4f",
  Vec2i = "com.boc_dev.maths.objects.vector.Vec2i",
  Vec3i = "com.boc_dev.maths.objects.vector.Vec3i",
  UUID = "java.util.UUID"
}

-- components table
local components = require "components"
local enums = require "enums"
local my_io = require "my_io"


-- tests the functions above
local file = 'class_template.java'
local lines = my_io.lines_from(file)

function firstToUpper(str)
    return (str:gsub("^%l", string.upper))
end


function generate_fields(fields)
  
  local return_string = ""
  
  for k, v in pairs(fields) do
      
      return_string = return_string .. "\t// " .. v.comment .. "\n"
      return_string = return_string .. "\tprivate " .. v.type .. " " .. k .. ";\n"
      
  end
  
  return return_string
end

function generate_construction_params(fields)
  
  local return_string = ""
  
  for k, v in pairsByKeys(fields) do
      
      return_string = return_string .. v.type .. " " .. k .. ", "
      
  end
  
  return return_string:sub(1, -3)
end

function generate_field_inits(fields)
  
  local return_string = ""
  
  for k, v in pairs(fields) do
      
      return_string = return_string .. "\t\tthis." .. k .. " = " .. k .. ";\n"
      
  end
  
  return return_string
end

function generate_field_descriptor_creation(fields)
  
  local return_string = ""
  
  for k, v in pairs(fields) do
      
      return_string = return_string .. "\t\t\t.addFieldDescriptor(\"" .. k .. "\", this::set" .. firstToUpper(k) .. ")\n" 
      
  end
  
  return return_string:sub(1, -2)
end

function generate_getters_and_setters(fields)
  
  local return_string = ""
  
  for k, v in pairs(fields) do
      
      -- getter
      return_string = return_string .. "\tpublic " .. v.type .. " get" .. firstToUpper(k) .. "() {\n" 
      return_string = return_string .. "\t\treturn " .. k .. ";\n\t}\n\n"
      
      -- setter
      return_string = return_string .. "\tprivate void set" .. firstToUpper(k) .. "(" .. v.type .. " " .. k .. ") {\n"
      return_string = return_string .. "\t\tthis." .. k .. " = " .. k .. ";\n\t}\n\n"
      
      
  end
  
  return return_string:sub(1, -2)
end

function generate_updaters(fields, class_name)
  
  local return_string = ""
  
  for k, v in pairs(fields) do
      
      return_string = return_string .. "\t\tpublic " .. class_name .. "Updater set" .. firstToUpper(k) .. "(" .. v.type .. " " .. k .. ") {\n"
      
      
      return_string = return_string .. "\t\t\tsetValue((" .. class_name .. "Object obj) -> obj.set" .. firstToUpper(k) .. "(" .. k .. "), \"" .. k .. "\");\n"
      
      return_string = return_string .. "\t\t\treturn this;\n\t\t}\n\n"
      
      
  end
  
  return return_string:sub(1, -2)
end

function generate_imports(fields, import_locations, render)
  
  import_needed = {}
  
  for k, v in pairs(fields) do
    
    local typeStr = ""
    if string.match(v.type, "%[%]") then
      typeStr = v.type:gsub("%[%]", "")
    else
      typeStr = v.type
    end
    
    import_needed[typeStr] = true
      
  end
  
  return_string = "import com.boc_dev.lge_model.gcs.*;\n"
  return_string = return_string .. "import com.boc_dev.lge_model.generated.enums.*;\n"
  
  if render == "true" then
    
    if not string.find(return_string, "com.boc_dev.maths.objects.matrix.Matrix4f") then
  
      return_string = return_string .. "import com.boc_dev.maths.objects.matrix.Matrix4f;\n"
    
    end
    
  end
  
  
  
  for k, v in pairs(import_needed) do
    
    if import_locations[k] then
    
      return_string = return_string .. "import " .. import_locations[k] .. ";\n"
    
    end
      
  end
    
  
  return return_string
end


function generate_component_class(component_name, component, lines, folder)
  
  -- generate fields
  field_string = generate_fields(component.fields)
  construction_params_string = generate_construction_params(component.fields)
  field_inits_string = generate_field_inits(component.fields)
  field_descriptor_creation_string = generate_field_descriptor_creation(component.fields)
  getters_and_setters = generate_getters_and_setters(component.fields)
  updaters_string = generate_updaters(component.fields, component_name)
  imports_string = generate_imports(component.fields, import_locations, component.render)
  
  update_render_function = ""
  
  if component.render == "true" then
    update_render_function = update_render_function .. "\n\n\t@Override\n"
    update_render_function = update_render_function .. "\tpublic void createRenderable(RenderVisitor renderVisitor) {\n"
    update_render_function = update_render_function .. "\t\trenderVisitor.sendCreateUpdate(this);\n\t}\n\n"
    update_render_function = update_render_function .. "\n\n\t@Override\n"
    update_render_function = update_render_function .. "\tpublic void updateRenderable(RenderVisitor renderVisitor, Matrix4f translation) {\n"
    update_render_function = update_render_function .. "\t\trenderVisitor.sendInstanceUpdate(this, translation);\n\t}\n"
    update_render_function = update_render_function .. "\n\n\t@Override\n"
    update_render_function = update_render_function .. "\tpublic void deleteRenderable(RenderVisitor renderVisitor) {\n"
    update_render_function = update_render_function .. "\t\trenderVisitor.sendDeleteUpdate(this);\n\t}\n"
    
  end

  -- read template and replace tags with current component data
  local new_file_text = ""
  for k,v in pairs(lines) do
    
    new_file_text = new_file_text .. 
      v
        :gsub("TYPE", component_name)
        :gsub("PACKAGE", package)
        :gsub("CLASS_COMMENT", component.comment)
        :gsub("COMPONENT_T", component_name:upper())
        :gsub("CONSTRUCTION_PARAMS", construction_params_string)
        :gsub("FIELDS", field_string)
        :gsub("FIELD_INITS", field_inits_string)
        :gsub("FIELD_DESCRIPTOR_CREATION", field_descriptor_creation_string)
        :gsub("GETTERS_AND_SETTERS", getters_and_setters)
        :gsub("UPDATERS", updaters_string)
        :gsub("IMPORTS", imports_string)
        :gsub("UPDATERENDERABLEFUNCTION", update_render_function)
        .. "\n"
        
  end

  -- Opens a file in append mode
  file = io.open("..\\" .. folder .. component_name .. "Object.java", "w")

  io.output(file)

  -- appends a word test to the last line of the file
  io.write(new_file_text)

  -- closes the open file
  file:close()
  
end

function generate_component_enum(enum_name, enum_data, enums_package)
  
  enum_file_string = "package " .. enums_package .. ";\n\n" ..
                     "/**" .. enum_data.comment .. "\n*/\n" ..
                     "public enum " .. enum_name .. " {\n"
  
  for name_count = 1, #enum_data.field do
  
    enum_file_string = enum_file_string .. "\t" .. enum_data.field[name_count]:upper() .. ",\n"
  
  end

  return enum_file_string:sub(1, -3) .. "\n}"
    
end

enum_file_string = "package " .. package .. ";\n\npublic enum ComponentType {\n"


-- loop over components in components tabel
for k, v in pairs(components) do
  generate_component_class(k, v, lines, folder)
  
  enum_file_string = enum_file_string .. "\t" .. k:upper() .. "(" .. v.render .. "),\n"
  
end


enum_file_string = enum_file_string:sub(1, -3) .. ";\n\n"
enum_file_string = enum_file_string .. "\tprivate final boolean render;\n\n"
enum_file_string = enum_file_string .. "\tComponentType(boolean render) {\n"
enum_file_string = enum_file_string .. "\t\tthis.render = render;\n"
enum_file_string = enum_file_string .. "\t}\n\n"
enum_file_string = enum_file_string .. "\tpublic boolean isRender() {\n"
enum_file_string = enum_file_string .. "\t\treturn render;\n"
enum_file_string = enum_file_string .. "\t}\n"
enum_file_string = enum_file_string .. "}"

-- Opens a file in append mode
file = io.open("..\\" .. folder .. "\\ComponentType.java", "w")

io.output(file)

-- appends a word test to the last line of the file
io.write(enum_file_string)

-- closes the open file
file:close()


-- generate enums
for enum_name, enums_data in pairs(enums) do
  enum_file_string = generate_component_enum(enum_name, enums_data, enums_package)
  
  -- Opens a file in append mode
  file = io.open("..\\" .. enums_folder .. "\\" .. enum_name .. ".java", "w")

  io.output(file)

  -- appends a word test to the last line of the file
  io.write(enum_file_string)

  -- closes the open file
  file:close()
end
