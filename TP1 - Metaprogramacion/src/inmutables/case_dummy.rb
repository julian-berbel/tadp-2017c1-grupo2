class Object
    def self.const_missing(nombre)
		Case_dummy.new(nombre)
	end
end

class Case_dummy
	def initialize(nombre)
		@nombre = nombre
		@padre = Object
	end
	
	def <(una_clase)
		@padre = una_clase
		self
	end
	
	def class_eval(&un_bloque)
        mi_clase = Object.const_set @nombre, Class.new(@padre)
		mi_clase.include(Case_Class)
		mi_clase.class_eval(&un_bloque)
		mi_clase.include(Buenos_Defaults)
		
		Object.send(:define_method, @nombre) do |*args|
			Object.const_get(__method__).new(*args)
		end
	end
end
