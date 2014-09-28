package staex

import de.endrullis.sta._

/**
 * @author Stefan Endrullis &lt;stefan@endrullis.de&gt;
 */
object Tutorial extends ScalaTikzAni {

	conf.frameRate = 30
	conf.boundingBox = Pos(-2, -1.5) -> Pos(2, 1.5)
	conf.slideTemplate = "templates/fullscreen-slide.template"
	conf.header =
"""
%\tikzstyle{myShadow} = [blur shadow={shadow blur steps=8}]
\tikzstyle{block} = [rectangle, draw, fill=blue!20, text width=3em, text centered, rounded corners, minimum height=2em]
"""


	// set default time map to linear
	implicit def timeMap[T] = TimeMap.linear[T]
	// set position time map to sinus for natural movements
	implicit def timeMapPos = TimeMap.decelerate[Pos]

	val letters = Text.splitIntoLetters("Scala Tikz Animations")

	for ((letter, i) <- letters.zipWithIndex) {
		add(Text.node(letter, Pos(1,2.2) stay i.toDouble/letters.size changeTo Pos(0,1.2) in (TimeMap.decelerate, 1)))
	}



  //add(Text.node("Scala Tikz Animations" stay 1))

	//add(Text.node(0 pause() changeTo 3 in 3 pause() changeTo 6 in 3))

	animateNotInLine("gen/tutorial", SlideOptions(options = "width=\\paperwidth,autoplay"))

}
