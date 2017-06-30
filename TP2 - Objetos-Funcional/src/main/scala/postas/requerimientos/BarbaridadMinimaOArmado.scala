package postas.requerimientos

import Item.Arma
import participantes.Participante

case class BarbaridadMinimaOArmado(barbaridad: Int) extends Requerimiento{
  def esCumplidoPor(participante: Participante): Boolean =
    BarbaridadMinima(barbaridad).esCumplidoPor(participante) || participante.tieneUn[Arma]
}