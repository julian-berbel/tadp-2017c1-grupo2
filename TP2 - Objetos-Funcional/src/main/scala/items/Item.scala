package main.scala.items
import vikingos.Vikingo

package object item{
  type Item = Nordico => Nordico
  
  def arma(danio: Int): Item = {nordico => nordico.calcularDanio(danio)}
  
  val hacha = arma _
  
  val maza = arma _
  
  def comestible(hambreReducida: Int): Item = 
    {nordico => nordico.hambre(-hambreReducida)}
  
  def sistemaDeVuelo: Item = {nordico => nordico}
}