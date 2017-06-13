package dragones
import participantes.Vikingo

case class Nadder(peso: Int) extends Dragon(peso) {
  val danio: Int = 150

  override def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
    super.puedeSerMontadoPor(unVikingo) && unVikingo.danio < danio
}