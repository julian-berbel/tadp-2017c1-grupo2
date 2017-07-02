package postas

import participantes.Participante
import postas.requerimientos.BarbaridadMinimaOArmado

case class Combate(barbaridadMinima: Int) extends Posta{
  val requerimiento = Some(BarbaridadMinimaOArmado(barbaridadMinima))

  def evaluar(participante: Participante): Int =
    participante.danio

  val cuantaHambreDa: Int = 10
}