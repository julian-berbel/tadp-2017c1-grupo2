package dragones
import participantes.Vikingo._
import postas.requerimientos.Requerimiento

case class Gronckle(peso: Int, pesoMaximoJinete: Int, requerimientos: List[Requerimiento] = List.empty) extends Dragon(peso, requerimientos) {
  override def velocidadBasica: Int = super.velocidadBasica / 2
  
  val danio: Int = peso * 5

  override def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
    super.puedeSerMontadoPor(unVikingo) && unVikingo.peso < pesoMaximoJinete
}