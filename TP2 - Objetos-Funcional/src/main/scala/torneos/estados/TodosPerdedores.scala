package torneos.estados

import participantes.Inscripto

case class TodosPerdedores[T <: Inscripto]() extends Estado[T]{
  val ganador: (List[T] => T) => Estado[T] = { _ =>
    this
  }

  def get: T = throw new NoSuchElementException("Son todos perdedores!")
}