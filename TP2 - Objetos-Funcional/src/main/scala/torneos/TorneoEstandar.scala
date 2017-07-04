package torneos

import dragones.Dragon
import participantes.Vikingo.Vikingo
import postas.Posta

case class TorneoEstandar(postas: List[Posta], dragones: List[Dragon]) extends Torneo[Vikingo] with PasajeDeRondaEstandar[Vikingo] with SinReagrupar{
  val criterioGanador: List[Vikingo] => Option[Vikingo] = {_.headOption}
}