package postas

import participantes.Vikingo
import requerimientos.MonturaRequerida

case class Carrera(requerimiento: Option[MonturaRequerida], largo: Int) extends Posta{
  def criterioPosta(vikingo: Vikingo): Int =
    vikingo.velocidad

  val cuantaHambreDa: Int = largo
}