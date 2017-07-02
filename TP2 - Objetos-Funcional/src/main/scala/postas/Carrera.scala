package postas

import participantes.Participante
import requerimientos.MonturaRequerida

case class Carrera(largo: Int, requerimiento: Option[MonturaRequerida] = None) extends Posta{
  def evaluar(participante: Participante): Int =
    participante.velocidad

  val cuantaHambreDa: Int = largo
}