class Is_a
    def initialize(una_clase)
        @clase = una_clase
    end

    def ===(un_objeto)
        un_objeto.is_a? @clase
    end
end

def self.is_a(una_clase)
    Is_a.new(una_clase)
end
