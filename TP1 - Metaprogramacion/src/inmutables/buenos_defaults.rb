module Buenos_Defaults

	def self.definir_si_no_esta_definido_en(donde, nombre_metodo, ancestro, &codigo)
		if donde.instance_method(nombre_metodo).owner == ancestro
			donde.send(:define_method, nombre_metodo, &codigo)
		end
	end
	
	def self.included(incluyente)
		definir_si_no_esta_definido_en(incluyente, :to_s, Kernel) do
			"#{self.class}(#{var_values.join(', ')})"
		end
		
		definir_si_no_esta_definido_en(incluyente, :==, BasicObject) do |otro|
			to_s == otro.to_s
		end
		
		definir_si_no_esta_definido_en(incluyente, :hash, Kernel) do
			var_values.map(&:hash).reduce(0,:+) + 7
		end
	end

end
