package com.github.bitfexl.tetris.javagame.render;

import com.github.bitfexl.tetris.javagame.exception.RenderException;
import com.github.bitfexl.tetris.javagame.render.component.DrawLayer;

import java.awt.*;
import java.util.ArrayList;

public class LayerManager {
    /**
     * The layers to render.
     */
    private ArrayList<DrawLayer> layers;

    /**
     * Init a new LayerManager.
     */
    public LayerManager() {
        this.layers = new ArrayList<>();
    }

    /**
     * Render all layers in order. Stops when a layer returns false on renderBelowLayers().
     * @param g2d The graphics object to render on.
     * @param props The props of the current render/frame.
     */
    public void renderAll(Graphics2D g2d, RenderProps props) throws RenderException {
        int i;

        for(i=layers.size()-1; i>=0; i--) {
            if(!layers.get(i).renderBelowLayers()) {
                break;
            }
        }

        for(i=Math.max(i, 0); i<layers.size(); i++) {
            layers.get(i).draw(g2d, props);
        }
    }

    /**
     * Add the layer on top of all others.
     * @param layer The layer to add.
     */
    public void addLayer(DrawLayer layer) {
        layers.add(layer);
    }

    /**
     * Add a layer at a given index (calls List.add(index, layer), does not handle exceptions).
     * @param index The index to add the layer.
     * @param layer The layer to add.
     */
    public void addLayerAt(int index, DrawLayer layer) {
        layers.add(index, layer);
    }

    /**
     * Remove a layer, if the layer has been added more than once it removes all of them.
     * @param layer The layer to remove.
     */
    public void removeLayer(DrawLayer layer) {
        while (layers.remove(layer)) {
        }
    }
}
