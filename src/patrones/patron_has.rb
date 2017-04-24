class Has
    def initialize(symbol, value)
        @symbol = ('@' + symbol.to_s).to_sym
        @value = value
    end

    def ===(un_objeto)
        un_objeto.instance_variables.include?(@symbol) &&
        un_objeto.instance_variable_get(@symbol) == @value
    end
end

def self.has(symbol, value)
    Has.new(symbol, value)
end
