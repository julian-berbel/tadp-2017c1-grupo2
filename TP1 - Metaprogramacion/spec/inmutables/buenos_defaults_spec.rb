require_relative '../tp_inmutabilidad_spec'

describe 'Buenos Defaults' do
  before do
    case_class UnaCaseClass do
      attr_accessor :una_variable, :otra_variable
    end
  end

  let(:un_objeto) { UnaCaseClass(3, 8) }

  describe '#to_s' do
    it 'imprime el nombre de la clase seguido de los valores de sus atributos entre parentesis' do
      expect(un_objeto.to_s).to eq 'UnaCaseClass(3, 8)'
    end
  end

  describe '#hash' do
    it 'devuelve la sumatoria de los hashes de los atributos + 7' do
      expect(un_objeto.hash).to eq (3.hash + 8.hash + 7)
    end
  end

  describe '#==' do
    context 'da true para dos objetos de la misma clase y mismos valores de atributos' do
      let(:otro_objeto_igual) { UnaCaseClass(3, 8) }

      it { expect(un_objeto).to eq otro_objeto_igual }
    end

    context 'da false para dos objetos de la misma clase y distintos valores de atributos' do
      let(:un_objeto_con_distintos_atributos) { UnaCaseClass(5, 7) }

      it { expect(un_objeto).to_not eq un_objeto_con_distintos_atributos }
    end

    context 'da false para dos objetos distintas clases y mismos valores de atributos' do
      before do
        case_class OtraCaseClass do
          attr_accessor :una_variable, :otra_variable
        end
      end

      let(:un_objeto_con_distinta_clase) { OtraCaseClass(3, 8) }

      it { expect(un_objeto).to_not eq un_objeto_con_distinta_clase }
    end
  end
end
