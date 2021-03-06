require_relative '../tp_inmutabilidad_spec'

describe 'Has' do
  describe '#===' do
    context 'si el objeto tiene el atributo' do
      before do
        case_class UnaCaseClass do
          attr_accessor :una_variable
        end
      end

      let(:un_objeto) { UnaCaseClass(4) }
    
      it 'y vale lo mismo da true' do
        expect(has(:una_variable, 4) === un_objeto).to be true
      end
      
      it 'y no vale lo mismo da false' do
        expect(has(:una_variable, 6) === un_objeto).to be false
      end
    end
    
    it 'si el objeto no tiene el atributo da false' do
      expect(has(:una_variable, 4) === Object.new).to be false
    end
  end
end
