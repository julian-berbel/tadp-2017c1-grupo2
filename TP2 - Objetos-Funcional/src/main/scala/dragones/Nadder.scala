package dragones
import participantes.Vikingo._
import postas.requerimientos.Requerimiento

case class Nadder(peso: Int, requerimientos: List[Requerimiento] = List.empty) extends Dragon(peso, requerimientos) {
  val danio: Int = 150

  override def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
    super.puedeSerMontadoPor(unVikingo) && unVikingo.danio < danio
}