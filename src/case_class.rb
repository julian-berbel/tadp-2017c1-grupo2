def self.case_class(unProxy, &codigo)
	unProxy.class_eval &codigo
end

def Object.const_missing(nombre)
  Case_proxy.new nombre
end

class Case_proxy
	attr_accessor :sym, :padre
	
	def initialize(sym)
		@sym = sym
		@padre = Object
	end
	
	def <(unaClase)
		@padre = unaClase
		self
	end
	
	def class_eval(&unBloque) 
		mi_clase = Object.const_set @sym, Class.new(@padre)
		mi_clase.class_eval &unBloque
		mi_clase.include Case_Class
	end
end

module Case_Class
	
end