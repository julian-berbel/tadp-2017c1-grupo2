package torneos

import dragones.Dragon
import participantes.Vikingo.Vikingo
import participantes.Inscripto
import postas.Posta
import torneos.estados.{Estado, Ganador, TodosPerdedores, VariosCompetidores}

trait Torneo[T <: Inscripto] {
  val postas: List[Posta]

  val dragones: List[Dragon]

  def competir(participantes: List[T]): Option[T] = {

    val resultado =
      postas.foldLeft(VariosCompetidores(participantes): Estado[T]) { (estado, posta) =>
        estado match {
          case VariosCompetidores(sobrevivientes) =>
            val _sobrevivientes = posta.competir(ordenDeEleccion(sobrevivientes.flatMap(_.prepararse)), dragonesDisponibles)
            val reagrupados = reagrupar(_sobrevivientes, participantes)
            reagrupados.length match {
              case 0 => TodosPerdedores()
              case 1 => Ganador(reagrupados.head)
              case _ => VariosCompetidores(reagrupar(criterioPasajeDeRonda(_sobrevivientes), participantes))
            }
          case _estado => _estado
        }
      }

    resultado.ganador(criterioGanador)
  }

  val criterioGanador: List[T] => Option[T]

  def reagrupar(sobrevivientes: List[Vikingo], participantesOriginales: List[T]): List[T]

  def criterioPasajeDeRonda: List[Vikingo] => List[Vikingo]

  def ordenDeEleccion(vikingos: List[Vikingo]): List[Vikingo] = vikingos

  val dragonesDisponibles: List[Dragon] = dragones
}