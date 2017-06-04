require 'rspec'
require_relative '../../src/tp_inmutabilidad'

describe 'Case Class' do

    before do
        case_class Una_case_class do
            attr_accessor :una_variable, :otra_variable
        end

        @un_objeto = Una_case_class.new(3,8)
    end

    describe 'case_class' do
        it 'crea una clase' do
            expect(Una_case_class.class).to eq Class
        end


        context 'si no le doy una clase padre' do
            it 'es subclase de Object' do
                expect(Una_case_class.superclass).to eq Object
            end
        end

        context 'si le doy una clase padre' do
            it 'es subclase de esa clase' do
                class Una_clase
                end
                case_class Otra_clase < Una_clase do end
                expect(Otra_clase.superclass).to eq Una_clase
            end
        end
    end

    describe '.new' do
        it 'crea un objeto de la clase' do
            expect(Una_case_class.new.class).to eq Una_case_class
        end

        context 'si le doy parametros' do
            it 'setea las variables de instancia' do
                expect(Una_case_class.new(1, 2).var_values).to eq [1, 2]
            end
        end
    end

    describe '.inherited' do
        it 'prohibe heredar de una Case Class' do
            expect {class Una_clase < Una_case_class 
                    end}.to raise_error(TypeError)
        end
    end

    describe '#copy' do
        context 'si no le paso lambdas' do
            it 'deberia devolver el mismo objeto' do
                expect(@un_objeto.copy).to eq @un_objeto
            end
        end

        context 'si le paso lambdas' do
            it 'deberia devolver el mismo objeto modificado' do
                expect(@un_objeto.copy ->(una_variable){una_variable + 5}).to eq Una_case_class(8, 8)
            end
			
            it 'deberia poder tomar mas de una lambda' do
                expect(@un_objeto.copy ->(una_variable){una_variable + 5}, -> (otra_variable){6}).to eq Una_case_class(8, 6)
            end
			
			it 'si el atributo no existe deberia romper' do
				expect{@un_objeto.copy ->(una_variable_que_no_tiene){2} }.to raise_error RuntimeError
			end
        end
    end

end
