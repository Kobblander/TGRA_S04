package com.tgra.client.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <h1>MapUtils</h1>
 * <h2>com.tgra.client.utility</h2>
 * <p></p>
 * Created on 19.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class MapUtils {

    private MapUtils() {
    }

    public static Object getKeyFromValue(Map<?, ?> hm, Object value){
        List<Object> list = new ArrayList<Object>();
        for(Object o:hm.keySet()){
            if(hm.get(o).equals(value)) {
                list.add(o);
            }
        }
        return list.get(0);
    }

    public static List<Object> getKeysFromValue(Map<?, ?> hm, Object value){
        List<Object> list = new ArrayList<Object>();
        for(Object o:hm.keySet()){
            if(hm.get(o).equals(value)) {
                list.add(o);
            }
        }
        return list;
    }
}
