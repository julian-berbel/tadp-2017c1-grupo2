package postas.requerimientos

import Item.Item
import participantes.Participante

case class ItemRequerido[T<:Item]() extends Requerimiento{
  def esCumplidoPor(participante: Participante): Boolean =
    participante.tieneUn[T]
}