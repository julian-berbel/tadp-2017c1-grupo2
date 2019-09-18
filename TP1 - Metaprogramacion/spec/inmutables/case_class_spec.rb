require_relative '../tp_inmutabilidad_spec'

describe 'Case Class' do
  before do
    case_class UnaCaseClass do
      attr_accessor :una_variable, :otra_variable
    end
  end

  let(:un_objeto) { UnaCaseClass(3, 8) }

  describe 'case_class' do
    it 'crea una clase' do
      expect(UnaCaseClass.class).to eq Class
    end

    context 'si no le doy una clase padre' do
      it 'es subclase de Object' do
        expect(UnaCaseClass.superclass).to eq Object
      end
    end

    context 'si le doy una clase padre' do
      before do
        class UnaClase
        end

        case_class OtraClase < UnaClase do end
      end

      it 'es subclase de esa clase' do
          expect(OtraClase.superclass).to eq UnaClase
        end
      end
    end

    describe '.new' do
      it 'crea un objeto de la clase' do
        expect(UnaCaseClass.new.class).to eq UnaCaseClass
      end

      context 'si le doy parametros' do
        it 'setea las variables de instancia' do
          expect(UnaCaseClass.new(1, 2).var_values).to eq [1, 2]
        end
      end
    end

    describe '.inherited' do
      it 'prohibe heredar de una Case Class' do
        expect { class UnaClase < UnaCaseClass; end }.to raise_error 'No se puede heredar de una Case Class!'
      end
    end

    describe '#copy' do
      context 'si no le paso lambdas' do
        it 'deberia devolver el mismo objeto' do
          expect(un_objeto.copy).to eq un_objeto
        end
      end

      context 'si le paso lambdas' do
        it 'deberia devolver el mismo objeto modificado' do
          expect(un_objeto.copy ->(una_variable) { una_variable + 5 }).to eq UnaCaseClass(8, 8)
        end
        
        it 'deberia poder tomar mas de una lambda' do
          expect(un_objeto.copy ->(una_variable) { una_variable + 5 }, ->(otra_variable) { 6 }).to eq UnaCaseClass(8, 6)
        end
        
        it 'si el atributo no existe deberia romper' do
          expect{ un_objeto.copy ->(una_variable_que_no_tiene) { 2 } }.to raise_error RuntimeError
        end
      end
    end
  end
