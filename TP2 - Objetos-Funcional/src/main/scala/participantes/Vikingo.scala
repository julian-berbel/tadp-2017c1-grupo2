package participantes

import Item._
import dragones.Dragon
import postas.Posta

import scala.util.Try

package object Vikingo{
  case class Vikingo(nombre: String, caracteristicas: Caracteristicas) extends Participante with Inscripto{
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
      dragones.foldLeft(Some(this).filter(_.puedeCorrer(posta)): Option[Participante]) { (mejorPorAhora, dragon) =>
        val otroParticipante: Option[Participante] = this.montar(dragon).filter(_.puedeCorrer(posta))

        (mejorPorAhora, otroParticipante) match{
          case (None, _) => otroParticipante
          case (_, None) => mejorPorAhora
          case (Some(mejor), Some(otro)) =>
            if (otro.esMejorQue(mejor)(posta)){
              otroParticipante
            }
            else mejorPorAhora
        }
      }
    }

  }

  object hipo extends Vikingo("Hipo", Caracteristicas(peso = 1, item = SistemaDeVuelo(), barbarosidad = 2, hambre = 0, velocidad = 3))

  object astrid extends Vikingo("Astrid", Caracteristicas(peso = 4, item = Hacha(30), barbarosidad = 5, hambre = 0, velocidad = 6))

  object patan extends Vikingo("Patan", Caracteristicas(peso = 7, item = Maza(100), barbarosidad = 8, hambre = 0, velocidad = 9))

  object pataPez extends Vikingo("PataPez", Caracteristicas(peso = 10, item = Comestible(11), barbarosidad = 12, hambre = 0, velocidad = 13)){
    override def puedeCorrer(posta: Posta): Boolean =
      super.puedeCorrer(posta) && correr(posta).get.hambre < 50

    override def correr(posta: Posta): Try[Vikingo] =
      super.correr(posta).map(item(_))
  }
}