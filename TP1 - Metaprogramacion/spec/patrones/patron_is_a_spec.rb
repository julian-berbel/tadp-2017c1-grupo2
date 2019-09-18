require_relative '../tp_inmutabilidad_spec'

describe 'Is_a' do
  describe '#===' do
    it 'da true si el objeto es de la clase especificada' do
      expect(is_a(Module) === Module.new).to be true
    end
    
    it 'da true si la clase especificada esta entre los ancestros del objeto' do
      expect(is_a(Module) === Class.new).to be true
    end
    
    it 'da false si la clase especificada no esta entre los ancestros del objeto' do
      expect(is_a(Module) === Object.new).to be false
    end
  end
end
