package participantes

import items.Item

case class Caracteristicas(peso: Int, item: Item, barbarosidad: Int, hambre: Int, velocidad: Int) {
  require(hambre >= 0 && hambre < 100, "Me quedé con hambre")

  def deltaHambre(delta: Int): Caracteristicas = copy(hambre = (hambre + delta).max(0))
}
