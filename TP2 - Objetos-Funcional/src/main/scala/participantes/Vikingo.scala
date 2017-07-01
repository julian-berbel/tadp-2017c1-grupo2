package participantes

import Item._
import dragones.Dragon
import postas.Posta

import scala.util.Try

package object Vikingo{
  case class Vikingo(caracteristicas: Caracteristicas) extends Participante with Inscripto{
    val peso: Int = caracteristicas.peso
    val item: Item = caracteristicas.item
    val barbarosidad: Int = caracteristicas.barbarosidad
    val hambre: Int = caracteristicas.hambre
    val velocidad: Int = caracteristicas.velocidad
    val _dragon: Option[Dragon] = None

    def tieneUn[T<:Item]: Boolean = item.isInstanceOf[T] //TODO broken

    def puedeCargar: Int = peso / 2 + barbarosidad

    val danio: Int = barbarosidad + item.danio

    def deltaHambre(delta: Int): Vikingo =
      copy(caracteristicas = caracteristicas.deltaHambre(delta))

    def montar(unDragon: Dragon): Option[Jinete] = Try(Jinete(this, unDragon)).toOption

    val cuantoPuedeCargar: Int = peso / 2 + barbarosidad * 2

    val tieneMontura: Boolean = false

    def correr(posta: Posta): Try[Vikingo] =
      Try(posta.darHambre(this))

    override def prepararse: List[Vikingo] =
      List(this)
  }

  object hipo extends Vikingo(Caracteristicas(peso = 1, item = SistemaDeVuelo(), barbarosidad = 2, hambre = 0, velocidad = 3))

  object astrid extends Vikingo(Caracteristicas(peso = 4, item = Hacha(30), barbarosidad = 5, hambre = 0, velocidad = 6))

  object patan extends Vikingo(Caracteristicas(peso = 7, item = Maza(100), barbarosidad = 8, hambre = 0, velocidad = 9))

  object pataPez extends Vikingo(Caracteristicas(peso = 10, item = Comestible(11), barbarosidad = 12, hambre = 0, velocidad = 13)){
    override def puedeCorrer(posta: Posta): Boolean =
      super.puedeCorrer(posta) && correr(posta).get.hambre < 50

    override def correr(posta: Posta): Try[Vikingo] =
      super.correr(posta).map(item(_))
  }
}