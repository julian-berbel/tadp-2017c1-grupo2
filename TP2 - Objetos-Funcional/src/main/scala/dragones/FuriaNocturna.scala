package dragones

import postas.requerimientos.Requerimiento

case class FuriaNocturna(peso: Int, danio: Int, requerimientos: List[Requerimiento] = List.empty) extends Dragon(peso, requerimientos) {
  override def velocidadBasica: Int = super.velocidadBasica * 3
}