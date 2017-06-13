package participantes

import Item._
import dragones.Dragon

import scala.util.Try

case class Vikingo(peso: Int, item: Item, barbarosidad: Int, hambre: Int, velocidad: Int) extends Participante{
  require(hambre >= 0 && hambre <= 100, "Me quedé con hambre")

  def tieneUn[T>:Item](_item: T): Boolean = item == _item

  def puedeCargar: Int = peso / 2 + barbarosidad

  val danio: Int = barbarosidad + item.danio

  def deltaHambre(delta: Int): Vikingo = copy(hambre = hambre + delta)

  def montar(unDragon: Dragon): Try[Jinete] = Try(Jinete(unDragon, this))

  val cuantoPuedeCargar: Int = peso / 2 + barbarosidad * 2

  val tieneMontura: Boolean = false
}

object hipo extends Vikingo(peso = ???, item = SistemaDeVuelo, barbarosidad = ???, hambre = 0, velocidad = ???)

object astrid extends Vikingo(peso = ???, item = Hacha(30), barbarosidad = ???, hambre = 0, velocidad = ???)

object patan extends Vikingo(peso = ???, item = Maza(100), barbarosidad = ???, hambre = 0, velocidad = ???)

object pataPez extends Vikingo(peso = ???, item = Comestible(???), barbarosidad = ???, hambre = 0, velocidad = ???)