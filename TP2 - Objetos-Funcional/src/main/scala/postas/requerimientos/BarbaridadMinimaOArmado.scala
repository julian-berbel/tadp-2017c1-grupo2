package postas.requerimientos

import Item.tipoArma
import participantes.Participante

case class BarbaridadMinimaOArmado(barbaridad: Int) extends Requerimiento{
  def esCumplidoPor(participante: Participante): Boolean =
    BarbaridadMinima(barbaridad).esCumplidoPor(participante) || ItemRequerido(tipoArma).esCumplidoPor(participante)
}