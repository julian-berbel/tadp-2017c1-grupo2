package torneos

import dragones.Dragon
import participantes.Vikingo.Vikingo
import postas.Posta

case class TorneoInverso(postas: List[Posta], dragones: List[Dragon]) extends Torneo[Vikingo] with SinReagrupar{
  val criterioGanador: List[Vikingo] => Option[Vikingo] = {_.lastOption}

  def criterioPasajeDeRonda: (List[Vikingo]) => List[Vikingo] = {vikingos => vikingos.takeRight(vikingos.length / 2)}
}