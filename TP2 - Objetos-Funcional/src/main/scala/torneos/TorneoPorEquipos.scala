package torneos

import dragones.Dragon
import participantes.Vikingo.Vikingo
import participantes.Equipo
import postas.Posta

case class TorneoPorEquipos(postas: List[Posta], dragones: List[Dragon]) extends Torneo[Equipo] with PasajeDeRondaEstandar{
  val criterioGanador: List[Equipo] => Equipo = {equipos =>
    equipos.maxBy(_.miembros.length)
  }

  def reagrupar(vikingos: List[Vikingo], originales: List[Equipo]): List[Equipo] =
    originales.map(_.selectMembers(vikingos)).flatten
}
