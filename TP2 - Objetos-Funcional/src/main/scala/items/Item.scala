package main.scala.items
import vikingos.Vikingo

package object item{
  type Item = Vikingo => Vikingo
  
  def arma(danio: Int): Item = {vikingo => vikingo.danio(danio)}
  
  val hacha = arma _
  
  val maza = arma _
  
  def comestible(hambreReducida: Int): Item = {vikingo => vikingo.hambre(-hambreReducida)}
  
  def sistemaDeVuelo: Item = {vikingo => vikingo}
}