package dragones

import Vikingo._
import Item.SistemaDeVuelo

case class FuriaNocturna(peso: Int, danio: Int) extends Dragon(peso) {
  override def velocidadBasica: Int = super.velocidadBasica * 3
}

object chimuelo extends FuriaNocturna(???, ???){
  override def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
    unVikingo.tieneUn(SistemaDeVuelo) && super.puedeSerMontadoPor(unVikingo)
}