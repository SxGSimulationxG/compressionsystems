from manim import *
import numpy as np

class CreateGraph(Scene):
    def construct(self):
        # ---------- Style ----------
        leafColour = GREEN
        textColour = BLACK
        edgeColour = BLACK
        groupBoxColour = BLACK
        textFontSize = 20
        edgeLabelSize = 18
        top_y = 3.0
        y_step = 1.2  # vertical gap between levels
        x_spacing = 1.5

        self.camera.background_color = WHITE

        # ---------- Data (symbol, probability) ----------
        symbols = [
            ("a", 0.22),
            ("b", 0.20),
            ("c", 0.18),
            ("d", 0.15),
            ("e", 0.10),
            ("f", 0.08),
            ("g", 0.05),
            ("h", 0.02),
        ]

        # ---------- Shannon–Fano split ----------
        def shannon_fano_split(items):
            if len(items) <= 1:
                return items, []
            total = sum(p for _, p in items)
            best_idx = 1
            best_diff = float("inf")
            run = 0.0
            for i in range(1, len(items)):
                run += items[i - 1][1]
                diff = abs(total / 2 - run)
                if diff < best_diff:
                    best_diff = diff
                    best_idx = i
            return items[:best_idx], items[best_idx:]

        # ---------- Tree building ----------
        def build_tree(items):
            node = {"items": items, "children": []}
            if len(items) > 1:
                L, R = shannon_fano_split(items)
                node["children"] = [build_tree(L), build_tree(R)]
            return node

        tree = build_tree(symbols)

        # ---------- Layout ----------
        def layout_positions(items):
            n = len(items)
            return {s: (i - n / 2) * x_spacing for i, (s, _) in enumerate(items)}

        leaf_x = layout_positions(symbols)

        def node_span_x(node):
            leaf_names = [s for s, _ in node["items"]]
            xs = [leaf_x[s] for s in leaf_names]
            return min(xs), max(xs)

        # ---------- Drawing ----------
        all_groups = VGroup()
        all_edges = VGroup()
        all_edge_labels = VGroup()

        def place_node(node, depth=0, parent_box=None, bit_label=None):
            xmin, xmax = node_span_x(node)
            x_center = 0.5 * (xmin + xmax)
            y = top_y - depth * y_step
            width = max(1.0, (xmax - xmin) + 1.0)

            rect = RoundedRectangle(
                corner_radius=0.2,
                width=width,
                height=0.9,
                color=groupBoxColour
            ).move_to([x_center, y, 0])

            names = "".join([s for s, _ in node["items"]])
            total_p = sum(p for _, p in node["items"])
            label = Text(f"{names} | {total_p:.2f}", font_size=textFontSize, color=textColour)
            label.move_to(rect.get_center())
            group = VGroup(rect, label)
            all_groups.add(group)

            if parent_box is not None:
                line = Line(parent_box.get_bottom(), rect.get_top(), stroke_color=edgeColour)
                all_edges.add(line)
                if bit_label is not None:
                    t = Text(str(bit_label), font_size=edgeLabelSize, color=textColour)
                    t.next_to(line, RIGHT if rect.get_center()[0] >= parent_box.get_center()[0] else LEFT, buff=0.05)
                    all_edge_labels.add(t)

            if node["children"]:
                L, R = node["children"]
                place_node(L, depth + 1, rect, bit_label=0)
                place_node(R, depth + 1, rect, bit_label=1)

        place_node(tree)

        # Center and fill frame
        full_group = VGroup(all_groups, all_edges, all_edge_labels)
        full_group.scale_to_fit_height(6)
        full_group.move_to(ORIGIN)

        # ---------- Add to scene ----------
        self.add(all_edges, all_edge_labels, all_groups)
