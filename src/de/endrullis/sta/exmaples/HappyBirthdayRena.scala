package de.endrullis.sta.exmaples

import java.awt.Color

import de.endrullis.sta._

/**
 * Animation of flying letters.
 *
 * @author Stefan Endrullis &lt;stefan@endrullis.de&gt;
 */
object HappyBirthdayRena extends ScalaTikzAni {

	conf.boundingBox = Pos(-2,-1.5) -> Pos(3,2)

	def splitIntoLetters(s: String) = {
		s.indices.toList.map(i => "\\phantom{"+s.substring(0,i)+"}"+s.charAt(i)+"\\phantom{"+s.substring(i+1)+"}")
	}

	implicit val posTm = TimeMap.sin[Pos]
	implicit val doubleTm = TimeMap.linear[Double]
	implicit val colorTm = TimeMap.linear[Color]

	conf.frameRate = 10

	def rel(rt: Double, pos: Double) = (1-(math.abs(rt-pos) min math.abs(rt-pos-1) min 1.0/3)*3).toFloat
	def color(rt: Double) = new Color(rel(rt, 0), rel(rt, 1.0/3), rel(rt, 2.0/3))

	splitIntoLetters("Happy Birthday").zipWithIndex.foreach{ case (s, i) =>
		val x = math.sin(i/10.0) change (rt => math.sin(i/10.0 + rt*2*math.Pi)) in 10
		val y = math.cos(i/10.0) change (rt => math.cos(i/10.0 + rt*2*math.Pi)) in 10
		val pos = Pos(x/2,y/2+1)

		//val pos = (Pos(3,0) stay i changeTo Pos(0,0) in 10)

		val col = color(1-i/14.0) change (rt => color(1-(rt+i/14.0)%1)) in 10
		val op  = 1.0

		add(defColor("mycolor", col))

		//add(("\\node[opacity=$op, mycolor] at $pos {"+s+"};") << ("pos" -> pos, "op" -> 1.0))
		add(vi"\node[opacity=$op, mycolor] at $pos {$s};")
	}

	splitIntoLetters("Rena").zipWithIndex.foreach{ case (s, i) =>
		val x = math.sin(i/10.0) change (rt => math.sin(i/10.0 + rt*2*math.Pi)) in 10
		val y = math.cos(i/ 4.0) change (rt => math.cos(i/ 4.0 + rt*2*math.Pi)) in 10
		val pos = Pos(x/2,y/1.5)

		//val pos = (Pos(3,0) stay i changeTo Pos(0,0) in 10)

		val col    = color(1-i/4.0) change (rt => color(1-(rt+i/4.0)%1)) in 10
		val op     = 1.0
		val rotate = 0.0 stay (4-i)/2.0 changeTo -360.0 in (TimeMap.sin, 8) stay i/2.0

		add(defColor("mycolor", col))

		add(vi"\node[opacity=$op, mycolor, rotate=$rotate] at $pos {$s};")
	}

	animateNotInLine("gen/rena", SlideOptions(options = "width=13cm,autoplay,loop", frameRate = 30))

}
