package participantes

import items._
import dragones.Dragon
import postas.Posta

import scala.util.Try

package object Vikingo {
  case class Vikingo(nombre: String, caracteristicas: Caracteristicas) extends Participante with Inscripto {
    val peso: Int = caracteristicas.peso
    val item: Item = caracteristicas.item
    val barbarosidad: Int = caracteristicas.barbarosidad
    val hambre: Int = caracteristicas.hambre
    val velocidad: Int = caracteristicas.velocidad

    def tieneUn(tipoDeItem: TipoDeItem): Boolean =
      item.tipo == tipoDeItem

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

    def mejorMontura(dragones: List[Dragon], posta: Posta): Option[Participante] = {
      val jinetes = dragones.map(this.montar(_)).flatten

      val participantes : List[Participante] = this :: jinetes
      val participantesQuePuedenCorrer : List[Participante] = participantes.filter(_.puedeCorrer(posta))

      participantesQuePuedenCorrer.maxByOption(posta.evaluar)
    }
  }

  object hipo extends Vikingo("Hipo", Caracteristicas(1, SistemaDeVuelo(), 2, 0, 3))

  object astrid extends Vikingo("Astrid", Caracteristicas(4, Hacha(30), 5, 0, 6))

  object patan extends Vikingo("Patan", Caracteristicas(7, Maza(100), 8, 0, 9))

  object pataPez extends Vikingo("PataPez", Caracteristicas(10, Comestible(11), 12, 0, 13)){
    override def puedeCorrer(posta: Posta): Boolean =
      super.puedeCorrer(posta) && correr(posta).get.hambre < 50

    override def correr(posta: Posta): Try[Vikingo] =
      super.correr(posta).map(item(_))
  }
}
