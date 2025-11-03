from manim import *

class DrawGraph(Scene):
    def construct(self):
        defaultRadius = 0.5
        vertexColour = PURPLE
        leafColour = GREEN
        textColour = BLACK
        textFontSize = 20
        vertex=["1.00","c","b","a","0.33","0.07","f","e","d","h","g"]
        edges=[(vertex[0],vertex[1]),(vertex[0],vertex[2]),(vertex[0],vertex[3]),(vertex[0],vertex[4]),(vertex[4],vertex[5]),(vertex[4],vertex[6]),(vertex[4],vertex[7]),(vertex[4],vertex[8]),(vertex[5],vertex[9]),(vertex[5],vertex[10])]
        
        
        g=Graph(
            vertex,
            edges, 
            layout="tree",
            layout_scale=4,
            labels=True,
            root_vertex=vertex[0], 
            vertex_config={
                "1.00": {"stroke_color": vertexColour,"stroke_width": 3, "radius": defaultRadius},
                "c": {"stroke_color": leafColour,"stroke_width": 3, "radius": defaultRadius},
                "b": {"stroke_color": leafColour,"stroke_width": 3, "radius": defaultRadius},
                "a": {"stroke_color": leafColour,"stroke_width": 3, "radius": defaultRadius},
                "0.33": {"stroke_color": vertexColour,"stroke_width": 3, "radius": defaultRadius},
                "0.07": {"stroke_color": vertexColour,"stroke_width": 3, "radius": defaultRadius},
                "f": {"stroke_color": leafColour,"stroke_width": 3, "radius": defaultRadius},
                "e": {"stroke_color": leafColour,"stroke_width": 3, "radius": defaultRadius},
                "d": {"stroke_color": leafColour,"stroke_width": 3, "radius": defaultRadius},
                "h": {"stroke_color": leafColour,"stroke_width": 3, "radius": defaultRadius},
                "g": {"stroke_color": leafColour,"stroke_width": 3, "radius": defaultRadius}
            },
            edge_config={
                "stroke_color": BLACK
            }
        )
        
        g._labels["a"].set_color(textColour)
        g._labels["b"].set_color(textColour)
        g._labels["c"].set_color(textColour)
        g._labels["d"].set_color(textColour)
        g._labels["e"].set_color(textColour)
        g._labels["f"].set_color(textColour)
        g._labels["g"].set_color(textColour)
        g._labels["h"].set_color(textColour)
        g._labels["1.00"].set_color(textColour)
        g._labels["0.33"].set_color(textColour)
        g._labels["0.07"].set_color(textColour)
        
        tA=Text("0.22",color=textColour,font_size=textFontSize)
        tA.next_to(g.vertices["a"], DOWN, buff=0.1)
        tB=Text("0.20", color=textColour,font_size=textFontSize)
        tB.next_to(g.vertices["b"], DOWN, buff=0.1)
        tC=Text("0.18", color=textColour,font_size=textFontSize)
        tC.next_to(g.vertices["c"], DOWN, buff=0.1)
        tD=Text("0.15", color=textColour,font_size=textFontSize)
        tD.next_to(g.vertices["d"], DOWN, buff=0.1)
        tE=Text("0.10", color=textColour,font_size=textFontSize)
        tE.next_to(g.vertices["e"], DOWN, buff=0.1)
        tF=Text("0.08", color=textColour,font_size=textFontSize)
        tF.next_to(g.vertices["f"], DOWN, buff=0.1)
        tG=Text("0.05", color=textColour,font_size=textFontSize)
        tG.next_to(g.vertices["g"], DOWN, buff=0.1)
        tH=Text("0.02", color=textColour,font_size=textFontSize)
        tH.next_to(g.vertices["h"], DOWN, buff=0.1)
        
        self.camera.background_color = WHITE
        self.add(g)
        self.add(tA,tB,tC,tD,tE,tF,tG,tH)
        
