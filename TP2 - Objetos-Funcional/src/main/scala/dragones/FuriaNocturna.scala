package dragones

import Item.SistemaDeVuelo
import postas.requerimientos.{ItemRequerido, Requerimiento}

case class FuriaNocturna(peso: Int, danio: Int, requerimientos: List[Requerimiento] = List.empty) extends Dragon(peso, requerimientos) {
  override def velocidadBasica: Int = super.velocidadBasica * 3
}

object chimuelo extends FuriaNocturna(50, 20, List(ItemRequerido[SistemaDeVuelo]()))