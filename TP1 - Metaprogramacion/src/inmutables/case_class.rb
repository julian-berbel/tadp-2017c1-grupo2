class Object
  def case_class(un_dummy, &codigo)
    un_dummy.definir_case_class(&codigo)
  end
end

module CaseClass
  def self.included(incluyente)
    incluyente.extend(ClassMethods)
    incluyente.include(InstanceMethods)
  end

  module ClassMethods
    def new(*args)
      super.freeze
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
      instance_variables.map { |var| instance_variable_get var }
    end

    def ===(otro)
      var_values.zip(otro.var_values).all? { |patron, variable| patron === variable }
    end

    def copy(*lambdas)
      copia = dup

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
