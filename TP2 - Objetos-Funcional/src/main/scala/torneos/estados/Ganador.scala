package torneos.estados

import participantes.Inscripto

case class Ganador[T <: Inscripto](participante: T) extends Estado[T]{
  val ganador: (List[T] => T) => Estado[T] = { _ =>
    this
  }

  def get: T = participante
}