package postas.requerimientos

import Item.Arma
import participantes.Participante

case class BarbaridadMinima(barbaridad: Int) extends Requerimiento{
  def esCumplidoPor(participante: Participante): Boolean =
    participante.barbarosidad > barbaridad || participante.tieneUn(Arma)
}