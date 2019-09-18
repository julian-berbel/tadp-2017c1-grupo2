module BuenosDefaults
  class << self
    def definir_si_no_fue_redefinido(donde, nombre_metodo, &codigo)
      if Object.ancestors.include?(donde.instance_method(nombre_metodo).owner)
        donde.send(:define_method, nombre_metodo, &codigo)
      end
    end
    
    def included(incluyente)
      definir_si_no_fue_redefinido(incluyente, :to_s) do
        "#{self.class}(#{var_values.join(', ')})"
      end
      
      definir_si_no_fue_redefinido(incluyente, :==) do |otro|
        to_s == otro.to_s
      end
      
      definir_si_no_fue_redefinido(incluyente, :hash) do
        var_values.map(&:hash).reduce(7, :+)
      end
    end
  end
end
