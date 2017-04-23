def self.case_object(un_dummy, &codigo)
	un_dummy.new_case_object(&codigo)
end


class Case_dummy
    def new_case_object(&un_bloque)		
		mi_objeto = Object.new
		mi_objeto.instance_eval &un_bloque

		proc = Proc.new do @nombre.to_s end
		mi_objeto.define_singleton_method(:to_s) do proc.call end
		mi_objeto.define_singleton_method(:clone) do mi_objeto end

		mi_objeto.freeze

		Object.const_set @nombre, mi_objeto
	end
end
