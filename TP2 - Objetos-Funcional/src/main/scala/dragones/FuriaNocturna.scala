package main.scala.dragones
import items.item._
import vikingos.Vikingo

case class FuriaNocturna(peso: Int, 
                         danio: Int, 
                         criterio2: Option[Criterio], 
                         min: Option[Int], 
                         item:Option[Item]) extends Dragon(peso) 
{
  def barbarosidadMinima(min:Int, v: Vikingo):Boolean = v.barbarosidad > min
  
  override def velocidadBasica: Int = super.velocidadBasica * 3
  
  def puedeSerMontadoPor(unVikingo: Vikingo): Boolean =
  {
      condicion1(unVikingo) && condicion2(unVikingo, min, item)
  }
  
  def itemParticular(v: Vikingo, item:Item): Boolean = v.tieneUn(item)
  
  def condicion2(unVikingo: Vikingo, min: Option[Int], item:Option[Item]): Boolean = 
  {
    criterio2 match 
    {
      case barbarosidadMinima if min.get != None => this.barbarosidadMinima(min.get, unVikingo)
      case itemParticular if item.get != None => this.itemParticular(unVikingo, item.get)
      case None => true 
    } 
  }
}

object chimuelo extends FuriaNocturna(???, ???){
  override def puedeSerMontadoPor(unVikingo: Vikingo) = unVikingo.tieneUn(sistemaDeVuelo) && super.puedeSerMontadoPor(unVikingo)
}