package postas

import participantes.Vikingo
import postas.requerimientos.BarbaridadMinimaOArmado

case class Combate(requerimiento: Some[BarbaridadMinimaOArmado]) extends Posta{
  def criterioPosta(vikingo: Vikingo): Int =
    vikingo.danio

  val cuantaHambreDa: Int = 10
}