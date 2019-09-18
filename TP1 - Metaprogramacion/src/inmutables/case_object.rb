class Object
  def case_object(un_dummy, &codigo)
    un_dummy.definir_case_dummy(&codigo)
  end
end

class CaseDummy
  def definir_case_dummy(&un_bloque)    
    mi_objeto = Object.new
    mi_objeto.instance_eval &un_bloque

    nombre = @nombre.to_s
    mi_objeto.define_singleton_method(:to_s) { nombre }

    def mi_objeto.clone
      self
    end

    mi_objeto.freeze

    Object.const_set @nombre, mi_objeto
  end
end
