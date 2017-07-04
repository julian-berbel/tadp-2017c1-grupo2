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
            val _sobrevivientes = reagrupar(posta.competir(ordenDeEleccion(sobrevivientes.flatMap(_.prepararse)), dragonesDisponibles), participantes)
            _sobrevivientes.length match {
              case 0 => TodosPerdedores()
              case 1 => Ganador(_sobrevivientes.head)
              case _ => VariosCompetidores(criterioPasajeDeRonda(_sobrevivientes))
            }
          case _estado => _estado
        }
      }

    resultado.ganador(criterioGanador)
  }

  val criterioGanador: List[T] => Option[T]

  def reagrupar(sobrevivientes: List[Vikingo], participantesOriginales: List[T]): List[T]

  def criterioPasajeDeRonda: List[T] => List[T]

  def ordenDeEleccion(vikingos: List[Vikingo]): List[Vikingo] = vikingos

  val dragonesDisponibles: List[Dragon] = dragones
}