class Object
  def case_class(un_dummy, &codigo)
    un_dummy.definir_case_class(&codigo)
  end
end

module CaseClass
  def self.included(incluyente)
    incluyente.singleton_class.send(:alias_method, :_new, :new)
    incluyente.extend(ClassMethods)
    incluyente.include(InstanceMethods)
  end
  
  module ClassMethods
    def new(*args)
      super(*args).freeze
    end

    def inherited(from)
      raise 'No se puede heredar de una Case Class!'
    end
    
    def attr_accessor(*args)
      attr_reader *args

      define_method(:initialize) do |*valores|
        args.zip valores.each do |variable, valor|
          instance_variable_set("@#{variable}", valor)
        end
      end
    end
  end
  
  module InstanceMethods
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

      lambdas.each do |lambda|
        variable = "@#{lambda.parameters.first.last}"
        valor = copia.instance_variable_get(variable)

        raise "No existe la variable #{variable}!" unless valor

        copia.instance_variable_set(variable, lambda.call(valor))
      end

      copia.freeze
    end
  end
end
