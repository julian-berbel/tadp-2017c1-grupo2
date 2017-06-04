require 'rspec'
require_relative '../../src/tp_inmutabilidad'

describe 'Buenos Defaults' do

    before do
        case_class Una_case_class do
            attr_accessor :una_variable, :otra_variable
        end

        @un_objeto = Una_case_class.new(3,8)
    end

	describe '#to_s' do
		it 'imprime el nombre de la clase seguido de los valores de sus atributos entre parentesis' do
			expect(@un_objeto.to_s).to eq 'Una_case_class(3, 8)'
		end
	end

	describe '#hash' do
		it 'devuelve la sumatoria de los hashes de los atributos + 7' do
			expect(@un_objeto.hash).to eq (3.hash + 8.hash + 7)
		end
	end

	describe '#==' do
		it 'da true para dos objetos de la misma clase y mismos valores de atributos' do
			otro_objeto_igual = Una_case_class.new(3,8)
			expect(@un_objeto == otro_objeto_igual).to be true
		end

		it 'da false para dos objetos de la misma clase y distintos valores de atributos' do
			un_objeto_con_distintos_atributos = Una_case_class.new(5,7)
			expect(@un_objeto == un_objeto_con_distintos_atributos).to be false
		end

		it 'da false para dos objetos de la misma clase y mismos valores de atributos' do
			case_class Otra_clase do
				attr_accessor :unaVariable, :otraVariable
			end
			un_objeto_con_distinta_clase = Otra_clase.new(3,8)
			expect(@un_objeto == un_objeto_con_distinta_clase).to be false
		end
	end
	
end
