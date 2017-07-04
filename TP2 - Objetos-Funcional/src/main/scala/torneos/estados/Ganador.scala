package torneos.estados

import participantes.Inscripto

case class Ganador[T <: Inscripto](participante: T) extends Estado[T]{
  val ganador: (List[T] => Option[T]) => Option[T] = { _ =>
    Some(participante)
  }
}