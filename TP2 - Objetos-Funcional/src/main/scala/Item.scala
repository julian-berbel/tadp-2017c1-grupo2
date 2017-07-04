import participantes.Vikingo._

package object Item{
  class Item(val tipo: TipoDeItem){
    def danio = 0

    def apply(vikingo: Vikingo): Vikingo = vikingo
  }

  trait TipoDeItem
  
  class Arma(override val danio: Int) extends Item(tipoArma)

  case class Hacha(override val danio: Int) extends Arma(danio)

  case class Maza(override val danio: Int) extends Arma(danio)

  case class Comestible(hambreReducida: Int) extends Item(tipoComestible){
    override def apply(vikingo: Vikingo): Vikingo = vikingo.deltaHambre(-hambreReducida)
  }

  case class SistemaDeVuelo() extends Item(tipoSistemaDeVuelo)

  case object tipoArma extends TipoDeItem

  case object tipoComestible extends TipoDeItem

  case object tipoSistemaDeVuelo extends TipoDeItem
}