class IsA
  def initialize(una_clase)
    @clase = una_clase
  end

  def ===(un_objeto)
    un_objeto.is_a? @clase
  end
end

class Object
  def is_a(una_clase)
    IsA.new(una_clase)
  end
end
