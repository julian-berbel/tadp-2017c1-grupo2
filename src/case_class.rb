class Object
	def case_class?
		false
	end
end

def self.case_class(un_dummy, &codigo)
	un_dummy.class_eval(&codigo)
end

# Codigo que entienden las case classes aca
Case_Class = Proc.new do
    singleton_class.send(:alias_method, :_new, :new)
    def self.new(*args)
        _new(*args).freeze
    end

    def self.inherited(from)
        raise 'No se puede heredar de una Case Class!'
    end

    def var_values
        instance_variables.map do |var|
			instance_variable_get(var)
        end
    end

	# Buenos Defaults
	def to_s
        "#{self.class}(#{var_values.join(', ')})"
	end

	def ==(otro)
		to_s == otro.to_s
	end

    def hash
        7 +
        var_values
        .map(&:hash)
        .reduce(0,:+)
    end
	# Buenos Defaults

    
    def ===(otro)
		var_values.zip(otro.var_values).all? do |patron, variable|
            patron === variable
        end
	end

	def self.attr_accessor(*args)
		send :attr_reader, *args
		define_method(:initialize) do |*valores|
			args.zip valores.each do |variable, valor|
				instance_variable_set('@' + variable.to_s, valor)
			end
		end
	end
	
	def self.case_class?
		true
	end

	def copy(*lambdas)
		copia = self.class._new(*var_values)
		lambdas.each { |lambda|
			variable = '@' + lambda.parameters.first.last.to_s
            raise "No existe la variable #{variable}!" if !copia.instance_variable_get(variable)
			copia.instance_variable_set(variable, lambda.call(copia.instance_variable_get(variable)))
		}
		copia.freeze
	end
end
