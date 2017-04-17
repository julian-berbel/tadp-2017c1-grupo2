def self.case_class(un_dummy, &codigo)
	un_dummy.class_eval(&codigo)
end

def self.case_object(un_dummy, &codigo)
	un_dummy.new_case_object(&codigo)
end

class Object
	def case_class?
		false
	end

	def self.const_missing(nombre)
		Case_dummy.new(nombre)
	end
end

class Class
	alias_method :_new, :new
	def new(*args)
		raise 'Error! No se puede heredar de una Case Class!' if args.first.case_class?
		_new(*args)
	end
end

# Codigo que entienden las case classes aca
Case_Class = Proc.new do
    self.singleton_class.send(:alias_method, :oldNew, :new)
    def self.new(*args)
        self.oldNew(*args).freeze
    end

    def var_values
        self.instance_variables.map do |var|
			self.instance_variable_get(var)
        end
    end

	# Buenos Defaults
	def to_s
		self.class.to_s + 
		'(' + 
		self.var_values
        .join(', ') +
		')'
	end

	def ==(otro)
		self.class == otro.class && 
		self.var_values == 
		otro.var_values
	end

    def hash
        7 +
        self.var_values
        .map {|var| var.hash}
        .reduce(0,:+)
    end
	# Buenos Defaults

	def self.attr_accessor(*args)
		send :attr_reader, *args
		define_method(:initialize) do |*valores|
			args.zip valores.each do |par_variable_valor|
				instance_variable_set('@' + par_variable_valor.first.to_s, par_variable_valor.last)
			end
		end
	end
	
	def self.case_class?
		true
	end

	def copy(*lambdas)
		copia = self.class.oldNew(*var_values)
		lambdas.each { |lambda|
			variable = '@' + lambda.parameters.first.last.to_s
			copia.instance_variable_set(variable, lambda.call(copia.instance_variable_get(variable)))
		}
		copia.freeze
	end
end

class Case_dummy
	attr_accessor :sym, :padre
	
	def initialize(sym)
		@sym = sym
		@padre = Object
	end
	
	def <(una_clase)
		@padre = una_clase
		self
	end
	
	def new_class(padre, un_bloque)
		mi_clase = Class.new(padre)
		mi_clase.class_eval &Case_Class
		mi_clase.class_eval &un_bloque
		mi_clase
	end
	
	def class_eval(&un_bloque)
		Object.const_set @sym, new_class(@padre, un_bloque)
		
		Object.send(:define_method, @sym) do |*args|
			Object.const_get(__method__).new(*args)
		end
	end
	
	def new_case_object(&un_bloque)		
		mi_objeto = Object.new
		mi_objeto.instance_eval &un_bloque
		proc = Proc.new do @sym.to_s end
		mi_objeto.define_singleton_method(:to_s) do proc.call end
		mi_objeto.define_singleton_method(:clone) do mi_objeto end
		mi_objeto.freeze
		Object.const_set @sym, mi_objeto
	end
end
