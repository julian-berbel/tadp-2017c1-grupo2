package dragones

import Item.SistemaDeVuelo
import participantes.Vikingo

case class FuriaNocturna(peso: Int, danio: Int) extends Dragon(peso, List.empty) {
  override def velocidadBasica: Int = super.velocidadBasica * 3
}

object chimuelo extends FuriaNocturna(???, ???){
  override def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
    unVikingo.tieneUn(SistemaDeVuelo) && super.puedeSerMontadoPor(unVikingo)
}