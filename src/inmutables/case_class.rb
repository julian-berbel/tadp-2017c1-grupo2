class Object
	def case_class?
		false
	end
end

def self.case_class(un_dummy, &codigo)
	un_dummy.class_eval(&codigo)
end

# Codigo que entienden las case classes aca
module Case_Class

	def self.included(incluyente)
		incluyente.singleton_class.send(:alias_method, :_new, :new)
		incluyente.extend(Class_Methods)
		incluyente.include(Instance_Methods)
	end
	
	module Class_Methods
			
		def new(*args)
			super(*args).freeze
		end

		def inherited(from)
			raise 'No se puede heredar de una Case Class!'
		end
		
		def attr_accessor(*args)
			attr_reader *args # hace falta send?
			define_method(:initialize) do |*valores|
				args.zip valores.each do |variable, valor|
					instance_variable_set('@' + variable.to_s, valor)
				end
			end
		end
		
		def case_class?
			true
		end
		
	end
	
	module Instance_Methods
			
		def var_values
			instance_variables.map do |var|
				instance_variable_get(var)
			end
		end
		
		def ===(otro)
			var_values.zip(otro.var_values).all? do |patron, variable|
				patron === variable
			end
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
	
end
