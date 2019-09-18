class Object
  def self.const_missing(nombre)
    CaseDummy.new(nombre)
  end
end

class CaseDummy
  def initialize(nombre)
    @nombre = nombre
    @padre = Object
  end
  
  def <(una_clase)
    @padre = una_clase
    self
  end
  
  def definir_case_class(&un_bloque)
    mi_clase = Object.const_set @nombre, Class.new(@padre)
    mi_clase.include(CaseClass)
    mi_clase.class_eval(&un_bloque)
    mi_clase.include(BuenosDefaults)
    
    nombre = @nombre
    Object.send(:define_method, @nombre) do |*args|
      Object.const_get(nombre).new(*args)
    end
  end
end
