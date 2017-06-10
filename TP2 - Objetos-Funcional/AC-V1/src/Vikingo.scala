package berk
{
  case class Vikingo (
      atributos: Atributos,
      item: Item) {
    def peso = atributos.peso;
    def velocidad = atributos.peso;
    def barbosidad = atributos.barbosidad;
    def hambre = atributos.hambre;
    def peso(unPeso : Int) = copy(atributos = atributos.copy(peso = unPeso));
    def velocidad(unaVelocidad : Int) = copy(atributos = atributos.copy(velocidad = unaVelocidad));
    def hambre(unHambre : Int) = copy(atributos = atributos.copy(hambre = unHambre));
    def barbosidad(unaBarbosidad : Int) = copy(atributos.copy(barbosidad = unaBarbosidad));
     
    def danio = this.barbosidad + (item match {
      case Arma(unDanio) => unDanio
    })
    def combatir = copy(atributos.copy(hambre = this.hambre+10))
  }
  case class Atributos (
      peso : Int,             //Kg
      velocidad : Int,        //Km/h
      barbosidad : Int,       
      hambre : Int,           //Porcentaje 0 a 100
  )
  {
    require(peso>0);
    require(velocidad>0);
    require(barbosidad>=0);
    require(0 to 100 contains hambre);
  }
  trait Item
  case class Consumible() extends Item
  case class Arma(danio: Int) extends Item 
  case class SistemaDeVuelo() extends Item
  
  trait Posta
  
  case class Combate(vikingos:List[Vikingo]) extends Posta {
    def aplicar : List[Vikingo] = vikingos.map(vi=>vi.combatir);
    def ganador = vikingos.maxBy(vi=>vi.danio);
  }
  class Pesca extends Posta {
    
  }
  class Carrera extends Posta {
    
  }
  
  class Dragon {
    
  }
}