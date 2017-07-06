package torneos

import dragones.Dragon
import participantes.Vikingo.Vikingo
import participantes.Inscripto
import postas.Posta
import torneos.estados.{Estado, Ganador, TodosPerdedores, VariosCompetidores}

trait Torneo[T <: Inscripto] {
  val postas: List[Posta]

  val dragones: List[Dragon]

  def competir(participantes: List[T]): Estado[T] = {

    val resultado =
      postas.foldLeft(VariosCompetidores(participantes): Estado[T]) { (estado, posta) =>
        estado.continuar(posta, this, participantes)
      }

    resultado.ganador(criterioGanador)
  }

  val criterioGanador: List[T] => T

  def reagrupar(sobrevivientes: List[Vikingo], participantesOriginales: List[T]): List[T]

  def criterioPasajeDeRonda: List[Vikingo] => List[Vikingo]

  def ordenDeEleccion(vikingos: List[Vikingo]): List[Vikingo] = vikingos

  val dragonesDisponibles: List[Dragon] = dragones
}