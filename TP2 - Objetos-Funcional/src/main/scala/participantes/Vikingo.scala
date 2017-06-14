package participantes

import Item._
import dragones.Dragon
import postas.Posta

import scala.util.Try

case class Vikingo(caracteristicas: Caracteristicas) extends Participante{

  val peso: Int = caracteristicas.peso
  val item: Item = caracteristicas.item
  val barbarosidad: Int = caracteristicas.barbarosidad
  val hambre: Int = caracteristicas.hambre
  val velocidad: Int = caracteristicas.velocidad

  def tieneUn[T>:Item](_item: T): Boolean = item == _item

  def puedeCargar: Int = peso / 2 + barbarosidad

  val danio: Int = barbarosidad + item.danio

  def deltaHambre(delta: Int): Vikingo =
    copy(caracteristicas = caracteristicas.deltaHambre(delta))

  def montar(unDragon: Dragon): Try[Jinete] = Try(Jinete(this, unDragon))

  val cuantoPuedeCargar: Int = peso / 2 + barbarosidad * 2

  val tieneMontura: Boolean = false

  def correr(posta: Posta): Try[Vikingo] =
    Try(posta.darHambre(this))
}

object hipo extends Vikingo(Caracteristicas(peso = ???, item = SistemaDeVuelo, barbarosidad = ???, hambre = 0, velocidad = ???))

object astrid extends Vikingo(Caracteristicas(peso = ???, item = Hacha(30), barbarosidad = ???, hambre = 0, velocidad = ???))

object patan extends Vikingo(Caracteristicas(peso = ???, item = Maza(100), barbarosidad = ???, hambre = 0, velocidad = ???))

object pataPez extends Vikingo(Caracteristicas(peso = ???, item = Comestible(???), barbarosidad = ???, hambre = 0, velocidad = ???)){
  override def puedeCorrer(posta: Posta): Boolean =
    super.puedeCorrer(posta) && correr(posta).get.hambre < 50

  override def correr(posta: Posta): Try[Vikingo] =
    super.correr(posta).map(item(_))
}