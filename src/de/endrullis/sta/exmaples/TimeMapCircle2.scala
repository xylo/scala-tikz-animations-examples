package de.endrullis.sta.exmaples

import de.endrullis.sta._

/**
 * Letter "A" flying in a circle by using TimeMaps for simulating sin and cos.
 * This example shall demonstrate TimeMaps can be used and misused. :)
 *
 * @author Stefan Endrullis &lt;stefan@endrullis.de&gt;
 */
object TimeMapCircle2 extends ScalaTikzAni {

	conf.boundingBox = Pos(-2,-2) -> Pos(2,2)

	conf.frameRate = 10

	val x = 0.0 changeTo 1.0 in (TimeMap(rt => math.sin(rt*2*math.Pi)), 10)
	val y = 0.0 changeTo 1.0 in (TimeMap(rt => math.cos(rt*2*math.Pi)), 10)
	val pos = Pos(x,y)
	//val pos = (x~~y).map{case x~~y => Pos(x,y)}

	add(vi"\node at $pos {A};")

	animateNotInLine("gen/circle", SlideOptions(frameRate = 30))

}
