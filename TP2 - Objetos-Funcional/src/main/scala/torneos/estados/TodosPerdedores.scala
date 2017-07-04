package torneos.estados

import participantes.Inscripto

case class TodosPerdedores[T <: Inscripto]() extends Estado[T]{
  val ganador: (List[T] => Option[T]) => Option[T] = { _ =>
    None
  }
}