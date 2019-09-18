require 'rspec'

RSpec.configure do |config|
  config.after do
    %i(UnaCaseClass OtraCaseClass UnaClase OtraClase).each do |it|
      if Object.const_defined? it
        Object.send :remove_const, it
      end
    end
  end
end

require_relative '../src/tp_inmutabilidad'
