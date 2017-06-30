import participantes.Vikingo

package object Item{
  trait Item{
    def danio = 0

    def apply(vikingo: Vikingo): Vikingo = vikingo
  }
  
  case class Arma(override val danio: Int) extends Item
  
  def Hacha(danio: Int) = Arma(danio)
  
  def Maza(danio: Int) = Arma(danio)
  
  case class Comestible(hambreReducida: Int) extends Item{
    override def apply(vikingo: Vikingo): Vikingo = vikingo.deltaHambre(-hambreReducida)
  }
  
  case class SistemaDeVuelo() extends Item
}