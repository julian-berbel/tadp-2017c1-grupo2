import participantes.Vikingo._

package object Item{
  trait Item{
    def danio = 0

    def apply(vikingo: Vikingo): Vikingo = vikingo
  }
  
  class Arma(override val danio: Int) extends Item

  case class Hacha(override val danio: Int) extends Arma(danio)

  case class Maza(override val danio: Int) extends Arma(danio)
  
  case class Comestible(hambreReducida: Int) extends Item{
    override def apply(vikingo: Vikingo): Vikingo = vikingo.deltaHambre(-hambreReducida)
  }
  
  case class SistemaDeVuelo() extends Item
}