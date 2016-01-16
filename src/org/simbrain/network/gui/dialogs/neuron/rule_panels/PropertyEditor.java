package org.simbrain.network.gui.dialogs.neuron.rule_panels;

import org.simbrain.network.gui.ParameterGetter;
import org.simbrain.network.gui.ParameterSetter;

/**
 * Wraps a getter and setter object on an object, of a specified type.  A string
 * description is provided to serve as a key when editors are stored in maps.
 *
 * @param <O> the type of the object whose property is being edited, e.g. NeuronUpdateRule.
 * @param <V> the value that is being edited, e.g. Double.
 */
public class PropertyEditor<O,V> {
    
    public Class<V> type;
    public String key;
    public ParameterGetter<O,V> getter;
    public ParameterSetter<O,V> setter;
    
    /**
     * @param type
     * @param description
     * @param parameterGetter
     * @param parameterSetter
     */
    public PropertyEditor(Class<V> type, String description, ParameterGetter<O,V> parameterGetter,
            ParameterSetter<O,V> parameterSetter) {
        super();
        this.type = type;
        this.key = description;
        this.getter = parameterGetter;
        this.setter = parameterSetter;
    }

}
