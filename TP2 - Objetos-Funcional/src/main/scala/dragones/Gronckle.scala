package dragones
import participantes.Vikingo

case class Gronckle(peso: Int, pesoMaximoJinete: Int) extends Dragon(peso) {
  override def velocidadBasica: Int = super.velocidadBasica / 2
  
  val danio: Int = peso * 5

  override def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
    super.puedeSerMontadoPor(unVikingo) && unVikingo.peso < pesoMaximoJinete
}