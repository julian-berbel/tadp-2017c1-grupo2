require 'rspec'
require_relative '../src/tp_inmutabilidad'

describe 'Case_Class' do

    before do
        case_class Una_clase do
            attr_accessor :una_variable, :otra_variable
        end

        @un_objeto = Una_clase.new(3,8)
    end

    describe 'case_class' do
        it 'crea una clase' do
            expect(Una_clase.class).to eq Class
        end


        context 'si no le doy una clase padre' do
            it 'es subclase de Object' do
                expect(Una_clase.superclass).to eq Object
            end
        end

        context 'si le doy una clase padre' do
            it 'es subclase de esa clase' do
                class Una_clase_normal
                end
                case_class Otra_clase < Una_clase_normal do end
                expect(Otra_clase.superclass).to eq Una_clase_normal
            end
        end
    end

    describe '.new' do
        it 'crea un objeto de la clase' do
            expect(Una_clase.new.class).to eq Una_clase
        end

        context 'si le doy parametros' do
            it 'setea las variables de instancia' do
                expect(Una_clase.new(1, 2).var_values).to eq [1, 2]
            end
        end
    end

    describe '.inherited' do
        it 'prohibe heredar de una Case Class' do
            expect {class Una_clase_normal < Una_clase 
                    end}.to raise_error(TypeError)
        end
    end

    describe 'buenos defaults' do

        describe '#to_s' do
            it 'imprime el nombre de la clase seguido de los valores de sus atributos entre parentesis' do
                expect(@un_objeto.to_s).to eq 'Una_clase(3, 8)'
            end
        end

        describe '#hash' do
            it 'devuelve la sumatoria de los hashes de los atributos + 7' do
                expect(@un_objeto.hash).to eq (3.hash + 8.hash + 7)
            end
        end

        describe '#==' do
            it 'da true para dos objetos de la misma clase y mismos valores de atributos' do
                otro_objeto_igual = Una_clase.new(3,8)
                expect(@un_objeto == otro_objeto_igual).to be true
            end

            it 'da false para dos objetos de la misma clase y distintos valores de atributos' do
                un_objeto_con_distintos_atributos = Una_clase.new(5,7)
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

    describe '#copy' do
        context 'si no le paso lambdas' do
            it 'deberia devolver el mismo objeto' do
                expect(@un_objeto.copy).to eq @un_objeto
            end
        end

        context 'si le paso lambdas' do
            it 'deberia devolver el mismo objeto modificado' do
                expect(@un_objeto.copy ->(una_variable){una_variable + 5}).to eq Una_clase(8, 8)
            end
            it 'deberia poder tomar mas de una lambda' do
                expect(@un_objeto.copy ->(una_variable){una_variable + 5}, -> (otra_variable){6}).to eq Una_clase(8, 6)
            end
        end
    end

end
