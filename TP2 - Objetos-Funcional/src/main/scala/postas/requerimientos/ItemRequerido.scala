package postas.requerimientos

import Item.TipoDeItem
import participantes.Participante

case class ItemRequerido(tipoDeItem: TipoDeItem) extends Requerimiento{
  def esCumplidoPor(participante: Participante): Boolean =
    participante.tieneUn(tipoDeItem)
}