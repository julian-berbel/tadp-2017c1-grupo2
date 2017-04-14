def self.case_class(un_dummy, &codigo)
	un_dummy.class_eval(&codigo)
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
        Proc.new do |var|
			self.instance_variable_get(var)
        end
    end

	def to_s
		self.class.to_s + 
		'(' + 
		self.instance_variables.map(&self.var_values)
        .join(', ') +
		')'
	end

	def ==(otro)
		self.class == otro.class && 
		self.instance_variables.map(&self.var_values) == 
		otro.instance_variables.map(&otro.var_values)
	end

    def hash
        7 +
        self.instance_variables.map(&self.var_values)
        .map {|var| var.hash}
        .reduce(0,:+)
    end

	def self.attr_accessor(*args)
		send :attr_reader, *args
	end
	
	def self.case_class?
		true
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
	
	def class_eval(&un_bloque)
		mi_clase = Object.const_set @sym, Class.new(@padre)
		mi_clase.class_eval(&Case_Class)
		mi_clase.class_eval &un_bloque
	end
end
