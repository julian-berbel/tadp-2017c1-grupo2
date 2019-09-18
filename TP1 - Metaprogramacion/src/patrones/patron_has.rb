class Has
  def initialize(symbol, value)
    @symbol = :"@#{symbol}"
    @value = value
  end

  def ===(un_objeto)
    un_objeto.instance_variables.include?(@symbol) && un_objeto.instance_variable_get(@symbol) == @value
  end
end

class Object
  def has(symbol, value)
    Has.new(symbol, value)
  end
end
