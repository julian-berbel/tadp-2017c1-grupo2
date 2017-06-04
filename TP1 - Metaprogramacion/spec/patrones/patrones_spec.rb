require 'rspec'
require_relative '../../src/tp_inmutabilidad'

require_relative 'patron_has_spec'
require_relative 'patron_is_a_spec'

describe 'Comparacion Estructural' do

	describe '#===' do
		before do
			case_class Alumno do
				attr_accessor :nombre, :estado
			end

			case_object Cursando do
			end

			case_class Termino do
				attr_accessor :nota
			end
		end
		
		it 'pasa si el objeto cumple con los patrones dados' do
			alumno = Alumno("Jose", Termino(9))
			valor =
				case alumno
					when Alumno("Jose", Termino(7))
						5
					when Alumno("Jose", Aprobo)
						7
					when Alumno("Jose", has(:nota, 9))
						3
				end
			
			expect(valor).to eq 3
		end
	end

end
